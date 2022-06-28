package com.example.taskupdateui;


import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
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
		// workbook object
		XSSFWorkbook workbook = new XSSFWorkbook();

		// spreadsheet object
		XSSFSheet spreadsheet
			= workbook.createSheet(" Student Data ");

		// creating a row object
		XSSFRow row;

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
					tem.get(("Current_Opening_Balance")),
					tem.get(("Current_Closing_Balance"))});
		}
		System.out.println("STUDENT DATA MAP :"+studentData);
		List<Long> keyStrings=new ArrayList<>(studentData.keySet());
		Collections.sort(keyStrings);
		//Set<String> keyid = studentData.keySet();
		System.out.println("KEYSTIRNGS"+keyStrings);
		int rowid = 0;

		// writing the data into the sheets...

		for (Long key : keyStrings) {


			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = studentData.get(key);
			
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				
				cell.setCellValue((String)obj);
				System.out.println("Object Write:"+obj);
			}
		}

		
		FileOutputStream out = new FileOutputStream(
			new File("final.xlsx"));


		workbook.write(out);
		out.close();
	}
}




