package com.jengle.sftp;

import java.io.FileOutputStream;  
import java.io.IOException;  
import java.net.SocketException;  
import org.apache.commons.net.ftp.FTPClient;  

public class FTPget { 

    // set global variables
    public static String FTPhost = "191.121.122.111";
    public static String userName = "username"; 
    public static String userPassword = "password";

    public static void main(String[] args) { 

	 // get an ftpClient object  
	  FTPClient ftpClient = new FTPClient();  
	  FileOutputStream fos = null;  
	  
	  try {  
	   // pass directory path on server to connect  
	   ftpClient.connect(FTPhost);  
	  
	   // pass username and password, returned true if authentication is  
	   boolean login = ftpClient.login(userName, userPassword);  
	  
	   // authentication successful, proceed to download all files in directory  
	   if (login) {  
	    System.out.println("Connection established...");  
	    
		// get list of files in remote directory
	    String[] files = ftpClient.listNames();
	    if (files != null && files.length > 0) {
	        for (String aFile: files) {
	    	    fos = new FileOutputStream(aFile);  
	    	    boolean download = ftpClient.retrieveFile(aFile, fos);
	    	    if (!download) {
	    	    	System.out.println("Error downloading file: " + aFile);
	    	    }
	            //System.out.println(aFile);
	        }
	    }
	    
	    // logout the user, returned true if logout successfully  
	    boolean logout = ftpClient.logout();  
	    if (logout) {  
	     System.out.println("Connection close...");  
	    }  
	   } else {  
	    System.out.println("Connection fail...");  
	   }  
	  
	  } catch (SocketException e) {  
	   e.printStackTrace();  
	  } catch (IOException e) {  
	   e.printStackTrace();  
	  } finally {  
	   try {  
	    ftpClient.disconnect();  
	   } catch (IOException e) {  
	    e.printStackTrace();  
	   }  
	  }  

	} 
}