/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby.LobbyAL;

import distriserver.ClientRemote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lobby.Lobby;

/**
 *
 * @author Frederik
 */
public class RMILobbyImpl implements RMILobbyI {
    
    Lobby lobby;

    @Override
    public void join(String userID, ClientRemote remoteClient) {
        lobby.join(userID, remoteClient);
    }

    @Override
    public ArrayList<String> getOtherPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTimeLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSynligtOrd() throws RemoteException {
        lobby.getSynligtOrd();
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAntalForkerteBogstaver() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getBrugteBogstaver() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean erSpilletVundet() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean erSpilletTabt() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean erSpilletSlut() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gætBogstav(String userID, String bogstav) throws RemoteException {
        lobby.gætBogstav(userID, bogstav);
    }

    @Override
    public void logStatus() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
