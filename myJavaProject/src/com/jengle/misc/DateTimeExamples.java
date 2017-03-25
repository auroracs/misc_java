package com.jengle.misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeExamples {

	public static void main(String[] args) throws ParseException {

		// create a gregorian calendar and convert it to a string
		GregorianCalendar gdate = new GregorianCalendar(2015, 12, 23);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		fmt.setCalendar(gdate);
 	    String dateFormatted = fmt.format(gdate.getTime());
        
		System.out.println("gdate to string: " + dateFormatted);
		   
		// Set timestamp format in DF (3 digit millisecond format SSS)
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        // Bring in timestamp
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        
		// Set timestamp format in DF2 (no milliseconds)
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        
        // Bring in timestamp
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        
  	    //get current date time with Date()
  	    Date date = new Date();
        String yourDate = df.format(date);
        System.out.println("Timestamp DF:" + yourDate);
        
  	    //get current date time with Date()
  	    Date date2 = new Date();
        String yourDate2 = df2.format(date2);
        System.out.println("Timestamp DF:" + yourDate2);

        // setup variables
        Date today = new Date();
        Date yesterday = new Date();
        yesterday = addDays(today, -1);
        System.out.println("Todays Date:" + today);
        System.out.println("Yesterdays Date:" + yesterday);
        
        // convert string to date
        String crtAfter = "2014-04-25T15:17:32Z";
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date1 = df3.parse(crtAfter);
        System.out.println("Date:" + date1);

	}

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
