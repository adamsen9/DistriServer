/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.*;
import lobby.LobbyI;
import distriserver.entity.UserI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author FrederikSwag
 */
public class RMIServerImpl extends UnicastRemoteObject implements RMIServerI {

    public RMIServerImpl() throws RemoteException {

    }

    @Override
    public boolean login(String user, String pass) throws RemoteException {
        System.out.println("Der forsøges at logge ind med brugernavn: " + user + " kodeord: " + pass);

        //Lav opslag i brugerautorisations modul på en bruger med brugernavn user og kodeord pass
        try {

            Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

            Bruger b = ba.hentBruger(user, pass);

            if (b.fornavn.isEmpty()) {
                System.out.println("Login fail");
                return false;
            } else {
                System.out.println("Login sucess");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean joinLobby(UserI user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getStats(UserI user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LobbyI> getLobbies() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
