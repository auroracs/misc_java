package com.jengle.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteFileExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

        try {
        	
            FileOutputStream is = new FileOutputStream("/amazon/namefile.txt"); // write *replace file data
            FileOutputStream os = new FileOutputStream("/amazon/namefile.txt", true); // write *append file data
            
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            BufferedWriter w = new BufferedWriter(osw);
            
            w.write("First Name: Jim Engle");
            w.newLine();
            w.write("Last Name: Engle");
            w.newLine();
            
            if (w != null) {  // check if open otherwise the close() will blow trying to close a file that's already closed
            	w.close();
            }
            osw.close();
            os.close();
            is.close();
            
        } catch (IOException e) {
            System.err.println("Problem writing to the file namefile.txt");
        }

	}

}
