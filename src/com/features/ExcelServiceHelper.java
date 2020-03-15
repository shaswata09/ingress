package com.features;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PMOProperties.ExcelFileDetails;

public class ExcelServiceHelper {
	public static void main(String args[]) {
		 final String fullPath = ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.TEMP_TABLE;
		 try{
			 FileInputStream file = new FileInputStream(new File(fullPath));
             XSSFWorkbook workbook = new XSSFWorkbook(file);
             XSSFSheet sheet = workbook.getSheetAt(0);	    
		     OutputStream os = new FileOutputStream(fullPath);	 
		     		         
	         sheet = workbook.getSheetAt(0);      
	         //Row row = sheet.getRow(0);
	         System.out.println(sheet.getLastRowNum());
	         sheet.shiftRows(7, 9, -1);
//	         sheet.shiftRows(1, sheet.getLastRowNum()-1, -1);
	        
	        
//	         System.out.println(row.getCell(0).getStringCellValue());
	         workbook.write(os);
	         os.close();
	         workbook.close();
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	}
	
	
	public static boolean isEmptyRow(Row row){
		boolean isEmptyRow;
		try {
			
			isEmptyRow = true;
	        for(int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++){
	            Cell cell = row.getCell(cellNum);
	            if(cell != null && cell.getCellType() != CellType.BLANK && StringUtils.isNotBlank(cell.toString())){
	            	isEmptyRow = false;
	            }    
	        }
		}
		catch(Exception exception) {
			isEmptyRow = true;
		}
	     return isEmptyRow;
	}
}
