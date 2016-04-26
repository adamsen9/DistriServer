/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;

import distriserver.boundary.RMIServerImpl;
import java.io.IOException;
import java.rmi.Naming;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;

/**
 *
 * @author FrederikSwag
 */
public class ServerMain {
    
    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
