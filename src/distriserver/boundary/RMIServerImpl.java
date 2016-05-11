/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import distriserver.ClientRemote;
import distriserver.controller.Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author FrederikSwag
 */
public class RMIServerImpl extends UnicastRemoteObject implements RMIServerI {

    Server server;

    public RMIServerImpl(Server server) throws RemoteException {
        this.server = server;
    }

    @Override
    public boolean login(String user, String pass) throws RemoteException {
        return server.login(user, pass);
    }

    @Override
    public String getStats(String userID) throws RemoteException {
        return server.getStats(userID);
    }

    @Override
    public String getLobbies() throws RemoteException {
        return server.getLobbies();
    }

    
    //Lobby methods
    
    @Override
    public void join(int lobbyNr, String userID, ClientRemote remoteClient) {
        server.join(lobbyNr, userID, remoteClient);
    }

    @Override
    public ArrayList<String> getOtherPlayers(int lobbyNr) {
        return server.getOtherPlayers(lobbyNr);
    }

    @Override
    public int getTimeLeft(int lobbyNr) {
        return server.getTimeLeft(lobbyNr);
    }

    @Override
    public void gætBogstav(int lobbyNr, String bogstav, String userID) throws RemoteException {
        server.gætBogstav(lobbyNr, bogstav, userID);
    }

    @Override
    public String getSynligtOrd(int lobbyNr) throws RemoteException {
        return server.getSynligtOrd(lobbyNr);
    }

    @Override
    public int getAntalForkerteBogstaver(int lobbyNr) throws RemoteException {
        return server.getAntalForkerteBogstaver(lobbyNr);
    }

    @Override
    public ArrayList<String> getBrugteBogstaver(int lobbyNr) throws RemoteException {
        return server.getBrugteBogstaver(lobbyNr);
    }

}
