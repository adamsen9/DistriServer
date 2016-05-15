/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import brugerautorisation.data.Bruger;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Frederik
 */

@WebService
public interface SOAPServerI {
    
    //Klient/sever metoder
    @WebMethod boolean login(String user, String pass);

    @WebMethod String getStats(String userID);

    @WebMethod String getLobbies();

    //Lobby metoder
    @WebMethod void join(int lobbyNr, String userID);

    @WebMethod ArrayList<String> getOtherPlayers(int lobbyNr);

    @WebMethod int getTimeLeft(int lobbyNr);

    @WebMethod void gætBogstav(int lobbyNr, String bogstav, String userID);

    @WebMethod String getSynligtOrd(int lobbyNr);

    @WebMethod int getAntalForkerteBogstaver(int lobbyNr);

    @WebMethod ArrayList<String> getBrugteBogstaver(int lobbyNr);
    
}
