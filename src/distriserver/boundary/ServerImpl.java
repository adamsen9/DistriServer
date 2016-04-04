/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.BrugeradminI;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author FrederikSwag
 */
public class ServerImpl extends UnicastRemoteObject implements ServerI {

    public ServerImpl() throws RemoteException {

    }

    @Override
    public boolean login(String user, String pass) throws RemoteException {
        System.out.println("Der forsøges at logge ind med brugernavn: " + user + " kodeord: " + pass);

        //Lav opslag i brugerautorisations modul på en bruger med brugernavn user og kodeord pass
        try {

            BrugeradminI ba = (BrugeradminI) Naming.lookup("rmi://javabog.dk/brugeradmin");

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
}
