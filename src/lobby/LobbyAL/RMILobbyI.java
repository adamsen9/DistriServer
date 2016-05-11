/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby.LobbyAL;

import distriserver.ClientRemote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMILobbyI extends java.rmi.Remote {
    
    //Selvlavede
    
    //TODO tilføj at klient giver en stub med
    void join(String userID, ClientRemote remoteClient);
    
    ArrayList<String> getOtherPlayers();
    
    int getTimeLeft();
    
    //Kopieret fra Jacobs galgeleg

    String getSynligtOrd() throws RemoteException;

    int getAntalForkerteBogstaver() throws RemoteException;

    ArrayList<String> getBrugteBogstaver() throws RemoteException;

    boolean erSpilletVundet() throws RemoteException;

    boolean erSpilletTabt() throws RemoteException;

    boolean erSpilletSlut() throws RemoteException;

    void gætBogstav(String userID, String bogstav) throws RemoteException;

    void logStatus() throws RemoteException;

}
