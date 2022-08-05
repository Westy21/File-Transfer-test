/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deskserver;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Westy
 */
public class Main {
    
    public static void main(String[] args) throws IOException{
        System.out.println("Hello world.. it's westy");
        ServerUI ui = new ServerUI();
        ui.show(); 
    }
}