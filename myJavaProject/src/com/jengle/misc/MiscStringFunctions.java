package com.jengle.misc;

public class MiscStringFunctions {

	public static String textFile = "";

	public static void main(String[] args) {
		//String fileName = args[0];
		
		// Convert string value to integer
		String pageCount = "12";
		int pagesToBeDownloaded = Integer.parseInt(pageCount);
		System.out.println("pages to be downloaded: " + pagesToBeDownloaded);
		
		// Convert and integer value to a string
		int pageNumber = 12;
		String strPageNumber = Integer.toString(pageNumber);
		System.out.println("page number: " + strPageNumber);
		
		// to determine if a string contains a value
		String storeName = "My Store Rocks";
        if (storeName.trim().length() > 0) {
    		System.out.println("Store name found");
        } else {
        	System.out.println("Store name is empty");
        }

		/**  
		 * Use of .equals to compare a string to a literal
		 */
		String fileName = "/amazon/amazoninventoryfeed.xml";
		
   	 	if (fileName.equals("")) {
   	 		System.out.println("fileName is null!");
   	 	}else {
   	 		System.out.println("fileName: " + fileName);
   	 	}
   	 
   	 	// Inventory update
        if (fileName.equals("/amazon/amazoninventoryfeed.xml")) {
        	textFile = "/amazon/invfeedresponse.txt";
        }
        
        // Shipping update
        if (fileName.equals("/amazon/amazonshippingfeed.xml")) {
        	textFile = "/amazon/shpfeedresponse.txt";
        }
        
        // Pricing update
        if (fileName.equals("/amazon/amazonpricefeed.xml")) {
        	textFile = "/amazon/prcfeedresponse.txt";
        }

	}

}
