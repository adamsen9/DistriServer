/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;

import distriserver.boundary.RMIServerImpl;
import distriserver.boundary.SOAPServerImpl;
import java.io.IOException;
import java.rmi.Naming;
import javax.xml.ws.Endpoint;

/**
 *
 * @author FrederikSwag
 */
public class ServerMain {
    
    	public static void main(String[] arg) throws IOException
	{
                
                //RMI-kommunikation
		RMIServerImpl impl = new RMIServerImpl();
		java.rmi.registry.LocateRegistry.createRegistry(1099); // start rmiregistry i server-JVM
                
		Naming.rebind("rmi://localhost/RMIServerImpl", impl);
		System.out.println("Server publiceret over lokalt RMI");
                
                
                //SOAP-kommunikation
                SOAPServerImpl impl2 = new SOAPServerImpl();
                Endpoint.publish("http://[::]:9901/SOAPServerImpl", impl2);
                
		System.out.println("Server publiceret over SOAP");
                
                
                
	}
    
}
