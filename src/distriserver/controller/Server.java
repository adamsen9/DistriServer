/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;


import distriserver.boundary.RMIServerImpl;
import distriserver.boundary.SOAPServerImpl;
import distriserver.boundary.brugerautorisation.Brugerautorisation;

import distriserver.entity.UserI;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.xml.ws.Endpoint;
import lobby.LobbyI;

/**
 *
 * @author FrederikSwag
 */
public class Server {
    Brugerautorisation ba;
    

    public Server() throws IOException {
        //Opsætning af RMI og SOAP servere
        //RMI-kommunikation
        ba = new Brugerautorisation();
        
        RMIServerImpl impl = new RMIServerImpl(this);
        
        java.rmi.registry.LocateRegistry.createRegistry(1099); // start rmiregistry i server-JVM

        Naming.rebind("rmi://10.16.227.109/RMIServerImpl", impl);
        System.out.println("Server publiceret over lokalt RMI");
        

        //SOAP-kommunikation
        //SOAPServerImpl impl2 = new SOAPServerImpl(this);
        //Endpoint.publish("http://[::]:9901/SOAPServerImpl", impl2);

        System.out.println("Server publiceret over SOAP");
    }

    public boolean login(String user, String pass) {
        return ba.login(user, pass);
    }

    public boolean joinLobby(UserI user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getStats(UserI user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<LobbyI> getLobbies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //Implementer returnering af lobby stub
    //public 
}
