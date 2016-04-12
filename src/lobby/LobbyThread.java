/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

/**
 *
 * @author FrederikSwag
 */
public class LobbyThread extends Thread {
    LobbyAL lobbyAL;
    LobbyI lobby;
    
    
    public LobbyThread(LobbyI lobby, LobbyAL lobbyAL) {
        this.lobby = lobby;
        this.lobbyAL = lobbyAL;
        
    }
    
    
    //Implementer spillet og  kommunikation med AL her
    public void run() {
        
        
        
        
        
    }
    
}
