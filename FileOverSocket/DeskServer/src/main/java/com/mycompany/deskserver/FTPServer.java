/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deskserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Westy
 * on create FTPserver
        1. SM.updateSettingFileName.
        2.SM.saveSetting(new Setting).
        3.SM.getSetting - Communicates with other classes.
 */
public class FTPServer {
    private boolean connect = false;
    private final int Connections = 3;
    public SettingManager SM = new SettingManager();
    
    public ArrayList<String> Messages = new ArrayList();
    
    private ServerSocket SerSocket;
    private Socket Socket;
    private final ExecutorService pool = Executors.newFixedThreadPool(Connections);
    
    private final ArrayList<ClientHandler> Clients = new ArrayList();
    public final ArrayList<String> ClientsNames = new ArrayList();
    
    public Setting getSettings(String context){
        return this.SM.getSetting(context);
    }
    
    public void start(){
        this.connect = true;
        new Thread(new Runnable(){
            public void run(){
                try {
                    showMessage("Starting server");
                    SerSocket = new ServerSocket(getSettings(FTPServer.this.getClass().
                            toGenericString() + " start.run").getPort());
                    while(FTPServer.this.connect){
                        showMessage("1. Waiting for connections at Port:" + SerSocket.getLocalPort() + ".");
                        Socket = SerSocket.accept();
                        showMessage("2. Client connected.");
                        ClientHandler newClient = new ClientHandler(Socket,FTPServer.this.getSettings(FTPServer.this.getClass().
                                toGenericString() + "start.run"));
                        Clients.add(newClient);
                        pool.execute(newClient);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FTPServer.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    FTPServer.this.connect = false;
                    end();
                    
                }
            }
        }).start();
    }
    
    public boolean isConnected(){
        return connect;
    }
    
    public void end(){
        showMessage("Closing server...");
        try {
            SerSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(FTPServer.class.getName()).log(Level.SEVERE, null, ex);
            showMessage("Could not close server due to "
                    + ex.getLocalizedMessage());
        }
        showMessage("Server closed.");
    }
    
    public void showMessage(String Message){
        Messages.add(Message);
    }
    
    public ArrayList<String> getMessage(){
        return this.Messages;
    }
    
    private int nothing(){
        return 0;
    }
    
}

