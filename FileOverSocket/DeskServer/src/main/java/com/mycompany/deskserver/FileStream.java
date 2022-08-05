/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deskserver;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Westy
 */
public class FileStream implements Serializable{
    // this object is used as a means of transport for file data across server and client
    
    private ArrayList<ArrayList<Object>> FileTreeData;
    
    private String FileName;
    private String ParentFolder;
    
    /*bytes of length  >1GB cannot be transfared through socket.
     *an object contain segments of those bytes cann be used in the future to allow transfere of 1GB files.
     *byte[] FileData is just for no
    */
    private byte[] FileData;
    private float FileSize;
    
    //used by client when requesting a file
    public void setFileName(String FileName){
        this.FileName = FileName;
    }
    
    public String getFileName(){
        return this.FileName;
    }
    
    public void setFileAttr(String ParentFolder,byte[] FileData){
        this.ParentFolder = ParentFolder;
        this.FileData = FileData;
    }
    
    public String getParentFolder(){
        return this.ParentFolder;
    }
    
    public byte[] getFileData(){
        return this.FileData;
    }
    
    public void setFileSize(){
        this.FileSize = FileSize;
    }
    
    public float getFileSize(){
        return this.FileSize;
    }
    
}
