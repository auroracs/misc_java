package com.jengle.log4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class log4jExample2 {

	final static Logger logger = Logger.getLogger(log4jExample2.class);

    public static void main(String[] args) {
    	
        try {
        	
			// set log4j properties
			PropertyConfigurator.configure(log4jExample2.initLogger());
	
			logger.info( "LOG4J example2 started...");

		} catch (IOException e) {
			logger.error(readStackTrace(e));
		}

    }
    
	/**
	 * Inits the logger.
	 *
	 * @return the properties
	 * @throws IOException
	 *              Signals that an I/O exception has occurred.
	 */
	private static Properties initLogger() throws IOException
	{
		final File logsDir;

		if (System.getProperty("os.name").contains("Windows"))
		{
			logsDir = new File("C:/ServerApps/logs");
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
		
		// Create a log file named after the class file
		final File log4jExample2Log = new File(
				logsDir.getCanonicalFile() + "/" + log4jExample2.class.getSimpleName() + ".log");
		if (!log4jExample2Log.exists())
		{
			log4jExample2Log.createNewFile();
		}
		final Properties prpsLog = new Properties();

		// Create file and email HANDLER_LOGs
		String log4jExample2LogFile = "";
		if (System.getProperty("os.name").contains("Windows"))
		{
			log4jExample2LogFile = log4jExample2Log.getCanonicalPath().replace("\\", "/");
		}
		else
		{
			log4jExample2LogFile = log4jExample2Log.getCanonicalPath();
		}
		final String cfgLog = "log4j.rootLogger=INFO, file\n"
				+ "log4j.appender.file=org.apache.log4j.RollingFileAppender\n"
				+ "log4j.appender.file.MaxFileSize=1024KB\n"
				+ "log4j.appender.file.MaxBackupIndex=10\n"
				+ "log4j.appender.file.BufferSize=10\n"
				+ "log4j.appender.file.Threshold=debug\n"
				+ "log4j.appender.file.layout=org.apache.log4j.PatternLayout\n"
				+ "log4j.appender.file.File="
				+ log4jExample2LogFile
				+ "\n"
				+ "log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} : %-50c : %-5p %x - %m %n\n";

		final ByteArrayInputStream byteCfg = new ByteArrayInputStream(
				cfgLog.getBytes());
		prpsLog.load(byteCfg);
		// Return log4j Properties

		return prpsLog;
	}

	/**
	 * Read stack trace.
	 * 
	 * @return the string
	 */
	private static String readStackTrace(final Throwable e) {
		final StringBuilder result = new StringBuilder(e.toString() + "\n>");

		// add each element of the stack trace
		for (StackTraceElement element : e.getStackTrace()) {
			result.append(element);
			result.append("\n");
		}
		return result.toString();
	}


}
