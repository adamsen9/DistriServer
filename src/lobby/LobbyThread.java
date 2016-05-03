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

    int timer;
    int timerReset = 30;
    static Lobby lobby;
    CountdownClass cc;
    Buffer buffer;

    int intMax;
    String strMax = "";
    Iterator it;
    int id;

    ArrayList<String> listeAfSpillere;

    public LobbyThread(Buffer buffer, int id) {
        lobby = new Lobby();
        this.buffer = buffer;
        this.id = id;
    }

    public Lobby getLobby() {
        return lobby;
    }

    //Implementer spillet og  kommunikation med AL her
    @Override
    public void run() {
        timer = 30;
        try {
            
            
            
            
            
            
            
            
            while (true) {
                    Thread.sleep(100);
                if (lobby.getSpillere().isEmpty()) {
                    //Hvis der ingen spillere er, gør ingenting

                } else {
                    if (lobby.nyeSpillere()) {
                        //Opdater alle stubbe om at der er nye spillere
                    }

                    if (timer == 0) {
                        //Omgangen er færdig og stemmer skal tælles op
                        //Hvis der ingen stemmer er, start forfra
                        //Hvis de to der har flest stemmer har lige mange, vælg tilfældigt
                        intMax = 0;
                        it = lobby.getVotes().entrySet().iterator();
                        timer = timerReset;

                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry) it.next();
                            //Match med max
                            if ((Integer) pair.getValue() > intMax) {
                                intMax = (Integer) pair.getValue();
                                strMax = (String) pair.getKey();
                            }
                            lobby.getBrugteBogstaver().add(strMax);

                        }
                        if (intMax != 0) {
                            //Opdater kun stemmer hvis der rent faktisk er afgivet nogle
                            if (lobby.getOrdet().contains(strMax)) {
                                //Bogstaver er gættet
                                //Opdater synligt ord

                                //Opdater med positivt point
                                for (String str : lobby.getVoters().get("A")) {
                                    //Send opdatering ud til alle brugere

                                    //Uddel point ud fra hvad der er stemt
                                    buffer.addPositiveVote(str);
                                }

                            } else {
                                //Bogstavet er gættet forkert

                                //Opdater med negativt point
                                for (String str : lobby.getVoters().get("A")) {
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

                        } else {
                            //Hvis der ikke er afgivet nogle stemmer skal der ikke ske noget
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

            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public static void fjernSpiller(String userID) {
        lobby.getSpillere().remove(userID);
    }

}
