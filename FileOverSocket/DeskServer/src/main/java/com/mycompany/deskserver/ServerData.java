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
public class ServerData implements Serializable{
    
    private ArrayList<ArrayList<Object>> FileTree;
    
    public void setFileTree(ArrayList<ArrayList<Object>> FileTree){
        this.FileTree = FileTree;
    }
    
    public ArrayList<ArrayList<Object>> getFileTree(){
        return this.FileTree;
    }
}
