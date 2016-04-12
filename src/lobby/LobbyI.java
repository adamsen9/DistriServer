/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import distriserver.entity.UserI;
import java.util.ArrayList;

/**
 *
 * @author FrederikSwag
 */
public interface LobbyI  {
    ArrayList<UserI> getUsers();
    boolean addUser(UserI user);
    
    
    
}
