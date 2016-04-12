/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import distriserver.entity.UserI;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import lobby.LobbyI;

/**
 *
 * @author FrederikSwag
 */

@WebService
public interface SOAPServerI {
    
    
    @WebMethod boolean login(String user, String pass);
    @WebMethod int getStats(UserI user);
    
    @WebMethod ArrayList<LobbyI> getLobbies();
    @WebMethod boolean joinLobby(UserI user);
    
}
