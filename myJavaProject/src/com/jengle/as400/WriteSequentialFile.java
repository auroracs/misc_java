package com.jengle.as400;

import com.ibm.as400.access.*;

public class WriteSequentialFile {

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
			newFile.open(AS400File.WRITE_ONLY, 0, AS400File.COMMIT_LOCK_LEVEL_NONE);

			Record writeRec = newFile.getRecordFormat().getNewRecord();
			
			writeRec.setField("BCMPY", "D"); // set company code to "D"
			writeRec.setField("BUYERNAME", "Jim Engle"); // set buyer name  
			newFile.write(writeRec); // write the record
			
			writeRec.setField("BCMPY", "D"); // set company code to "D"
			writeRec.setField("BUYERNAME", "Darin Engle"); // set buyer name  
			newFile.write(writeRec); // write the record

			
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
