/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import distriserver.controller.Server;
import java.util.ArrayList;
import javax.jws.WebService;

/**
 *
 * @author Frederik
 */

@WebService(endpointInterface = "distriserver.boundary.SOAPServerI")
public class SOAPServerImpl implements SOAPServerI {

    Server server;

    public SOAPServerImpl(Server server) {
        this.server = server;
    }

    @Override
    public boolean login(String user, String pass) {
        return server.login(user, pass);
    }

    @Override
    public String getStats(String userID) {
        return server.getStats(userID);
    }

    @Override
    public String getLobbies() {
        return server.getLobbies();
    }

    @Override
    public void join(int lobbyNr, String userID) {
        server.join(lobbyNr, userID);
    }

    @Override
    public ArrayList<String> getOtherPlayers(int lobbyNr) {
        return server.getOtherPlayers(lobbyNr);
    }

    @Override
    public int getTimeLeft(int lobbyNr) {
        return server.getTimeLeft(lobbyNr);
    }

    @Override
    public void gætBogstav(int lobbyNr, String bogstav, String userID) {
        server.gætBogstav(lobbyNr, bogstav, userID);
    }

    @Override
    public String getSynligtOrd(int lobbyNr) {
        return server.getSynligtOrd(lobbyNr);
    }

    @Override
    public int getAntalForkerteBogstaver(int lobbyNr) {
        return server.getAntalForkerteBogstaver(lobbyNr);
    }

    @Override
    public ArrayList<String> getBrugteBogstaver(int lobbyNr) {
        return server.getBrugteBogstaver(lobbyNr);
    }

}
