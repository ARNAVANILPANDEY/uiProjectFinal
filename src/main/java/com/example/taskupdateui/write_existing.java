package com.example.taskupdateui;


import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class write_existing {

	// any exceptions need to be caught
	public static void main(Vector<HashMap<String,String>> arg,HashMap<String,String> inpArg) throws IOException
	{
		

		// workbook object
		XSSFWorkbook workbook = new XSSFWorkbook();

		// spreadsheet object
		XSSFSheet spreadsheet
			= workbook.createSheet("Student Data");





		// This data needs to be written (Object[])
		HashMap<Long, Object[]> studentData
			= new HashMap<Long, Object[]>();

		studentData.put(
			Long.valueOf(0),
			new Object[] {"Installment No.","Stage Number","Installment Due Date","Installment Amount","Interest Rate",
					"Component 1 (Principal)","Component 2 (Interest)","Opening Balance","Closing Balance" });





		for ( HashMap<String, String> tem: arg)
		{
			System.out.println();
			System.out.println("tem is "+tem);
			studentData.put(Long.valueOf(tem.get("Installment_Number")),new Object[]{tem.get("Installment_Number"),
					tem.get(("Stage_Number")),
					tem.get(("Installment_Due_Date")),
					tem.get(("Installment_Amount")),
					tem.get(("Interest_Rate")),
					tem.get(("Current_Principal")),
					tem.get(("Current_Interest")),
					tem.get(("Current_Opening_Balance")),
					tem.get(("Current_Closing_Balance"))});
		}
		System.out.println("STUDENT DATA MAP :"+studentData);
		List<Long> keyStrings=new ArrayList<>(studentData.keySet());
		Collections.sort(keyStrings);
		//Set<String> keyid = studentData.keySet();
		

		 

		// writing the data into the sheets...


/**********************************************************************/

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rowid1 = 0;
		System.out.println(rowid1);

		for (String key:inpArg.keySet()) {



			XSSFRow row = spreadsheet.createRow(rowid1++);
			Object[] objectArr = studentData.get(key);

			int columnCount = 0;

			// Create new cell for each row
			XSSFCell cell = row.createCell(columnCount);


				cell = row.createCell(columnCount++);

				cell.setCellValue((String)key);
			cell = row.createCell(columnCount++);
				cell.setCellValue((String)inpArg.get(key));

		}



/**********************************************/
		int rowid = 17;
		System.out.println(rowid);
		for (Long key : keyStrings) {



			 XSSFRow row = spreadsheet.createRow(rowid++);
			Object[] objectArr = studentData.get(key);

			 int columnCount = 0;

             // Create new cell for each row
             XSSFCell cell = row.createCell(columnCount);

			for (Object obj : objectArr) {
				cell = row.createCell(columnCount++);

				cell.setCellValue((String)obj);
				System.out.println("Object Write:"+obj);
			}
		}





		 
         // Write the workbook in file system
         FileOutputStream outputStream = new FileOutputStream("final.xlsx");
         workbook.write(outputStream);
         System.out.println("Excel is updated successfully");

         // Close the workbook
         workbook.close();
         outputStream.close();
	}
}
