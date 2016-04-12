/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary.brugerautorisation;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FrederikSwag
 */
public class brugerautorisation {

    Brugeradmin ba;

    public brugerautorisation() {
        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        } catch (Exception e) {

        }

    }

    public boolean login(String user, String pass) {
        System.out.println("Der forsøges at logge ind med brugernavn: " + user + " kodeord: " + pass);
        try {

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
