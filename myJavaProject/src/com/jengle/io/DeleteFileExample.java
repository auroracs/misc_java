package com.jengle.io;

import java.io.File;

public class DeleteFileExample {

	public static void main(String[] args) throws Exception {

		try {

			File file = new File("/amazon/signedURL.txt");

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
