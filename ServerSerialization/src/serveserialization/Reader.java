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


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import sun.misc.BASE64Encoder;

/**
 *
 * @author Rickson
 */
public class Reader 
{ 
    public void encryptCBC (String str) {
        
        String strDataToEncrypt = new String();
	String strCipherText = new String();
	String strDecryptedText = new String();
        
        try {
 
		    KeyGenerator keyGen = KeyGenerator.getInstance("DES");
                    SecretKey secretKey = keyGen.generateKey();
 
		    // Create the cipher 
		    //desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                    Cipher desCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
 
		    // Initialize the cipher for encryption
		    desCipher.init(Cipher.ENCRYPT_MODE,secretKey);
 
		    //sensitive information
		    //byte[] text = "No body can see me".getBytes();
                    strDataToEncrypt = str;
                    byte[] byteDataToEncrypt = strDataToEncrypt.getBytes();
                    byte[] byteCipherText = desCipher.doFinal(byteDataToEncrypt);
                    strCipherText = new BASE64Encoder().encode(byteCipherText);
 
		    System.out.println("Hasil enkripsi dengan DES CBC : " +strCipherText);
 
 
		    // Initialize the same cipher for decryption
		    desCipher.init(Cipher.DECRYPT_MODE,secretKey,desCipher.getParameters());
 
		    // Decrypt the text
		    byte[] byteDecryptedText = desCipher.doFinal(byteCipherText);
                    
                    strDecryptedText = new String(byteDecryptedText);
 
		    System.out.println("Hasil teks setelah didekripsi : " +strDecryptedText);
 
		}catch (NoSuchAlgorithmException noSuchAlgo)
		{
			System.out.println(" No Such Algorithm exists " + noSuchAlgo);
		}
		
			catch (NoSuchPaddingException noSuchPad)
			{
				System.out.println(" No Such Padding exists " + noSuchPad);
			}
		
				catch (InvalidKeyException invalidKey)
				{
					System.out.println(" Invalid Key " + invalidKey);
				}
				
				catch (BadPaddingException badPadding)
				{
					System.out.println(" Bad Padding " + badPadding);
				}
				
				catch (IllegalBlockSizeException illegalBlockSize)
				{
					System.out.println(" Illegal Block Size " + illegalBlockSize);
				}
				
				catch (InvalidAlgorithmParameterException invalidParam)
				{
					System.out.println(" Invalid Parameter " + invalidParam);
				}
	}
    }

  
