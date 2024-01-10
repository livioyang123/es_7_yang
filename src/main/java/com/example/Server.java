package com.example;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Server {
    public  void avvioServer(){
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(6789);
            System.out.println("Server in attesa di connessioni...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket));
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    
    
  
    @Override
    public void run() {
        try (
            BufferedReader inDaClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outVersoClient = new DataOutputStream(clientSocket.getOutputStream());
        ) {
            Persona p = new Persona("A","B",12);

            Persona p2 = new Persona("livio","B",12);


            XmlMapper mapper = new XmlMapper();//xml
            ObjectMapper jsonMapper = new ObjectMapper(); //JSon

            String msg_xml = mapper.writeValueAsString(p);
            String msg_JSON = jsonMapper.writeValueAsString(p2);
            outVersoClient.writeBytes(msg_xml+"\n");
            outVersoClient.writeBytes(msg_JSON+"\n");
            
            System.out.println(p.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }
