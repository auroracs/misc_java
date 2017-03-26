package com.jengle.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args) {

		try {

			String serverIP = "111.111.111.111"; // remote server address
			int serverPort = 16; // remote server PORT

			// create socket connection to remote server
			Socket client = new Socket(serverIP, serverPort);

			// setup output to server
			PrintWriter out = new PrintWriter(client.getOutputStream());

			// get system info (FedEx server in this example)
			out.print("0,\"050\"1,\"System Information and Control\"1989,\"04\"99,\"\"");

			out.flush();

			// setup to receive response from server
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

			String response = ""; // holds the response from the server
			int value = 0;
			// char resValue;

			// Doesn't send a carriage return so .read() will block reads to the
			// end of the stream
			while ((value = br.read()) != -1) {

				// zero indicate the end of the string from server
				if (value == 0) {
					break;
				}

				// converts int to character and appends to response string
				response += (char) value;
				// resValue = (char) value;
				// logger.info("string version of value: " + resValue);

			}

			if (response.equalsIgnoreCase(" ")) {
				System.out.println("BLANK Data from server ");
			} else {
				System.out.println("response: " + response);
			}

			client.close();

		} catch (UnknownHostException e) {
			System.out.println("Unknown Host Exception! Trace: " + readStackTrace(e));
		} catch (IOException e) {
			System.out.println("IOException! Trace: " + readStackTrace(e));
		}

	}

	private static String readStackTrace(final Throwable e) {
		final StringBuilder result = new StringBuilder(e.toString() + "\n>");
		for (StackTraceElement element : e.getStackTrace()) {
			result.append(element);
			result.append("\n");
		}
		return result.toString();
	}

}
