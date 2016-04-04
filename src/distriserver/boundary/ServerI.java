/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import java.rmi.RemoteException;

/**
 *
 * @author FrederikSwag
 */
public interface ServerI extends java.rmi.Remote {
    
    boolean login(String user, String pass) throws RemoteException;
    
    
}
