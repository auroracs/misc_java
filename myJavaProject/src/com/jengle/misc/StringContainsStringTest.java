package com.jengle.misc;

import org.apache.commons.lang3.StringUtils;

public class StringContainsStringTest {

	public static void main(String[] args) throws Exception {

		String str = "This is the string to search";

		System.out.println("String to search: " + str);

		System.out.println("\nUsing String.contains():");
		System.out.println("str.contains(\"the\"): " + str.contains("the"));
		System.out.println("str.contains(\"The\"): " + str.contains("The"));

		System.out.println("\nUsing String.indexOf() processing:");
		System.out.println("(str.indexOf(\"the\") > -1 ? true : false): " + (str.indexOf("the") > -1 ? true : false));
		System.out.println("(str.indexOf(\"The\") > -1 ? true : false): " + (str.indexOf("The") > -1 ? true : false));

		int pos = str.indexOf("is the");
		System.out.println("Position: " + pos);
		
		System.out.println("\nUsing StringUtils.contains():");
		System.out.println("StringUtils.contains(str, \"the\"): " + StringUtils.contains(str, "the"));
		System.out.println("StringUtils.contains(str, \"The\"): " + StringUtils.contains(str, "The"));

	}
}
