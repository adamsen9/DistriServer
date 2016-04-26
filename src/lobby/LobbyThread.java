/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import java.util.logging.Level;
import java.util.logging.Logger;
import lobby.LobbyAL.ALIncomingRMII;

/**
 *
 * @author FrederikSwag
 */
public class LobbyThread extends Thread {

    ALIncomingRMII lobbyAL;
    Lobby lobby;

    public LobbyThread(Lobby lobby, ALIncomingRMII lobbyAL) {
        this.lobby = lobby;
        this.lobbyAL = lobbyAL;

    }

    //Implementer spillet og  kommunikation med AL her
    public void run() {
        try {
            System.out.println("Testing timer");
            System.out.println("5");
            Thread.sleep(1000);
            System.out.println("4");
            Thread.sleep(1000);
            System.out.println("3");
            Thread.sleep(1000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("1");
            Thread.sleep(1000);
            System.out.println("0");
            System.out.println("Færdig");

        } catch (InterruptedException ex) {
            Logger.getLogger(LobbyThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
