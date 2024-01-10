package com.example;
import java.io.*;
import java.net.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Client {

    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miSocket;
    String strRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDaServer;
    

    public Socket connetti(){
        try {

            miSocket = new Socket(nomeServer,portaServer);
            outVersoServer = new DataOutputStream(miSocket.getOutputStream());
            inDaServer = new BufferedReader(new InputStreamReader(miSocket.getInputStream()));

        } catch (UnknownHostException e) {
            // TODO: handle exception
            System.err.println("host sconosciuto");
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("errore durante la connesione ");
            System.exit(1);
        }
        return miSocket;
    }

   

    public void comunica(){

        try {
            String msg_xml = inDaServer.readLine();
            XmlMapper mapper = new XmlMapper();
            Persona p = mapper.readValue(msg_xml,Persona.class);
            System.out.println(p.toString());
            
            String msg_JSON = inDaServer.readLine();
            ObjectMapper jsonMapper = new ObjectMapper();
            Persona p2 = jsonMapper.readValue(msg_JSON,Persona.class);
            System.out.println(p2.toString());

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.out.println("errore durante la connesione con il server!");
            System.exit(1);
        }
    }

    public void chiudiConnessione() {

        try {
            if (miSocket != null && !miSocket.isClosed()) {
                miSocket.close();
                System.out.println("Connessione chiusa.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
