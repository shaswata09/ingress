package com.features;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PMOProperties.ExcelFileDetails;

public class IssueLogin {
	final String fullPath =  ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.EMPLOYEE_LOGIN_FILE_NAME;
	final String fullPath1 = ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.EMPLOYEE_LOOKUP_FILE_NAME;
	FileInputStream file,file1;
    XSSFWorkbook workbook,workbook1;
    XSSFSheet sheet,empLookUpSheet;
    
    
	public void autoFillLogin() throws IOException {
        try {
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);
            this.sheet = this.workbook.getSheetAt(0);
            this.file1 = new FileInputStream(new File(this.fullPath1));
            this.workbook1 = new XSSFWorkbook(this.file1);
            this.empLookUpSheet = this.workbook1.getSheetAt(0);
        } catch(final IOException e) {
            e.printStackTrace();
        }
        byte count = 0;
        final Iterator<Row> rowIterator = empLookUpSheet.iterator();
        while(rowIterator.hasNext()) {

            final Row row = rowIterator.next();
            if(count == 0) {
                count = 1;
                continue;
            }
            // For each row, iterate through all the columns
            final Cell cell = row.getCell(1);
           
            int empLookUpId;
            final CellType type = cell.getCellType();
            if(type == CellType.STRING) {
                empLookUpId = Integer.parseInt(cell.getStringCellValue());
            } else {
                empLookUpId = (int)cell.getNumericCellValue();
            }
            this.isInEluTable(this.sheet, empLookUpId);

        }

    }

    public void isInEluTable(final XSSFSheet Sheet, final int empId) throws IOException {

        for(int i = 1; i <= this.sheet.getLastRowNum(); i++) {
            final Row row = this.sheet.getRow(i);
            if(row == null) {
                continue;
            }
            final Cell cell = row.getCell(0);
            final CellType type = cell.getCellType();
            if(null == cell) {
                continue;
            }
            final int cellEmpId;
            if(type == CellType.NUMERIC) {
                cellEmpId = (int)cell.getNumericCellValue();
            } else {
                cellEmpId = Integer.parseInt(cell.getStringCellValue());
            }
            if(cellEmpId == empId) {
                return;
            }
        }
        this.addDefault(empId);

    }

    public void addDefault(final int empId) throws IOException { 
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);
            this.sheet = this.workbook.getSheetAt(0);

        int lastRowNumber = this.sheet.getLastRowNum();
 

        final Row row = this.sheet.createRow(++lastRowNumber);
        for(int i = 0; i < 3; i++) {
            final Cell cell = row.createCell(i);
            if(i == 0) {
                cell.setCellValue(empId);
            } else if(i == 1) {
                cell.setCellValue("12345");
            } else {
                cell.setCellValue(0);
            }

        }

        final FileOutputStream out = new FileOutputStream(
                new File(ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.EMPLOYEE_LOGIN_FILE_NAME));
        this.workbook.write(out);

        out.close();
    }

}
