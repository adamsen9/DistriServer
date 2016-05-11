/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver;

import java.io.Serializable;
import java.rmi.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author FrederikSwag
 */
public interface ClientRemote extends Remote, Serializable {
    
    void listeAfSpillere(ArrayList<String> liste) throws RemoteException;
    
    void listeAfStemmer(HashMap<String, Integer> liste) throws RemoteException;
    
    void synligtOrd(String str) throws RemoteException;
    
    void resultatAfAfstemning(boolean bool) throws RemoteException;
    
    void sidstGættet(String str) throws RemoteException;
    
}
