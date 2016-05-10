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

    int time;

    public CountdownClass(int time) {
        this.time = time;

    }

    public int getTime() {
        return time;
    }

    public void resetTime(int timerReset) {
        time = timerReset;
    }

    @Override
    public void run() {
        while (true) {
            for (; time > 0; time--) {
                System.out.println(time);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CountdownClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
