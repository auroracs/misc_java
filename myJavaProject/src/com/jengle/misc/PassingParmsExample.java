package com.jengle.misc;

public class PassingParmsExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// The parms is an array. The first parm is always element zero
		String FirstName = args[0]; // first parm in the array
		String LastName = args[1];  // second parm in the array
		String available = args[2]; // third parm in the array

		System.out.println("First Name: " + FirstName);
		System.out.println("Last Name: " + LastName);
		System.out.println("Available?: " + available);

	}

}
