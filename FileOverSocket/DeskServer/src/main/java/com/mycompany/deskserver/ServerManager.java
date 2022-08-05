/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deskserver;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Westy
 */
public class ServerManager {
    
    /*
        resposible for retreiving servers previously added by the user
        using settings for each server.
    */
    
    //where server settings are stored.
    //use: settingSM knows where the setting directory is.
    private SettingManager settingSM;
    
    public ServerManager(SettingManager settingSM){
        this.settingSM = settingSM;
    }
    
    public ArrayList<FTPServer> getServers(){
        //return servers based on each setting
        ArrayList<FTPServer> Servers = new ArrayList();
        //from main Settingfolder get all sub files.
        File SettingFolder = settingSM.getSettingFolder();
        File[] subFiles = SettingFolder.listFiles();
        if(subFiles.length>0){
            for(File File: subFiles){
                FTPServer savedServer = new FTPServer();
                
                //if server names are changed_ a null pointer will occur_ as the
                //file that associates with cannot be found using the serverName
                System.out.println("Test 1");
                
                savedServer.SM.updateSettingFileName(File.getName());
                Servers.add(savedServer);
                System.out.println("Test 2");
            }
            return Servers;
        }
        return null;
    }
    
}
