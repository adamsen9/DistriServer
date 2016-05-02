/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.entity;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Frederik
 */
public class BufferThread implements Runnable {

    DAL dal;
    Buffer buffer;
    ArrayList<String> list;
    String tmp;

    public BufferThread(DAL dal,Buffer buffer) {
        this.buffer = buffer;
        this.dal = dal;
    }

    public Buffer getBuffer() {
        return buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                if (buffer.anyNegative()) {
                    list = buffer.getNegativeVote();
                    for (String str : list) {
                        dal.addNegativeVote(str);
                        list.remove(str);
                    }

                }

                if (buffer.anyPositive()) {
                    list = buffer.getPositiveVote();
                    for (String str : list) {
                        dal.addPositiveVote(str);
                        list.remove(str);
                    }
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
