/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveserialization;

import messageserialization.Message;
import java.net.*;
import java.io.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rah Desta
 */
public class ServeSerialization {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            /*
             * variable initialization
             */
            ServerSocket serverSocket;            
            Socket clientSocket; 
            ObjectInputStream inputStream;
            ObjectOutputStream outputStream; 
            /*
             * create socket server, accept client, preparing input stream
             * receive message, and print to screen
             */
            serverSocket = new ServerSocket(5111);
            while(true) {
                clientSocket = serverSocket.accept();            
                Connection c = new Connection(clientSocket);      
            }  
        }
        catch(IOException e) {
            System.out.println("Listen: " + e.getMessage());
        }        
    }
    
}

class Connection extends Thread {
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    Socket clientSocket;
    
    public Connection(Socket client) {
        try {
            clientSocket = client;
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());   
            this.start();
        }
        catch(IOException ex) {
            System.out.println("IO: " + ex.getMessage());
        }        
    }
    
    @Override
    public void run() {
        try {
            Message message;;
             /*
             * preparing output stream, send message back to client
             */
            
            message = (Message) inputStream.readObject();
            Reader file = new Reader();
            System.out.println("From client: " + message.getString());
            String str = message.getString();
            
            //outputStream.writeObject(new Message(file.encrypt(mee)));
            //System.out.println( file.encryptCBC(str));
            file.encryptCBC(str);
            
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}