/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;


import distriserver.boundary.RMIServerImpl;
import distriserver.boundary.SOAPServerImpl;
import distriserver.boundary.brugerautorisation.Brugeraut;

import distriserver.entity.UserI;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import lobby.LobbyI;
import lobby.LobbyImpl;
import lobby.LobbyThread;

/**
 *
 * @author FrederikSwag
 */
public class Server {
    Brugeraut ba;
    

    public Server() throws IOException {
        //Opsætning af RMI og SOAP servere
        //RMI-kommunikation
        ba = new Brugeraut();
        
        RMIServerImpl impl = new RMIServerImpl(this);
        
        java.rmi.registry.LocateRegistry.createRegistry(1099); // start rmiregistry i server-JVM

        Naming.rebind("rmi://localhost/RMIServerImpl", impl);
        System.out.println("Server publiceret over lokalt RMI");
        

        //SOAP-kommunikation
        //SOAPServerImpl impl2 = new SOAPServerImpl(this);
        //Endpoint.publish("http://[::]:9901/SOAPServerImpl", impl2);

        System.out.println("Server publiceret over SOAP");
        
        System.out.println("Tester timer i lobbytråd");
        
        
    }

    public boolean login(String user, String pass) {
        return ba.login(user, pass);
    }

    //TODO her skal returneres en lobby stub til klient, der da kan kalde join
    public boolean joinLobby(UserI user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getStats(String userID) {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<LobbyI> getLobbies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //Implementer returnering af lobby stub
    //public 
}
