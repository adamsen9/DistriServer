/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver;

import brugerautorisation.transport.soap.Brugeradmin;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Frederik
 */
public class SimeplSOAPKlient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("localhost:9901/soapserverimpl?wsdl");

        QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");


        Service service = Service.create(url, qname);

        Brugeradmin ba = service.getPort(Brugeradmin.class);


    }

}
