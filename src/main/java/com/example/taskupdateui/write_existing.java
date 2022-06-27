package loll;


import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


public class write {

	// any exceptions need to be caught
	public static void main(Vector<HashMap<String,String>> arg) throws IOException
	{
		
		 FileInputStream fis = new FileInputStream(new File("final.xlsx"));
		// workbook object
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// spreadsheet object
		XSSFSheet spreadsheet
			= workbook.getSheet(" Student Data ");



		// This data needs to be written (Object[])
		HashMap<Long, Object[]> studentData
			= new HashMap<Long, Object[]>();

		studentData.put(
			Long.valueOf(0),
			new Object[] {"Installment No.","Stage Number","Installment Due Date","Installment Amount","Interest Rate",
					"Component 1 (Principal)","Component 2 (Interest)","Opening Balance","Closing Balance" });

//		studentData.put("2", new Object[] { "1", "10-Jul-22","4,754.42","12.00%","3,758.26","996.16","1,01,000.00","97,241.74"});
//		for (Map.Entry<String, Object[]> entry :  studentData.entrySet()) {
////		    System.out.println(Double.toString(Math.round(Double.parseDouble(Arrays.toString(entry.getValue())))));
//			
////		    Arrays.toString(myArray);
//		}



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
					tem.get(("Current_Opening Balance")),
					tem.get(("Current_Closing Balance"))});
		}
		System.out.println("STUDENT DATA MAP :"+studentData);
		List<Long> keyStrings=new ArrayList<>(studentData.keySet());
		Collections.sort(keyStrings);
		//Set<String> keyid = studentData.keySet();
		
		int rowid = spreadsheet.getLastRowNum();
		System.out.println(rowid);
		 

		// writing the data into the sheets...

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

		
		 fis.close();
		 
         // Write the workbook in file system
         FileOutputStream outputStream = new FileOutputStream("final.xlsx");
         workbook.write(outputStream);
         System.out.println("Excel is updated successfully");

         // Close the workbook
         workbook.close();
         outputStream.close();
	}
}
