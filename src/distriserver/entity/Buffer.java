/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.entity;

import java.util.ArrayList;

/**
 *
 * @author Frederik
 */
public class Buffer {

    ArrayList<String> positive;
    ArrayList<String> negative;

    public Buffer() {
        positive = new ArrayList<String>();
        negative = new ArrayList<String>();
    }

    public void addNegativeVote(String userID) {
        negative.add(userID);
    }

    public ArrayList<String> getNegativeVote() {
        return negative;

    }

    public void addPositiveVote(String userID) {
        positive.add(userID);
    }

    public ArrayList<String> getPositiveVote() {
        return positive;
    }

    public boolean anyPositive() {
        if (positive.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean anyNegative() {
        if (negative.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
