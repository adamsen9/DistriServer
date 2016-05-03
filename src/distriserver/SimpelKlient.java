/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver;

import distriserver.boundary.GWTStub;
import distriserver.boundary.GWTStubImpl;
import java.rmi.Naming;
import java.util.Scanner;
import distriserver.boundary.RMIServerI;
import lobby.Lobby;

/**
 *
 * @author FrederikSwag
 */
public class SimpelKlient {
    static Lobby localLobby;

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
        for(Lobby lobby :server.getLobbies() ) {
            c++;
            System.out.println("Lobby nr " + c);
        }
        
        System.out.println("Går ind i lobby 1");
        
        GWTStub stub = (GWTStub) new GWTStubImpl();
        localLobby = server.getLobbies().get(0);
        
        localLobby.join(user, stub);
        System.out.println("Gætter M");
        localLobby.gætBogstav("M", user);
    }

}
