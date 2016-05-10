/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import distriserver.boundary.GWTStub;
import distriserver.entity.Buffer;
import distriserver.entity.DAL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FrederikSwag
 */
public class LobbyThread implements Runnable {

    int timer = 10;
    int timerReset = 10;
    static Lobby lobby;
    CountdownClass cc;
    Buffer buffer;

    int intMax;
    String strMax = "";
    Iterator it;
    int id;

    ArrayList<String> listeAfSpillere;

    ArrayList<GWTStub> spillere;

    public LobbyThread(Buffer buffer, int id) {
        lobby = new Lobby();

        lobby.id = id;

        this.buffer = buffer;
        this.id = id;
        spillere = new ArrayList<>();
        cc = new CountdownClass(timer);
    }

    public Lobby getLobby() {
        return lobby;
    }

    //Implementer spillet og  kommunikation med AL her
    @Override
    public void run() {
        Thread t = new Thread(cc);
        t.start();
        System.out.println("LOBBYTRÅD ID ER " + id);

        
        try {
            while (true) {
                timer = cc.getTime();

                Thread.sleep(100);
                if (lobby.getSpillere().isEmpty()) {
                    //Hvis der ingen spillere er, gør ingenting
                    spillere.clear();
                    it = lobby.getSpillere().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        spillere.add(((GWTStub) pair.getValue()));
                    }

                }
                //TODO byg arrayliste af stubbe for nemmere opdatering længere nede i koden

                if (lobby.nyeSpillere()) {
                    System.out.println("Der er nye spillere og stubbe opdaeres");
                    //Opdater alle stubbe om at der er nye spillere
                    //TODO alt hvad der hedder opdatering af stubbe
                }

                if (cc.getTime() == 0) {
                    //Omgangen er færdig og stemmer skal tælles op
                    System.out.println("Omgangen er færdig og stemmer tælles op");
                    //Hvis der ingen stemmer er, start forfra
                    //Hvis de to der har flest stemmer har lige mange, vælg tilfældigt
                    intMax = 0;
                    strMax = "";

                    cc.resetTime(timerReset);

                    
                    it = lobby.getVotes().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        //Match med max
                        System.out.println(pair.getKey() + ": " + pair.getValue());

                        if ((Integer) pair.getValue() > intMax) {
                            intMax = (Integer) pair.getValue();
                            strMax = (String) pair.getKey();
                            System.out.println("max ord sættes til " + strMax);
                        }
                        lobby.getBrugteBogstaver().add(strMax);
                    }

                    if (intMax > 0) {
                        //Opdater kun stemmer hvis der rent faktisk er afgivet nogle
                        System.out.println("Der er afgivet stemmer");
                        if (lobby.getOrdet().contains(strMax)) {
                            //Bogstaver er gættet
                            //Opdater synligt ord
                            System.out.println("Bogstavet er gættet");
                            //Opdater med positivt point
                            for (String str : lobby.getVoters().get(strMax)) {
                                //Send opdatering ud til alle brugere
                                for (GWTStub stub : spillere) {
                                    try {
                                        stub.sidstGættet(strMax);
                                    } catch (RemoteException ex) {
                                        //TODO find ud af hvordan at vi fjerner spillere
                                        //fjernSpiller((String) pair.getKey());
                                    }
                                }
                                //Uddel point ud fra hvad der er stemt
                                buffer.addPositiveVote(str);
                            }

                        } else {
                            //Bogstavet er gættet forkert
                            System.out.println("Bogstavet er gættet forkert");
                            //Opdater med negativt point
                            for (String str : lobby.getVoters().get(strMax)) {
                                //Send opdatering ud til alle brugere
                                it = lobby.getSpillere().entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry pair = (Map.Entry) it.next();
                                    try {
                                        ((GWTStub) pair.getValue()).resultatAfAfstemning(true);
                                        ((GWTStub) pair.getValue()).sidstGættet("PLACEHOLDER");
                                    } catch (RemoteException ex) {
                                        //En remote exception betyder at der ikke er forbindelse til brugeren længere
                                        fjernSpiller((String) pair.getKey());
                                    }

                                }

                                //Uddel point ud fra hvad der er stemt
                                buffer.addNegativeVote(str);
                            }
                        }

                        //Gæt nulstilles
                        //lobby.nulstilGæt();

                    } else {
                        //Hvis der ikke er afgivet nogle stemmer skal der ikke ske noget
                        System.out.println("Der er ikke afgivet nogle stemmer og der genstartes");
                    }

                } else {
                    //Send opdateringer vedrørende den nuværende afstemning og brugere i lobbyen til klienter i stub listen

                    //Beskeder om nuvæerende afstemning
                    it = lobby.getSpillere().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();

                        try {

                            ((GWTStub) pair.getValue()).listeAfStemmer(lobby.stemmer);

                        } catch (RemoteException ex) {
                            //En remote exception betyder at der ikke er forbindelse til brugeren længere
                            fjernSpiller((String) pair.getKey());
                        }
                    }

                    //Udsender beskeder om spillerstatus
                    if (lobby.nyeSpillere) {
                        listeAfSpillere = new ArrayList<String>();

                        it = lobby.getSpillere().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            listeAfSpillere.add((String) pair.getKey());

                        }

                        it = lobby.getSpillere().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            listeAfSpillere.add((String) pair.getKey());
                            try {

                                ((GWTStub) pair.getValue()).listeAfSpillere(listeAfSpillere);

                            } catch (RemoteException ex) {
                                //En remote exception betyder at der ikke er forbindelse til brugeren længere
                                fjernSpiller((String) pair.getKey());
                            }
                        }

                    }
                }

            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void fjernSpiller(String userID) {
        lobby.getSpillere().remove(userID);
    }

}
