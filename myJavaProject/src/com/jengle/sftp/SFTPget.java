package com.jengle.sftp;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.jcraft.jsch.Channel; 
import com.jcraft.jsch.ChannelSftp; 
import com.jcraft.jsch.JSch; 
import com.jcraft.jsch.Session; 

public class SFTPget { 

	private static final Logger LOGGER = Logger.getLogger(SFTPget.class);
	
    // set global variables
    public static String SFTPHOST = "211.141.151.111";
    public static int SFTPPORT = 22;
    public static String SFTPUSER = "username"; 
    public static String SFTPPASS = "password";
    public static String SFTPWORKINGDIR = "outbound/";

public static void main(String[] args) { 

	String response = getRemoteFile();
	System.out.println("Response: " + response);
	
	} 

public static String getRemoteFile()
{
	try
	{
		// Initialize the logger
		PropertyConfigurator.configure(initLogger());

		Session session = null; 
		Channel channel = null; 
		ChannelSftp channelSftp = null; 
		
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
		int fileCount = 0;
		
		// get list of remote directory
		@SuppressWarnings("unchecked")
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls(".");
			
		// iterate through objects in list, identifying specific file names
		for (ChannelSftp.LsEntry oListItem : list) {

            // If it is a file (not a directory)
            if (!oListItem.getAttrs().isDir()) {

            	// Grab the remote file ([remote filename], [local path/filename to write file to])
            	String localFile = null;
            	localFile = "/remotefolder/in/" + oListItem.getFilename();
            	channelSftp.get(oListItem.getFilename(), localFile);
            	fileCount++; // count number of files

                // Delete remote file
                channelSftp.rm(oListItem.getFilename()); 
            }
        }
		
		LOGGER.info("File count: " + new Integer(fileCount).toString() + "\n");
		
		channelSftp.disconnect();
		session.disconnect();

		if (fileCount == 0)
		{
			LOGGER.info("EMPTY! There are no files to download at this time.\n");
			return "EMPTY! There are no files to download at this time.";
		}
		else 
		{
			LOGGER.info("SUCCESS! Files have been placed on the local drive.\n");
			return "SUCCESS! File placed on local drive";
		}
	}
	catch (Exception e)
	{
		LOGGER.error(readStackTrace(e));
		LOGGER.error("FAILED TO RUN method getRemoteFile()!\n");
		return "FAILED TO RUN method getRemoteFile()!\n";
	}
}

private static Properties initLogger() throws IOException
{
	final File logsDir;

	if (System.getProperty("os.name").contains("Windows"))
	{
		logsDir = new File("C:/SFTP/logs");
		if (!logsDir.exists())
		{
			logsDir.mkdir();
		}
	}
	else
	{
		logsDir = new File("/var/log");
		if (!logsDir.exists())
		{
			logsDir.mkdir();
		}
	}

	// Create a log file named after this java program
	final File lpLog = new File(logsDir.getCanonicalFile() + "/"
			+ SFTPget.class.getSimpleName() + ".log");
	if (!lpLog.exists())
	{
		lpLog.createNewFile();
	}
	final Properties prpsLog = new Properties();
	// Create file logger
	String appenderFile = "";
	if (System.getProperty("os.name").contains("Windows"))
	{
		appenderFile = lpLog.getCanonicalPath().replace("\\", "/");
	}
	else
	{
		appenderFile = lpLog.getCanonicalPath();
	}

	final String cfgLog = "log4j.rootLogger=ALL, file\n"
			+ "log4j.appender.file=org.apache.log4j.RollingFileAppender\n"
			+ "log4j.appender.file.MaxFileSize=1024KB\n"
			+ "log4j.appender.file.MaxBackupIndex=10\n"
			+ "log4j.appender.file.BufferSize=10\n"
			+ "log4j.appender.file.Threshold=info\n"
			+ "log4j.appender.file.layout=org.apache.log4j.PatternLayout\n"
			+ "log4j.appender.file.File="
			+ appenderFile
			+ "\n"
			+ "log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} : %-40c : %-5p %x - %m %n\n";

	final ByteArrayInputStream byteCfg = new ByteArrayInputStream(cfgLog.getBytes());
	prpsLog.load(byteCfg);

	// Return log4j Properties
	return prpsLog;
}

private static String readStackTrace(final Throwable e)
{
	final StringBuilder result = new StringBuilder(e.toString() + "\n>");

	// add each element of the stack trace
	for (StackTraceElement element : e.getStackTrace())
	{
		result.append(element);
		result.append("\n");
	}
	return result.toString();
}


}