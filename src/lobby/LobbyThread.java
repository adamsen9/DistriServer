/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import distriserver.ClientRemote;
import distriserver.entity.Buffer;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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

    ArrayList<ClientRemote> spillere;

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
                        spillere.add(((ClientRemote) pair.getValue()));
                        it.remove();
                    }

                }
                //TODO byg arrayliste af stubbe for nemmere opdatering længere nede i koden

                if (lobby.nyeSpillere()) {
                    System.out.println("Der er nye spillere og klienter opdateres");
                    //Opdater alle stubbe om at der er nye spillere
                    //TODO alt hvad der hedder opdatering af stubbe
                }

                if (lobby.nyeStemmer) {
                    System.out.println("Der er afgivet nye stemmer og klienter opdateres");
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
                                for (ClientRemote stub : spillere) {
                                    try {
                                        stub.sidstGættet(strMax);
                                    } catch (RemoteException ex) {
                                        //TODO find ud af hvordan at vi fjerner spillere
                                        //fjernSpiller((String) pair.getKey());
                                    }
                                }
                                //Uddel point ud fra hvad der er stemt
                                buffer.addPositiveVote(str);

                                //Det rigtige bogstav tilføjes til listen
                                lobby.brugteBogstaver.add(strMax);

                                //Afstemning nulstilles
                                resetVoting();

                                //Opdaterer synligt ord
                                lobby.opdaterSynligtOrd();
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
                                        ((ClientRemote) pair.getValue()).resultatAfAfstemning(true);
                                        ((ClientRemote) pair.getValue()).sidstGættet("PLACEHOLDER");
                                    } catch (RemoteException ex) {
                                        //En remote exception betyder at der ikke er forbindelse til brugeren længere
                                        fjernSpiller((String) pair.getKey());
                                    }
                                    it.remove();
                                }



                                //Uddel point ud fra hvad der er stemt
                                buffer.addNegativeVote(str);

                                //Det forkerte bogstav tilføjes til listen
                                lobby.brugteBogstaver.add(strMax);

                                //Afstemning nulstilles
                                resetVoting();

                                //Opdaterer synligt ord
                                lobby.opdaterSynligtOrd();
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

                            ((ClientRemote) pair.getValue()).listeAfStemmer(lobby.stemmer);

                        } catch (RemoteException ex) {
                            //En remote exception betyder at der ikke er forbindelse til brugeren længere
                            fjernSpiller((String) pair.getKey());
                        }
                        it.remove();
                    }

                    //Udsender beskeder om spillerstatus
                    if (lobby.nyeSpillere) {
                        listeAfSpillere = new ArrayList<String>();

                        it = lobby.getSpillere().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            listeAfSpillere.add((String) pair.getKey());
                            it.remove();
                        }

                        it = lobby.getSpillere().entrySet().iterator();
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            listeAfSpillere.add((String) pair.getKey());
                            try {

                                ((ClientRemote) pair.getValue()).listeAfSpillere(listeAfSpillere);

                            } catch (RemoteException ex) {
                                //En remote exception betyder at der ikke er forbindelse til brugeren længere
                                fjernSpiller((String) pair.getKey());
                            }
                            it.remove();
                        }

                    }
                }

            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public int getTime() {
        return cc.getTime();
    }

    public static void fjernSpiller(String userID) {
        lobby.getSpillere().remove(userID);
    }

    public static void resetVoting() {
        lobby.resetVoting();
    }

}
