/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deskserver;

import java.io.Serializable;

/**
 *
 * @author Westy
 */
public class Setting implements Serializable{
    
    private String Name = "null";
    private String ID = "null";
    
    //default
    private int Port = 2021;
    
    
    public Setting(){
        
    }
    
    public void setName(String Name){
        this.Name = Name;
    }
    
    public String getName(){
        if(Name == null){
            return "null";
        }else{
            return this.Name;
        }
    }
    
    public void setID(String ID){
        this.ID = ID;
    }
    
    public String getID(){
        if(ID == null){
            return "null";
        }else{
            return this.ID;
        }
    }
    
    public void setPort(int Port){
        this.Port=Port;
    }
    
    public int getPort(){
        return this.Port;
    }
    
}
