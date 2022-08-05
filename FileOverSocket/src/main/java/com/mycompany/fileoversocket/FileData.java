/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileoversocket;

import java.io.Serializable;

/**
 *
 * @author Westy
 */
public class FileData implements Serializable{
    
    public String Name;
    public byte[] Data;
    
    public FileData(String Name, byte[] Data){
        this.Name = Name;
        this.Data = Data;
    }
    
}
