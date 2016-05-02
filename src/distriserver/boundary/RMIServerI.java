/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import distriserver.entity.UserI;
import java.rmi.RemoteException;
import java.util.ArrayList;
import lobby.Lobby;

/**
 *
 * @author FrederikSwag
 */
public interface RMIServerI extends java.rmi.Remote {

    boolean login(String user, String pass) throws RemoteException;

    String getStats(String userID) throws RemoteException;

    ArrayList<Lobby> getLobbies() throws RemoteException;
}
