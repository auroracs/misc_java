package com.jengle.log4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class log4jExample
{
	final static Logger logger = Logger.getLogger(log4jExample.class);
	
	public static void main(String[] args) {
	
		// initialize the LOG4J log
		try {
			PropertyConfigurator.configure(initLogger());
		} catch (IOException e1) {
			System.out.println("ERROR: IOException trying to initialize LOG4J! Trace: " + readStackTrace(e1));
		}

		log4jExample obj = new log4jExample();
		obj.runMe("log4j_test");
		
	}
	
	private void runMe(String parameter){
		
		if(logger.isDebugEnabled()){
			logger.debug("This is debug : " + parameter);
		}
		
		if(logger.isInfoEnabled()){
			logger.info("This is info : " + parameter);
		}
		
		logger.warn("This is warn : " + parameter);
		logger.error("This is error : " + parameter);
		logger.fatal("This is fatal : " + parameter);
		
	}

	private static Properties initLogger() throws IOException {
		final File logsDir;

		// create the log folder if doesn't exists
		if (System.getProperty("os.name").contains("Windows")) {
			logsDir = new File("C:/log4j_logs");
			if (!logsDir.exists()) {
				logsDir.mkdir();
			}
		} else {
			logsDir = new File("/log4j_logs");
			if (!logsDir.exists()) {
				logsDir.mkdir();
			}
		}

		final File ParseLog = new File(logsDir.getCanonicalFile() + "/log4jExample.log");
		if (!ParseLog.exists()) {
			ParseLog.createNewFile();
		}
		final Properties prpsLog = new Properties();
		// Create file and email loggers
		String appenderFile = "";
		if (System.getProperty("os.name").contains("Windows")) {
			appenderFile = ParseLog.getCanonicalPath().replace("\\", "/");
		} else {
			appenderFile = ParseLog.getCanonicalPath();
		}

		final String cfgLog = "log4j.rootLogger=ALL, file\n"
				+ "log4j.appender.file=org.apache.log4j.RollingFileAppender\n"
				+ "log4j.appender.file.MaxFileSize=2024KB\n" 
				+ "log4j.appender.file.MaxBackupIndex=15\n"
				+ "log4j.appender.file.BufferSize=10\n" 
				+ "log4j.appender.file.Threshold=info\n"
				+ "log4j.appender.file.layout=org.apache.log4j.PatternLayout\n" 
				+ "log4j.appender.file.File="
				+ appenderFile + "\n"
				+ "log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} : %-40c : %-5p %x - %m %n\n";

		final ByteArrayInputStream byteCfg = new ByteArrayInputStream(cfgLog.getBytes());
		prpsLog.load(byteCfg);

		// Return log4j Properties
		return prpsLog;
	}

	static String readStackTrace(final Throwable e) {
		final StringBuilder result = new StringBuilder(e.toString() + "\n");

		// add each element of the stack trace
		for (StackTraceElement element : e.getStackTrace()) {
			result.append(element);
			result.append("\n");
		}
		final String returnString = result.toString();

		return returnString;
	}

}