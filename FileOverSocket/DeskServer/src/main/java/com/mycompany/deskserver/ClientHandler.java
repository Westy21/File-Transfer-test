/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deskserver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Westy
 */
public class ClientHandler extends FTPServer implements Runnable{
              
    private final Socket Socket;
    private ObjectInputStream OIS;
    private ObjectOutputStream OOS;
    private ServerData SD;
    
    private final FileManager FM;
    
    public ClientHandler(Socket Socket,Setting Setting) throws IOException{
        this.Socket = Socket;
        this. OOS = new ObjectOutputStream(Socket.getOutputStream());
        this.FM = new FileManager(Setting.getName().toLowerCase());//error_Null pointer.
        
        //sent client Server files.
        SD = new ServerData();
        SD.setFileTree(FM.getRootData());
        sentToClient(SD);
    }
    
    //sent Messanger
    private void sentToClient(Object Object){
        try {
            OOS.writeObject(Object);
            OOS.flush();
        } catch (IOException ex) {
            Logger.getLogger(
                    ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try{
            OIS = new ObjectInputStream(Socket.getInputStream());
            FileStream FileStream;
            while(true){
               if((FileStream=(FileStream)OIS.readObject()) !=null){
                   
                    if(FileStream.getFileData()==null){
                        //request_File
                        sentToClient(FM.deconstructFile(FM.getFile(FileStream.getFileName())));
                    }else if(FileStream.getFileData()!=null){
                        //store_File
                        FM.constructFile(FileStream);
                        SD = new ServerData();
                        SD.setFileTree(FM.getRootData());
                        sentToClient(SD);
                    }
                }
            }
        }catch(IOException ex){
            showMessage(" Client has disconnected.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException ex){
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int nothing(){
        return 0;
    }
}
