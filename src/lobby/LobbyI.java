/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import java.util.ArrayList;

/**
 *
 * @author FrederikSwag
 */
public interface LobbyI {

    //Join skal også have at klient subscriber med 
    void join(String userID);

    void leave(String userID);

    ArrayList<String> getPlayers();

    long getTimeLeft();

    String getPartialWord();

    ArrayList<String> getCorrectLetters();

    ArrayList<String> getWrongLetters();

    ArrayList<String> getVotes();

}
