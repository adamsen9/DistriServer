/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import distriserver.controller.Server;
import distriserver.entity.UserI;
import java.util.ArrayList;
import javax.jws.WebService;
import lobby.LobbyI;

/**
 *
 * @author FrederikSwag
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
    public int getStats(UserI user) {
        return server.getStats(user);
    }

    @Override
    public ArrayList<LobbyI> getLobbies() {
        return server.getLobbies();
    }

    @Override
    public boolean joinLobby(UserI user) {
        return server.joinLobby(user);
    }
}
