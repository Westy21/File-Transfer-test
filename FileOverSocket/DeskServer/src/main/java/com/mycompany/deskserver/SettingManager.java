/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deskserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Westy
 */
public class SettingManager{    
    private final String SettingFolderName = "Server";
    private final File SettingFolder = new File(SettingFolderName);
    
    private String SettingFileName;
    private File SettingFile;
    
    private Setting cachedSetting;
    
    public File getSettingFolder(){
        //create folder if not exist
        if(!SettingFolder.exists()){
            SettingFolder.mkdir();
        }
        return this.SettingFolder;
    }
    
    private boolean filenameUpdated = false;
    
    public void updateSettingFileName(String SettingFileName){
        //called first
        //must be called first before all by the addServerUI.class
            //addServer event
        this.SettingFileName = SettingFileName;
        SettingFile = new File(SettingFolderName+"\\"+SettingFileName);
        buildFileTree();
        filenameUpdated = true;
    }
    
    private void buildFileTree(){
        //second method to be called, called after updateSettingName
        //build Setting fileTree_For first time use
        if(SettingFolder.exists()){
            if(!SettingFile.exists()){
                try {
                    SettingFile.createNewFile();
                    saveSetting(new Setting());
                } catch (IOException ex) {
                    Logger.getLogger(SettingManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            SettingFolder.mkdir();
            try {
                SettingFile.createNewFile();
                saveSetting(new Setting());
            } catch (IOException ex) {
                Logger.getLogger(SettingManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void settingFileValid(){
        if(this.SettingFileName == null){
            System.out.println("SettingFileName is null");
        }
    }
    
    public Setting getSetting(String context){
        
        if(!filenameUpdated){
            System.out.println("Filename not updated." + context);
            settingFileValid();
        }
        
        Setting savedSetting = null;
        //read from a file if no knew settings
        if(cachedSetting == null){
            System.out.println("getSetting() : Reading file...");
            try {
                    //load settings from Directory
                    //for first time users_No settings exist therefore use default values from Settings class
                    FileInputStream FIS = new FileInputStream(SettingFile);
                    ObjectInputStream OIS = new ObjectInputStream(FIS);
                    savedSetting = (Setting)OIS.readObject();
                    OIS.close();
                    FIS.close();
                    return savedSetting;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SettingManager.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Failed to locate file from" + SettingFile.getName());
                } catch (IOException ex) {
                    Logger.getLogger(SettingManager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SettingManager.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Failed to cast class(Setting)!");
                } finally{
                    //a variable to keep newSettings near instead of reading files all the tine
                    this.cachedSetting = savedSetting;
                }
        }else{
            System.out.println("getSetting() : cache Settings...");
            return cachedSetting;
        }
        System.out.println("Failed to getSetting_ from File");
        return null;
    }
    
    public void saveSetting(Setting newSetting){
        //second
        try {
            System.out.println("saveSetting");
            //override existing settings in directory with new settings
            FileOutputStream FOS = new FileOutputStream(SettingFile);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(newSetting);
            OOS.flush();
            OOS.close();
            FOS.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SettingManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SettingManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //a variable to keep newSettings near instead of reading files all the tine
            this.cachedSetting = newSetting;
        }
    }
        
}
