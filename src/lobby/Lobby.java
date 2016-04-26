/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import distriserver.boundary.GWTStub;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author FrederikSwag
 */
public class Lobby {

    //Opdaterings kontol
    boolean nyeSpillere = false;

    //
    HashMap<String, Integer> stemmer;
    HashMap<String, ArrayList<String>> hvemStemteHvad;
    HashMap<String, GWTStub> spillere;

    String ordet = "MASSEØDELÆGGELSESVÅBEN";
    String synligtOrd = "";

    public Lobby() {
        //Setup af stemmer
        stemmer = new HashMap<>();
        stemmer.put("A", 0);
        stemmer.put("B", 0);
        stemmer.put("C", 0);
        stemmer.put("D", 0);
        stemmer.put("E", 0);
        stemmer.put("F", 0);
        stemmer.put("G", 0);
        stemmer.put("H", 0);
        stemmer.put("I", 0);
        stemmer.put("J", 0);
        stemmer.put("K", 0);
        stemmer.put("L", 0);
        stemmer.put("M", 0);
        stemmer.put("N", 0);
        stemmer.put("O", 0);
        stemmer.put("P", 0);
        stemmer.put("Q", 0);
        stemmer.put("R", 0);
        stemmer.put("S", 0);
        stemmer.put("T", 0);
        stemmer.put("U", 0);
        stemmer.put("V", 0);
        stemmer.put("W", 0);
        stemmer.put("X", 0);
        stemmer.put("Y", 0);
        stemmer.put("Z", 0);

        //Setup af hvemStemteHvad
        hvemStemteHvad = new HashMap<String, ArrayList<String>>();
        hvemStemteHvad.put("A", new ArrayList<String>());
        hvemStemteHvad.put("B", new ArrayList<String>());
        hvemStemteHvad.put("C", new ArrayList<String>());
        hvemStemteHvad.put("D", new ArrayList<String>());
        hvemStemteHvad.put("E", new ArrayList<String>());
        hvemStemteHvad.put("F", new ArrayList<String>());
        hvemStemteHvad.put("G", new ArrayList<String>());
        hvemStemteHvad.put("H", new ArrayList<String>());
        hvemStemteHvad.put("I", new ArrayList<String>());
        hvemStemteHvad.put("J", new ArrayList<String>());
        hvemStemteHvad.put("K", new ArrayList<String>());
        hvemStemteHvad.put("L", new ArrayList<String>());
        hvemStemteHvad.put("M", new ArrayList<String>());
        hvemStemteHvad.put("N", new ArrayList<String>());
        hvemStemteHvad.put("O", new ArrayList<String>());
        hvemStemteHvad.put("P", new ArrayList<String>());
        hvemStemteHvad.put("Q", new ArrayList<String>());
        hvemStemteHvad.put("R", new ArrayList<String>());
        hvemStemteHvad.put("S", new ArrayList<String>());
        hvemStemteHvad.put("T", new ArrayList<String>());
        hvemStemteHvad.put("U", new ArrayList<String>());
        hvemStemteHvad.put("V", new ArrayList<String>());
        hvemStemteHvad.put("W", new ArrayList<String>());
        hvemStemteHvad.put("X", new ArrayList<String>());
        hvemStemteHvad.put("Y", new ArrayList<String>());
        hvemStemteHvad.put("Z", new ArrayList<String>());

    }


    //RMILobby metoder
    public void join(String userID, GWTStub stub) {
        nyeSpillere = true;
        spillere.put(userID, stub);
    }
    
    public void gætBogstav(String bogstav, String userID) {
        if (bogstav.length() > 1) {
        } else {
            Integer value = stemmer.get(bogstav);
            stemmer.put(bogstav, value + 1);
            System.out.println(stemmer.get(bogstav));
        }
    }
    
    //Deler
    
    public String getSynligtOrd() {
        return synligtOrd;
    }

    //Lobby tråd metoder
    public HashMap<String, Integer> getVotes() {
        return stemmer;
    }

    public HashMap<String, ArrayList<String>> getVoters() {
        return hvemStemteHvad;
    }

    public String getOrdet() {
        return ordet;
    }

    public boolean nyeSpillere() {
        return nyeSpillere;
    }
    
    public HashMap<String, GWTStub> getSpillere() {
        return spillere;
    }

}
