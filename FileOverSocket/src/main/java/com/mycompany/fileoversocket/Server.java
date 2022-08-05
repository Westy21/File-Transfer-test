/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileoversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Westy
 */
public class Server {
    
    private final int Connections = 2;
    
    public int Port;
    
    private ServerSocket SerSocket;
    private Socket Socket;
    private final ExecutorService pool = Executors.newFixedThreadPool(Connections);
    
    private final ArrayList<ClientHandler> Clients = new ArrayList();
    
    public Server(int Port){
        this.Port = Port;
    }
    
    public void start(){
        try {
            SerSocket = new ServerSocket(Port);
            while(true){
                System.out.println("Waiting for client connection...");
                Socket = SerSocket.accept();
                System.out.println("New client Connected");
                var newClient = new ClientHandler(Socket);
                Clients.add(newClient);
                pool.execute(newClient);
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            end();
        }
    }
    
    public void end(){
        System.out.println("/**/Server going offline...");
        try {
            SerSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("/**/Server is offline...");
    }
    
}
