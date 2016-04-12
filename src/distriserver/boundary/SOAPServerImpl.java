/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

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

    @Override
    public boolean login(String user, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getStats(UserI user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LobbyI> getLobbies() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean joinLobby(UserI user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
