package com.jengle.sftp;

import java.io.File; 
import java.io.FileInputStream;

import com.jcraft.jsch.Channel; 
import com.jcraft.jsch.ChannelSftp; 
import com.jcraft.jsch.JSch; 
import com.jcraft.jsch.Session; 

public class SecureFTPExample { 

    // set global variables
    public static String SFTPHOST = "191.121.122.111";
    public static int SFTPPORT = 22;
    public static String SFTPUSER = "username"; 
    public static String SFTPPASS = "password";
    public static String SFTPWORKINGDIR = "/";
    public static String FILETOTRANSFER;

public static void main(String[] args) { 

	SFTPHOST = args[0];
	SFTPUSER = args[1];
	SFTPPASS = args[2];
	SFTPWORKINGDIR = args[3];
	FILETOTRANSFER = args[4];

	Session session = null; 
	Channel channel = null; 
	ChannelSftp channelSftp = null; 
	
	try{ 
		JSch jsch = new JSch(); 
		session = jsch.getSession(SFTPUSER,SFTPHOST,SFTPPORT); 
		session.setPassword(SFTPPASS); 
		java.util.Properties config = new java.util.Properties(); 
		config.put("StrictHostKeyChecking", "no"); 
		session.setConfig(config); 
		session.connect(); 
		channel = session.openChannel("sftp"); 
		channel.connect(); 
		channelSftp = (ChannelSftp)channel; 
		channelSftp.cd(SFTPWORKINGDIR);
		File f = new File(FILETOTRANSFER); 
		channelSftp.put(new FileInputStream(f), f.getName()); 
		channelSftp.disconnect();
		session.disconnect();
		//System.out.println("File has been transferred.");
	}catch(Exception ex){ 
		ex.printStackTrace(); 
	}
	} 
}