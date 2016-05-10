/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.controller;

/**
 *
 * @author FrederikSwag
 */
public class ServerMain {

    public static void main(String[] args) {
        try {
            new Server();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
