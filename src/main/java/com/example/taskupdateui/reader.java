package com.example.taskupdateui;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class reader {
  public static HashMap<String, String> reading() throws IOException {
     String file_loc=".//src/main/resources/static/bok.xlsx";
    File file = new File(file_loc); //creating a new file instance
    FileInputStream fis = new FileInputStream(file); //obtaining bytes from the file  

    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheetAt(0); //creating a Sheet object to retrieve object  
    Iterator < Row > itr = sheet.iterator();
    HashMap < String, String > map = new HashMap < > (); //iterating over excel file  
    while (itr.hasNext()) {
      String information = "", temp_information = "", dates = "";
      Row row = itr.next();

      double val = 0;
      int i = 0;
      int k = 0;
      int j = 0;
      int d = 0;

      Iterator < Cell > cellIterator = row.cellIterator();
      DataFormatter dataFormatter = new DataFormatter(); //iterating over each column  
      while (cellIterator.hasNext()) {
        Cell cell = cellIterator.next();
        i++;
        if (cell.getCellType() == CellType.STRING) {
          information = cell.getStringCellValue();
//      	System.out.println(cell+" "+i);
          k++;
        } else if (cell.getCellType() == CellType.NUMERIC) {
          val = cell.getNumericCellValue();
//      	System.out.println(cell+" "+i);
          j++;
        } 
       
        
       
        if (i% 4== 1) {
          temp_information = information;
          

        }
        if (i % 4==3) {
            
          
            if (temp_information.length() != 0 && information.length() != 0 && val == 0)
            {
              map.put(temp_information, information);
//            System.out.println(temp_information+" "+information);
            information = "";
            temp_information = "";
            }
            else
            {
           

            String str = Double.toString(val);
            if (temp_information.length() != 0 && str.length() != 0)
              map.put(temp_information, str);
//            System.out.println(temp_information+" "+str);
            information = "";
            temp_information = "";
            }
          }
        }
   

      }
      System.out.println(map);
	return map;
    
  }
  public static void main(String args[]) {
	  HashMap < String, String > maps = new HashMap < > ();
	  try {
		maps=reading();
		System.out.println(Arrays.asList(maps));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}