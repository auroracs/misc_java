package com.jengle.as400;

import com.ibm.as400.access.*;

public class KeyedReadUpdate {

	public static void main(String[] args) {

		System.out.println("Performing CHAIN on AS400 file...");
		
		try {
			
			String pFileName = "MYFILEP";
			String pFileMemberName = pFileName;
			
			AS400 as400 = new AS400("111.111.111.111");
			as400.setUserId("USERNAME");
			as400.setPassword("PASSWORD");
			
			String as400FileName = "/QSYS.LIB/MYLIB.LIB/" + pFileName + ".FILE";
			String as400MemberName = pFileMemberName + ".MBR";

			// Create a file object that represents the AS400 file
			KeyedFile newFile = new KeyedFile(as400, as400FileName + "/" + as400MemberName);

			RecordFormat recordFormat = null;
			AS400FileRecordDescription recordDescription = 
			        new AS400FileRecordDescription(as400, as400FileName);

		    // There is only one record format in my file, so take the first (and only) element
		    // of the RecordFormat array returned as the RecordFormat for the file.
		    recordFormat = recordDescription.retrieveRecordFormat()[0];
			
			// Set the record format of AS400 file
			newFile.setRecordFormat(recordFormat);

			// Open the file for reading only.
			newFile.open(AS400File.READ_WRITE, 100, AS400File.COMMIT_LOCK_LEVEL_NONE);

			// setup key fields (company code and order ID)
			Object[] partialKey = new Object[2];
		     partialKey[0] = "D"; // key field 1 (company code)
		     partialKey[1] = "XXXX"; // key field 2 (order ID) 
		     
			// Simulate CHAIN and position to keyed record
			Record data = newFile.read(partialKey);   
			 
			// if %found, print the record
			if (data != null) { 
				
				// get and print company code
				String company = data.getField("BCMPY").toString();
				System.out.println("Company code: " + company);
				
				// get and print the merch order ID
				String merchOrderID = data.getField("MERORDID").toString();
				System.out.println("Merchant Order ID: " + merchOrderID);
				
				// get and print the buyer name
				String buyerName = data.getField("BUYERNAME").toString();
				System.out.println("Buyer Name: " + buyerName);
				
				// set new buyer name and update the record
				buyerName = "Jim Engle";
				data.setField("BUYERNAME", buyerName);
				newFile.update(data);
				
				// get and print the updated buyer name
				buyerName = data.getField("BUYERNAME").toString();
				System.out.println("Buyer Name: " + buyerName);
				
			}
			
		     // Close the file since I am done using it
		     newFile.close();

		     // Disconnect since I am done using record-level access
		     as400.disconnectService(AS400.RECORDACCESS);
		     
			System.out.println("Completed file reading to AS400...");

		} 

		catch(Exception e) { 
			System.out.println(e.getMessage()); 
			e.printStackTrace(); 
		}		

	}

}
