package com.leaves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeaveServiceImpl {

	public static final boolean insertLeaveIntoTable(final List<String> finalList, final String folderPath, final String fileName) {
		boolean leaveEntryFlag = false;
		final String fullPath = folderPath+fileName;
		
		final XSSFWorkbook workbook;
		final XSSFSheet sheet;
		final FileInputStream file;
		final CreationHelper createHelper;
		final CellStyle dateCellStyle;
	    final FileOutputStream out;
	    
		if(!LeaveFileServiceHelper.checkIfFileExists(folderPath, fileName)) {
			leaveEntryFlag = LeaveFileServiceHelper.createLeaveEntryFile(folderPath, fileName);
			if(leaveEntryFlag==false)
				return false;
		}
		try {
			file = new FileInputStream(new File(fullPath));
	        workbook = new XSSFWorkbook(file);
	        sheet = workbook.getSheetAt(0);
	        createHelper = workbook.getCreationHelper();
	        dateCellStyle = workbook.createCellStyle();
	        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

	        final Row row = sheet.createRow(sheet.getLastRowNum()+1);
	        
	        row.createCell(0).setCellValue(Integer.parseInt(finalList.get(0)));
	        row.createCell(1).setCellValue(finalList.get(1));
	        row.createCell(2).setCellValue(finalList.get(2));
	        row.createCell(3).setCellValue(finalList.get(3));
	        row.getCell(3).setCellStyle(dateCellStyle);
	        row.createCell(4).setCellValue(finalList.get(4));
	        row.getCell(4).setCellStyle(dateCellStyle);
	        row.createCell(5).setCellValue(finalList.get(5));
	        row.createCell(6).setCellValue(Long.parseLong(finalList.get(6)));
	        row.createCell(7).setCellValue(finalList.get(7));
	        row.createCell(8).setCellValue(finalList.get(8));
	        row.createCell(9).setCellValue(finalList.get(9));
	        row.createCell(10).setCellValue(finalList.get(10));
	        
	        out = new FileOutputStream(new File(fullPath));
	        workbook.write(out);
	        
	        out.close();	        
	        workbook.close();
	        file.close();
	        return true;
		}catch(Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
	
	//public static void main(String args[]) {
		//System.out.println(checkIfFileExists("C:\\Users\\shasw\\OneDrive\\Desktop\\Leave_Management\\PMO Application\\PMO Application\\"+"TempTable.xlsx"));
		//System.out.println(createLeaveEntryFile("C:\\Users\\shasw\\OneDrive\\Desktop\\Leave_Management\\PMO Application\\PMO Application\\","TempTable.xlsx"));
		//System.out.println(checkIfFolderExists("C:\\Users\\shasw\\OneDrive\\Desktop\\Leave_Management\\PMO Application\\PMO Application\\"));
		//System.out.println(createLeaveEntryDirectory("C:\\Users\\shasw\\OneDrive\\Desktop\\Leave_Management\\PMO Application\\PMO Application\\"));
	//}
}
