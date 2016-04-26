/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import distriserver.entity.Buffer;
import distriserver.entity.DAL;
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
    Lobby lobby;
    CountdownClass cc;
    Buffer buffer;

    int intMax;
    String strMax = "";

    public LobbyThread(Lobby lobby, Buffer buffer) {
        this.lobby = lobby;
        this.buffer = buffer;
    }

    //Implementer spillet og  kommunikation med AL her
    public void run() {
        timer = 30;
        try {
            while (true) {
                Thread.sleep(100);
                if(lobby.nyeSpillere) {
                    //Opdater alle stubbe om at der er nye spillere
                }

                if (timer == 0) {
                    //Omgangen er færdig og stemmer skal tælles op
                    //Hvis der ingen stemmer er, start forfra
                    //Hvis de to der har flest stemmer har lige mange, vælg tilfældigt
                    intMax = 0;
                    Iterator it = lobby.getVotes().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        //Match med max
                        System.out.println("");
                        if ((Integer) pair.getValue() > intMax) {
                            intMax = (Integer) pair.getValue();
                            strMax = (String) pair.getKey();
                        }

                    }
                    if (intMax != 0) {
                        //Opdater kun stemmer hvis der rent faktisk er afgivet nogle
                        if (lobby.getOrdet().contains(strMax)) {
                            //Bogstaver er gættet
                                //Opdater synligt ord
                                
                            //Opdater med positivt point
                            for (String str : lobby.getVoters().get("A")) {
                                //Send opdatering ud til alle bruger id der matcher str

                                //Uddel point ud fra hvad der er stemt
                            }

                        } else {
                            //Bogstavet er gættet forkert
                            
                            //Opdater med negativt point
                            for (String str : lobby.getVoters().get("A")) {
                                //Send opdatering ud til alle bruger id der matcher str
                                
                                //Uddel point ud fra hvad der er stemt
                                
                            }

                        }

                    } else {
                        //Hvis der ikke er afgivet nogle stemmer skal der ikke ske noget
                    }

                } else {
                    //Send opdateringer vedrørende den nuværende afstemning og brugere i lobbyen til klienter i stub listen

                }

            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

}
