/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import distriserver.ClientRemote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author FrederikSwag
 */
public interface RMIServerI extends java.rmi.Remote {

    //Klient/sever metoder
    boolean login(String user, String pass) throws RemoteException;

    String getStats(String userID) throws RemoteException;

    String getLobbies() throws RemoteException;

    //Lobby metoder
    void join(int lobbyNr, String userID, ClientRemote remoteClient) throws RemoteException;

    ArrayList<String> getOtherPlayers(int lobbyNr) throws RemoteException;

    int getTimeLeft(int lobbyNr) throws RemoteException;

    void gætBogstav(int lobbyNr, String bogstav, String userID) throws RemoteException;

    String getSynligtOrd(int lobbyNr) throws RemoteException;

    int getAntalForkerteBogstaver(int lobbyNr) throws RemoteException;

    ArrayList<String> getBrugteBogstaver(int lobbyNr) throws RemoteException;
}
