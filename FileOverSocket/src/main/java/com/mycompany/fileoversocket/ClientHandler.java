/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileoversocket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Westy
 */
public class ClientHandler implements Runnable{
    
    private final Socket Socket;
    private ObjectInputStream OIS;
    
    public ClientHandler(Socket Socket){
        this.Socket = Socket;
    }
    
    @Override
    public void run() {
        try{
            OIS = new ObjectInputStream(Socket.getInputStream());
            FileData sentFileData;
            while((sentFileData=(FileData)OIS.readObject()) != null){
                System.out.println("Receiving client data...");
                System.out.println("Send File:"
                        + "     Name: " + sentFileData.Name
                        + "     Data: " + sentFileData.Data.toString());
                File newFile = new File("SentFile.mp3");
                newFile.createNewFile();
                FileOutputStream FOS = new FileOutputStream(newFile);
                FOS.write(sentFileData.Data);
                FOS.close();
            }
        }catch(Exception Ex){
            System.out.println("Error occured during listining " + Ex.getMessage());
        }finally{
            try {
                OIS.close();
                Socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
