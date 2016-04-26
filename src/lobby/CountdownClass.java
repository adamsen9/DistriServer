/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FrederikSwag
 */
public class CountdownClass implements Runnable {

    String stringToUpdate;
    int time;

    public void countDown(String strinToUpdate, int time) {
        this.stringToUpdate = strinToUpdate;
        this.time = time;

    }

    @Override
    public void run() {
        for (; time >= 0; time--) {
            try {
                stringToUpdate = Integer.toString(time);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CountdownClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
