package com.jengle.as400;

import com.ibm.as400.access.*;

public class SequentialFileRead {

	public static void main(String[] args) {

		System.out.println("Reading file to AS400...");
		try
		{  
			String pFileName = "MYFILEP";
			String pFileMemberName = pFileName;
			
			AS400 as400 = new AS400("111.111.111.111");
			as400.setUserId("USERNAME");
			as400.setPassword("PASSWORD");
			
			String as400FileName = "/QSYS.LIB/MYLIB.LIB/" + pFileName + ".FILE";
			String as400MemberName = pFileMemberName + ".MBR";

			// Create a file object that represents the AS400 file
			SequentialFile newFile = new SequentialFile(as400, as400FileName + "/" + as400MemberName);

			RecordFormat recordFormat = null;
			AS400FileRecordDescription recordDescription = 
			        new AS400FileRecordDescription(as400, as400FileName);

		    // There is only one record format in my file, so take the first (and only) element
		    // of the RecordFormat array returned as the RecordFormat for the file.
		    recordFormat = recordDescription.retrieveRecordFormat()[0];
			
			// Set the record format of AS400 file
			newFile.setRecordFormat(recordFormat);

			// Open the file for reading only.
			newFile.open(AS400File.READ_ONLY, 100, AS400File.COMMIT_LOCK_LEVEL_NONE);

			// Priming read...
			Record data = newFile.readFirst();   
			 
			// loop through AS400 file, printing each record
			while (data != null) 
			{ 
				// get and print company code
				String company = data.getField("BCMPY").toString();
				System.out.println("Company code: " + company);
				
				// get and print the merch order ID
				String merchOrderID = data.getField("MERORDID").toString();
				System.out.println("Merchant Order ID: " + merchOrderID);
				
				// get and print the buyer name
				String buyerName = data.getField("BUYERNAME").toString();
				System.out.println("Buyer Name: " + buyerName);
				
				// Get next record
				data = newFile.readNext();   
			 
			}
			
		     // Close the file since I am done using it
		     newFile.close();

		     // Disconnect since I am done using record-level access
		     as400.disconnectService(AS400.RECORDACCESS);

		} 
		
		catch(Exception e) 
		{ 
			System.out.println(e.getMessage()); 
			e.printStackTrace(); 
		}		
		System.out.println("Completed file reading to AS400...");

	}

}
