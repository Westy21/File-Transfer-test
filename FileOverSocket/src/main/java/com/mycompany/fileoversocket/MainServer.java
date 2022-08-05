/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileoversocket;

/**
 *
 * @author Westy
 */
public class MainServer {
    
    public static void main(String[] args){
        var Server = new Server(2021);
        Server.start();
    }
}
