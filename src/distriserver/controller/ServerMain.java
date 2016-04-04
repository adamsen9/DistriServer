/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;

import distriserver.boundary.ServerImpl;
import java.io.IOException;
import java.rmi.Naming;

/**
 *
 * @author FrederikSwag
 */
public class ServerMain {
    
    	public static void main(String[] arg) throws IOException
	{
		System.out.println("Publicerer Brugeradmin over RMI");
                
                
		ServerImpl impl = new ServerImpl();
		java.rmi.registry.LocateRegistry.createRegistry(1099); // start rmiregistry i server-JVM
                
		Naming.rebind("rmi://localhost/ServerImpl", impl);
		System.out.println("Server publiceret over lokalt RMI");
	}
    
}
