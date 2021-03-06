package com.leaves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PMOProperties.ExcelFileDetails;

public class RejectedListTable {
	final String fileName = ExcelFileDetails.REJECTED_LIST;
    final String filePath = ExcelFileDetails.PROJECT_FOLDER_PATH;
    final String fullPath = this.filePath + this.fileName;
    FileInputStream file;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
	
	public RejectedListTable() throws IOException
	{
		try {
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);
//            System.out.println(this.workbook.getActiveSheetIndex());
            this.sheet = this.workbook.getSheetAt(0);
        } catch(final Exception e) {
        	// Blank workbook
            this.workbook = new XSSFWorkbook();

            // Create a blank sheet
            this.sheet = this.workbook.createSheet("Temp Table");
            int lastRowNumber = this.sheet.getLastRowNum();
            final FileOutputStream out = new FileOutputStream(
                    new File(this.fullPath));
            final Row row = this.sheet.createRow(++lastRowNumber);
            for(int i = 0; i < 11; i++) {
                final Cell cell = row.createCell(i);
                if(i == 0) {
                    cell.setCellValue("Emp Id");
                } else if(i == 1) {
                    cell.setCellValue("Emp Name");
                } else if(i == 2) {
                    cell.setCellValue("Project");
                } else if(i == 3) {
                    cell.setCellValue("Start Date");
                } else if(i == 4) {
                    cell.setCellValue("End Date");
                } else if(i == 5) {
                    cell.setCellValue("Leave Month");
                } else if(i == 6) {
                    cell.setCellValue("Number Of Days");
                } else if(i == 7) {
                    cell.setCellValue("Type Of Leave");
                } else if(i == 8) {
                    cell.setCellValue("Location");
                } else if(i == 9) {
                    cell.setCellValue("SOW");
                } else if(i == 10) {
                    cell.setCellValue("Comment");
                }

            }
            this.workbook.write(out);
            out.close();
        }
	}
}
