/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver;

import distriserver.boundary.ServerI;
import java.rmi.Naming;
import java.util.Scanner;

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
        String pass = "clancbsfryser2";
        //String pass = reader.nextLine();

        System.out.println("Der forsøges at logges ind");

        //Opret forbindelse til server
        
        ServerI server = (ServerI) Naming.lookup("rmi://localhost/ServerImpl");
        if (server.login(user, pass)) {
            System.out.println("Login sucess");
        } else {
            System.out.println("Login fail");
        }

    }

}
