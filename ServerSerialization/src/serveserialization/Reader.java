/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveserialization;
 
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import messageserialization.Message;
import java.io.*;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Rickson
 */
public class Reader 
{
    /*public static String initialRead()
    {
        //Z means: "The end of the input but for the final terminator, if any"
        String temp = null;    
        try 
        {
            String output = new Scanner(new File("file.txt")).useDelimiter("\\Z").next();
            temp="" + output;
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return temp;
        
    }
    
    public String readAll()
    {
        String temp = null;
        temp = initialRead();
        return temp;
    }
    

    public String readDay( String day) 
    {
        String temp,message = null;
        int i;
        temp = initialRead();
        String perLine [] = temp.split("\n");
        for(i=0;i<perLine.length;i++)
        {
            if(perLine[i].indexOf(day) != -1)
            {
                message = perLine[i];
            }
        }
        return message;
    }*/
    
    public void encryptCBC (String str) {
        try {
 
		    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		    SecretKey myDesKey = keygenerator.generateKey();
 
		    Cipher desCipher;
 
		    // Create the cipher 
		    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                    //desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
 
		    // Initialize the cipher for encryption
		    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
 
		    //sensitive information
		    //byte[] text = "No body can see me".getBytes();
                    byte[] text = str.getBytes();
 
		    System.out.println("Text [Byte Format] : " + text);
		    System.out.println("Text : " + new String(text));
 
		    // Encrypt the text
		    byte[] textEncrypted = desCipher.doFinal(text);
 
		    System.out.println("Text Encryted : " + textEncrypted);
 
		    // Initialize the same cipher for decryption
		    desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
 
		    // Decrypt the text
		    byte[] textDecrypted = desCipher.doFinal(textEncrypted);
 
		    System.out.println("Text Decryted : " + new String(textDecrypted));
 
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(NoSuchPaddingException e){
			e.printStackTrace();
		}catch(InvalidKeyException e){
			e.printStackTrace();
		}catch(IllegalBlockSizeException e){
			e.printStackTrace();
		}catch(BadPaddingException e){
			e.printStackTrace();
		} 
        }
    }

  
