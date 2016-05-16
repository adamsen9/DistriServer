/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;

import distriserver.ClientRemote;
import distriserver.boundary.RMIServerImpl;
import distriserver.boundary.SOAPServerI;
import distriserver.boundary.SOAPServerImpl;
import distriserver.boundary.brugerautorisation.Brugeraut;
import distriserver.entity.BufferThread;
import distriserver.entity.DAL;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javax.xml.ws.Endpoint;
import lobby.LobbyThread;

/**
 *
 * @author FrederikSwag
 */
public class Server {

    Brugeraut ba;

    ArrayList<LobbyThread> lobbyThreads;
    Thread t;
    DAL dal;

    public Server() throws IOException, Exception {
        RMIServerImpl impl = new RMIServerImpl(this);
        SOAPServerImpl impl2 = new SOAPServerImpl(this);

        //SOAP kommunikation
        System.out.println("Publicerer galgeleg over SOAP");
        Endpoint.publish("http://[::]:9901/soapserverimpl", impl2);
        //RMI-kommunikation
        ba = new Brugeraut();

        SecurityManager appsm = System.getSecurityManager();

        lobbyThreads = new ArrayList<>();

        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("rmiserverimpl", impl);

        System.out.println("Server publiceret over RMI");
        
        
        //Til brug ved tests
        
       		//java.rmi.registry.LocateRegistry.createRegistry(1099); // start rmiregistry i server-JVM
		//Naming.rebind("rmi://localhost/brugeradmin", impl);

        //Oprettelse af lobbier og start af tråde
        System.out.println("Lobbier oprettes");
        dal = new DAL();

        for (int i = 1; i <= 1; i++) {
            lobbyThreads.add(new LobbyThread(dal.getBuffer(), "DINGERLING ".hashCode()));
        }

        for (LobbyThread lobbyThread : lobbyThreads) {
            t = new Thread(lobbyThread);
            t.start();
        }

        //DAL buffer startes
        System.out.println("DAL buffer oprettes og startes");
        BufferThread bufferThread = new BufferThread(dal, dal.getBuffer());
        t = new Thread(bufferThread);
        t.start();
    }

    public boolean login(String user, String pass) {
        try {
            return ba.login(user, pass);

        } catch (Exception e) {

            return false;
        }
    }

    public String getStats(String userID) {
        return dal.getUserStats(userID);
    }

    public String getLobbies() {
        String str = "";
        for (int i = 0; i < lobbyThreads.size() - 1; i++) {
            str += i + ", ";
        }
        str += lobbyThreads.get(lobbyThreads.size() - 1);
        return str;
    }

    public void gætBogstav(int lobbyNr, String bogstav, String userID) {
        lobbyThreads.get(lobbyNr).getLobby().gætBogstav(bogstav, userID);
    }

    public void join(int lobbyNr, String userID, ClientRemote remoteClient) {
        lobbyThreads.get(lobbyNr).getLobby().join(userID, remoteClient);
    }

    public ArrayList<String> getOtherPlayers(int lobbyNr) {
        return lobbyThreads.get(lobbyNr).getLobby().getSpillereID();
    }

    public int getTimeLeft(int lobbyNr) {
        return lobbyThreads.get(lobbyNr).getTime();
    }

    public String getSynligtOrd(int lobbyNr) {
        return lobbyThreads.get(lobbyNr).getLobby().getSynligtOrd();
    }

    public int getAntalForkerteBogstaver(int lobbyNr) {
        return lobbyThreads.get(lobbyNr).getLobby().getBrugteBogstaver().size();
    }

    public ArrayList<String> getBrugteBogstaver(int lobbyNr) {
        return lobbyThreads.get(lobbyNr).getLobby().getBrugteBogstaver();
    }

    public void join(int lobbyNr, String userID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
