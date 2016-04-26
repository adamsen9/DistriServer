/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import distriserver.entity.UserI;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author FrederikSwag
 */
public interface RMIServerI extends java.rmi.Remote {

    boolean login(String user, String pass) throws RemoteException;

    String getStats(String userID) throws RemoteException;

    ArrayList<String> getLobbies() throws RemoteException;

    boolean joinLobby(UserI user) throws RemoteException;
}
