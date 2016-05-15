/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;

import distriserver.ClientRemote;
import distriserver.boundary.RMIServerImpl;
import distriserver.boundary.brugerautorisation.Brugeraut;
import distriserver.entity.BufferThread;
import distriserver.entity.DAL;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
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
        //RMI-kommunikation
        ba = new Brugeraut();

        lobbyThreads = new ArrayList<>();

        RMIServerImpl impl = new RMIServerImpl(this);
        
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("rmiserverimpl",impl);
        
        System.out.println("Server publiceret over RMI");

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

}
