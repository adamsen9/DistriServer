/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.boundary;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author FrederikSwag
 */
public class GWTStubImpl implements GWTStub {

    @Override
    public void listeAfSpillere(ArrayList<String> liste) throws RemoteException {
        for (String str : liste) {
            System.out.println(str);
        }
    }

    @Override
    public void listeAfStemmer(HashMap<String, Integer> liste) throws RemoteException {
        Iterator it = liste.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.print((String) pair.getKey());
            System.out.println((String) pair.getValue());
        }

    }

    @Override
    public void synligtOrd(String str) throws RemoteException {
        System.out.println(str);
    }

    @Override
    public void resultatAfAfstemning(boolean bool) throws RemoteException {
        System.out.println(bool);
    }

    @Override
    public void sidstGættet(String str) throws RemoteException {
        System.out.println(str);
    }

}
