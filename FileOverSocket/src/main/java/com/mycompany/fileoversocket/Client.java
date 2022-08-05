/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileoversocket;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Westy
 */
public class Client implements Runnable{
    private final int Port;
    private final String Address;
    private Socket Socket;
    
    private ObjectOutputStream OOS;
    private FileInputStream FIS;
    private DataInputStream DIS;
    
    public Client(String Address, int Port){
        this.Port = Port;
        this.Address = Address;
    }
    
    public void connect() throws InterruptedException{
        System.out.println("Connecting to server...");
        
        try {
            Socket = new Socket(Address,Port);
            System.out.println("Connection successfull...");
            System.out.println("loading files to sent...");
            OOS = new ObjectOutputStream(Socket.getOutputStream());
            FIS = new FileInputStream("catchme.mp3");
            System.out.println("Sending files...");
            OOS.writeObject(new FileData("catchme.mp3", FIS.readAllBytes()));
            //Thread.sleep(5000);
            System.out.println("Files sent...");
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                System.out.println("closing connection...");
                FIS.close();
                OOS.close();
                Socket.close();
                System.out.println("Client is offline...");
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void run() {
        try {
            connect();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
