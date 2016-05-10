/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;

import distriserver.boundary.RMIServerImpl;
import distriserver.boundary.brugerautorisation.Brugeraut;
import distriserver.entity.BufferThread;
import distriserver.entity.DAL;

import distriserver.entity.UserI;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import lobby.Lobby;
import lobby.LobbyThread;

/**
 *
 * @author FrederikSwag
 */
public class Server {

    Brugeraut ba;

    ArrayList<Lobby> lobbies;
    ArrayList<LobbyThread> lobbyThreads;
    Thread t;
    DAL dal;

    public Server() throws IOException {
        //Opsætning af RMI og SOAP servere
        //RMI-kommunikation
        ba = new Brugeraut();

        lobbies = new ArrayList<>();
        lobbyThreads = new ArrayList<>();

        RMIServerImpl impl = new RMIServerImpl(this);

        java.rmi.registry.LocateRegistry.createRegistry(1099); // start rmiregistry i server-JVM

        Naming.rebind("rmi://localhost/RMIServerImpl", impl);
        System.out.println("Server publiceret over lokalt RMI");

        //SOAP-kommunikation
        //SOAPServerImpl impl2 = new SOAPServerImpl(this);
        //Endpoint.publish("http://[::]:9901/SOAPServerImpl", impl2);
        System.out.println("Server publiceret over SOAP");

        //Oprettelse af lobbier og start af tråde
        System.out.println("Lobbier oprettes");
        dal = new DAL();

        for (int i = 1; i <= 1; i++) {
            lobbyThreads.add(new LobbyThread(dal.getBuffer(), "DINGERLING ".hashCode()));
        }

        for (LobbyThread lobbyThread : lobbyThreads) {
            lobbies.add(lobbyThread.getLobby());
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

    //TODO her skal returneres en lobby stub til klient, der da kan kalde join
    public String getStats(String userID) {
        return dal.getUserStats(userID);
    }

    public ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    //Implementer returnering af lobby stub
    //public 
}
