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
public class MainClient {
    public static void main(String[] args){
        new Thread(new Client("127.0.0.1", 2021)).start();
    }
}
