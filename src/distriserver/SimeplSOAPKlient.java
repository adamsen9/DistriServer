/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver;

import brugerautorisation.transport.soap.Brugeradmin;
import distriserver.boundary.SOAPServerI;
import distriserver.boundary.SOAPServerImpl;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Frederik
 */
public class SimeplSOAPKlient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://54.191.78.231:9901/soapserverimpl?wsdl");

        QName qname = new QName("http://boundary.distriserver/", "SOAPServerImplService");

        Service service = Service.create(url, qname);
        
        SOAPServerI server = service.getPort(SOAPServerI.class);
        
        server.login("s123157", "clancbs");
    }

}
