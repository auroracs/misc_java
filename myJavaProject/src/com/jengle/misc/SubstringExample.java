package com.jengle.misc;

public class SubstringExample {

	public static void main(String[] args) {

		String Str = new String("Welcome to Treelinedata.com");

		// Extracts from position 11 to the end, 
		//  since no ending position was used
		
		// Return Value : Treelinedata.com
		System.out.print("Return Value : ");
		System.out.println(Str.substring(11));

		// Extracts from position 11 thru 15 
		
		// Return Value : Tree
		System.out.print("Return Value : ");
		if (Str.substring(0, 1).equals("W")) {
		System.out.println(Str.substring(1, 5));
		}
		//System.out.println(Str.substring(11, 15));
	}

}
