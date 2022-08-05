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
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Westy
 */
public class FileManager{
    private final String DefaultMainFolder = "Desk";
    private final String DefaultFolder;
    private final ArrayList<String> Files = new ArrayList();
    
    private  ArrayList<ArrayList<Object>> Root;
    private ArrayList<Object>Node = new ArrayList();

    public FileManager(String DefaultFolder) throws IOException{
        this.DefaultFolder = DefaultMainFolder + "\\" + DefaultFolder;
        //load fileStructure
        reLoadFiles();
    }

    private void reLoadFiles(){
        Files.clear();
        loadFiles(new File(DefaultFolder));
    }
    
     //Populate Files Array<> -- Stores file paths
    public void loadFiles(File File){
        if(File.exists()){
            if(File.isDirectory()){
                File[] FileArray = File.listFiles();
                if(FileArray.length>0){
                    for(File subFile:FileArray){
                        loadFiles(subFile);
                    }
                }
            }else if(File.isFile()){
                Files.add(DefaultFolder +"\\"+ File.getParentFile().getName() +"\\"+File.getName());
            }
        }else{
            File.mkdirs();
        }
    }
    
    public ArrayList<ArrayList<Object>> getRootData(){
        Root = new ArrayList<ArrayList<Object>>();
        File File = new File(DefaultFolder);
        if(File.exists()){
            File[] subFiles = File.listFiles();
            if(subFiles.length>0){
                for(File subFile: subFiles){
                    Node = new ArrayList<Object>();
                    Node.add(subFile.getName());
                    File[] subFileFiles = subFile.listFiles();
                    if(subFileFiles.length>0){
                        for(File fileSubFile: subFileFiles){
                            Node.add(fileSubFile.getName());
                        }
                        Root.add(Node);
                    }
                }
            }
        }
        return Root;
    }
    
    public File getFile(String Filename){
        //Meke sure files are uptodate
        reLoadFiles();
        
        if(Filename != null){
            for(String File: Files){
                if(File.contains(Filename)){
                    return new File(File);
                }
            }
        }
        return null;
    }
    
    public FileStream deconstructFile(File File) throws FileNotFoundException, IOException{
        FileInputStream FIS = new FileInputStream(File);
        FileStream FileStream = new FileStream();
        FileStream.setFileName(File.getName());
        FileStream.setFileAttr(File.getParentFile().getName(),FIS.readAllBytes());
        FileStream.setFileSize();
        FIS.close();
        return FileStream;
    }
    
    public void constructFile(FileStream FileStream) throws IOException{
        //system for sorting files based on their file extentions
        File ParentFolder = new File(DefaultFolder+"\\"+FileStream.getParentFolder());
        if(ParentFolder.mkdir());
        
        File File = new File(DefaultFolder+"\\"+FileStream.getParentFolder()+"\\"+FileStream.getFileName());
        FileOutputStream FOS = new FileOutputStream(File);
        FOS.write(FileStream.getFileData());
        FOS.flush();
        FOS.close();
        if(!File.exists()){
            File.createNewFile();
        }
    }
    
    public void printFiles(){
        for(String Filename: Files){
            File File = new File(Filename);
            System.out.println(File.getName() + " : " + File.exists());
            
        }
    }
    
}
