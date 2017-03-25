package com.jengle.sftp;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.commons.io.FilenameUtils;

import com.jcraft.jsch.Channel; 
import com.jcraft.jsch.ChannelSftp; 
import com.jcraft.jsch.JSch; 
import com.jcraft.jsch.Session; 

public class SFTPsend 
{ 

	private static final Logger LOGGER = Logger.getLogger(SFTPsend.class);
	
	// set global variables
    public static String SFTPHOST = "ftp.domainname.com";
    public static int SFTPPORT = 22;
    public static String SFTPUSER = "username"; 
    public static String SFTPPASS = "password";
    public static String SFTPWORKINGDIR = "remote/working/folder/";

	public static void main(String[] args) 
	{ 
		String response = sendLocalFiles();
		System.out.println("Response: " + response);
	}

	public static String sendLocalFiles()
	{
		try
		{ 
			// Initialize the logger
			PropertyConfigurator.configure(initLogger());

			Session session = null; 
			Channel channel = null; 
			ChannelSftp channelSftp = null; 
			int fileCount = 0;
			
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
			
			// local IFS directory
			File f = new File("c:/users/jim/documents/testing/");
		    File[] files  = f.listFiles();

		    if(files != null)
		    for(int i=0; i < files.length; i++)
		    {
		        fileCount++;
		        File file = files[i];
		        
		        // if not a directory, send it
		        if(!file.isDirectory()) 
		        {   
		        	// send file to remote folder
					channelSftp.put(new FileInputStream(file), file.getName());
					
					// build empty trigger file, named after file just sent except using ".trg" extension
					String basename = FilenameUtils.getBaseName(file.getName());
					File baseFile = new File(basename + ".trg");

					// now create the trigger file
		            FileOutputStream is = new FileOutputStream(baseFile);
		            OutputStreamWriter osw = new OutputStreamWriter(is);    
		            BufferedWriter w = new BufferedWriter(osw);
		            
		            w.write(" ");
		            w.newLine();
		            
		            w.close();
		            osw.close();
		            is.close();
		            

		        	// send empty trigger file to remote folder
					channelSftp.put(new FileInputStream(baseFile), baseFile.getName());
					
	                // Delete file from local directory
	                file.delete(); 
		        }
		     }


			LOGGER.info("Files sent: " + new Integer(fileCount).toString() + "\n");
			
			channelSftp.disconnect();
			session.disconnect();
			
			String rtnMessage;
			
			if (fileCount == 0)
			{
				rtnMessage = "EMPTY! There are no files to download at this time.\n";
			}
			else 
			{
				rtnMessage = "SUCCESS! Files have been placed on the local drive.\n";
			}
			LOGGER.info(rtnMessage);
			return rtnMessage;
		}
		catch(Exception e)
		{ 
			LOGGER.error(readStackTrace(e));
			LOGGER.error("FAILED TO RUN method sendLocalFiles()!\n");
			return "FAILED TO RUN method sendLocalFiles()!\n";
		}
}
	
private static Properties initLogger() throws IOException
{
	final File logsDir;

	if (System.getProperty("os.name").contains("Windows"))
	{
		logsDir = new File("C:/log4j_logs");
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
			+ SFTPsend.class.getSimpleName() + ".log");
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