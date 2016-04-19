/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import lobby.LobbyAL.ALIncomingRMII;

/**
 *
 * @author FrederikSwag
 */
public class LobbyThread extends Thread {
    ALIncomingRMII lobbyAL;
    LobbyI lobby;
    
    
    public LobbyThread(LobbyI lobby, ALIncomingRMII lobbyAL) {
        this.lobby = lobby;
        this.lobbyAL = lobbyAL;
        
    }
    
    
    //Implementer spillet og  kommunikation med AL her
    public void run() {
        
        
        
        
        
    }
    
}
