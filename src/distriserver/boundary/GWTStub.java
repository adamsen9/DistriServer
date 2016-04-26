/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Frederik
 */
public interface GWTStub extends java.rmi.Remote {
    
        void listeAfSpillere(ArrayList<String> liste) throws RemoteException;
        
        void listeAfSpillere(HashMap<String, Integer> liste ) throws RemoteException;
        
        void synligtOrd(String str) throws RemoteException;
        
        void resultatAfAfstemning(boolean bool) throws RemoteException;
        
        void sidstGættet(String str) throws RemoteException;
}
