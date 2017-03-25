package com.jengle.misc;

public class VariablesExample {

	// Global Variables... 
	public static String appName = "ListOrders";
	public static String appVersion = "2013_09_01";
    
	// Constant Variables...
	public final static String ALGORITHM = "HmacSHA256";
    public final static String DESTATTRMBRKEY = "sqsQueueUrl";

	public static void main(String[] args) {

		System.out.println("Global variable appName: " + appName);
		System.out.println("Global variable appVersion: " + appVersion);

		System.out.println("Constant variable ALGORITHM: " + ALGORITHM);
		System.out.println("Constant variable DESTATTRMBRKEY: " + DESTATTRMBRKEY);

	}

}
