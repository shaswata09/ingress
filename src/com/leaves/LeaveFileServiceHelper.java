package com.leaves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PMOProperties.ExcelFileAttributes;

public class LeaveFileServiceHelper {

	public static final boolean checkIfFileExists(final String folderPath, final String folderName) {
		try {		
			final FileInputStream file = new FileInputStream(new File(folderPath+folderName));
			file.close();
			return true;			
		}
		catch(Exception exception) {
			return false;
		}
	}
	
	
	public static final boolean createLeaveEntryFile(final String folderPath, final String fileName) {
		final String fullPath = folderPath + fileName;		
		try {
			if(!checkIfFileExists(folderPath, fileName)) {
				createLeaveEntryDirectory(folderPath);
				
				final XSSFWorkbook workbook = new XSSFWorkbook();
				final XSSFSheet sheet = workbook.createSheet(StringUtils.split(fileName, '.')[0]);
				final FileOutputStream out = new FileOutputStream(new File(fullPath));
	     
	            final Row row = sheet.createRow(sheet.getLastRowNum());
	            
	            row.createCell(0).setCellValue(ExcelFileAttributes.EMPLOYEE_ID);
	            row.createCell(1).setCellValue(ExcelFileAttributes.EMPLOYEE_NAME);
	            row.createCell(2).setCellValue(ExcelFileAttributes.PROJECT);
	            row.createCell(3).setCellValue(ExcelFileAttributes.START_DATE);
	            row.createCell(4).setCellValue(ExcelFileAttributes.END_DATE);
	            row.createCell(5).setCellValue(ExcelFileAttributes.LEAVE_MONTH);
	            row.createCell(6).setCellValue(ExcelFileAttributes.NUMBER_OF_DAYS);
	            row.createCell(7).setCellValue(ExcelFileAttributes.TYPE_OF_LEAVE);
	            row.createCell(8).setCellValue(ExcelFileAttributes.LOCATION);
	            row.createCell(9).setCellValue(ExcelFileAttributes.SOW);
	            row.createCell(10).setCellValue(ExcelFileAttributes.COMMENT);
	         
	            workbook.write(out);
	            
	            workbook.close();
	            out.close();				
				return true;
			}				
			else
				return false;
		}
		catch(Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
	
	public static boolean checkIfDirectoryExists(final String folderPath) {
		final File tmpDir = new File(folderPath);
		if(tmpDir.isDirectory()) {
			return true;
		}			
		else
			return false;
	}
	
	
	public static final boolean createLeaveEntryDirectory(final String folderPath) {
		if(!checkIfDirectoryExists(folderPath)) {
			final File file = new File(folderPath);
			return file.mkdirs();
		}
		else
			return false;
	}
}
