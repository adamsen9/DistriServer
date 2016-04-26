/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import distriserver.controller.Server;
import distriserver.entity.UserI;

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
    public boolean joinLobby(UserI user) throws RemoteException {
        return server.joinLobby(user);
    }

    @Override
    public String getStats(String userID) throws RemoteException {
        return server.getStats(userID);
    }

    @Override
    public ArrayList<String> getLobbies() throws RemoteException {
        return server.getLobbies();
    }
}
