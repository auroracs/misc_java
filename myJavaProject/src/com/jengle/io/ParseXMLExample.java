package com.jengle.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ParseXMLExample {

	// Define global variables
	public static FileOutputStream is;
	public static OutputStreamWriter osw;
	public static BufferedWriter w;

	public static void main(String[] args) throws IOException {

		// set output XML file
		is = new FileOutputStream("c:\\java\\parsedfile.xml");
		osw = new OutputStreamWriter(is);
		w = new BufferedWriter(osw);

		// read in the record to be parsed
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("C:\\java\\test.xml"));
			while ((sCurrentLine = br.readLine()) != null) {
				// pass the element to write routine
				extractElements(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		// close files, we're done
		w.close();
		osw.close();
		is.close();

	}

	public static void extractElements(String data) {
		
		String match = "><";
		int y = 0;
		int i = 0;

		while ((i = (data.indexOf(match, i) + 1)) > 0) {
			writeElements(data.substring(y, i));
			y = i;
		}

	}

	public static void writeElements(String data) {

		try {
			w.write(data);
			w.newLine();
		} catch (IOException e) {
			System.err.println("Problem writing to the file parsedfile.xml");
		}

	}

}
