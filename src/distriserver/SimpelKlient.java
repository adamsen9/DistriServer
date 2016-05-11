/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver;

import java.rmi.Naming;
import java.util.Scanner;
import distriserver.boundary.RMIServerI;

/**
 *
 * @author FrederikSwag
 */
public class SimpelKlient {


    public static void main(String[] args) throws Exception {

        Scanner reader = new Scanner(System.in);
        System.out.println("Indtast brugernavn: ");
        //String user = reader.nextLine();
        String user = "s123157";
        System.out.println("Indtast kodeord: ");
        String pass = "clancbs";
        //String pass = reader.nextLine();

        System.out.println("Der forsøges at logges ind");

        //Opret forbindelse til server
        RMIServerI server = (RMIServerI) Naming.lookup("rmi://localhost/RMIServerImpl");

        if (server.login(user, pass)) {
            System.out.println("Login sucess");
        } else {
            System.out.println("Login fail");
            System.exit(0);
        }

        System.out.println("Anmoder om lobbier");
        int c = 0;
        System.out.println("Lobbies: " + server.getLobbies());


        ClientRemote remoteClient = (ClientRemote) new ClientRemoteImpl();
        
        server.join(0, user, remoteClient);
        System.out.println("Der er joinet");
        
        System.out.println("Gætter M");
        
        server.gætBogstav(0, "M", user);
        
    }

}
