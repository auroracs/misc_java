package com.jengle.misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindandReplaceExample {

	public static void main(String[] args) {
		
		String x = "this is my string!";
		String y = x.replace("!","!!!");
		
		x = "~CC^";
		y = x.replaceAll("^", "%");
		
		// look for pattern "><"
		// Returns 16 and 40
		
		//String match = "><";
        //String text = "<name>Jim</name><addr1>address 1</addr1><addr2>Omaha</addr2>";

		String match = "/eBay/test/";
		String match2 = "</totalPages>";
        String text = "/eBay/test/fileismine.xml";

        int i = 0;
        int z = 0;
        int j = 0;
        
        if ( (i=(text.indexOf(match,i)+1)) >0 ) {
            System.out.println(i);
        } else {
        	System.out.println(i);
        }
        
        //while((i=(text.indexOf(match,i)+1))>0)
        //    System.out.println(i);
        
        // find starting position of page count
        while ( (i=(text.indexOf(match,i)+1)) >0 ) {
            System.out.println(i);
    		j = i+11;
        }
        
        // find ending position page count
        while ( (z=(text.indexOf(match2,z)+1)) >0 ) {
            System.out.println(z);
    		System.out.println(text.substring(j, z-1));
    		//y = i;
        }
        
        String str = "1EOBHGBDIUQQ1111,1EOBHGBDIUQQ2222";

        Pattern p = Pattern.compile("(,)(?! )");
        Matcher m = p.matcher(str);

        StringBuffer sb = new StringBuffer();

        while(m.find()) {
            m.appendReplacement(sb, " ");
        }

        m.appendTail(sb);
        System.out.println(sb);
        
    }

}
