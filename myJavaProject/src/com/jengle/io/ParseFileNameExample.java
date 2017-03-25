package com.jengle.io;

import java.io.File; 

import org.apache.commons.io.FilenameUtils;

public class ParseFileNameExample 
{

	public static void main(String[] args) 
	{
		File file = new File("testing.txt");
		
		// example of parsing the file name into one string
		//  and the extension into another string.
		String basename = FilenameUtils.getBaseName(file.getName());
		String extension = FilenameUtils.getExtension(file.getName());
		File baseFile = new File(basename + ".trg");
		System.out.println("original file name: " + file);
		System.out.println("original file extension: " + extension);
		System.out.println("new file name: " + baseFile);
	}

}
