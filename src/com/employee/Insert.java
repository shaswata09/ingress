package com.employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PMOProperties.*;

public class Insert {
	
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    FileInputStream file;
    final String fullPath = ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.EMPLOYEE_LOGIN_FILE_NAME;

    public void insertEmployee() throws IOException {
    	
        final Scanner scanner = new Scanner(System.in);
        try {
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);
            this.sheet = this.workbook.getSheetAt(0);
        } catch(final IOException io) { // if the file is not present then it will create the file
                                        // first and call again
            // Blank workbook
            this.workbook = new XSSFWorkbook();
            
            // Create a blank sheet
            this.sheet = this.workbook.createSheet("Login Details");
            final FileOutputStream out = new FileOutputStream(
                    new File(fullPath));
            this.workbook.write(out);
            out.close();
            insertEmployee();
            return;
        }
        int lastRowNumber = this.sheet.getLastRowNum();

 

        final Row row = this.sheet.createRow(++lastRowNumber);
        for(int i = 0; i < 3; i++) {
            if(i == 0) {
                System.out.println("Give employee id: ");
            } else if(i == 1) {
                System.out.println("Give Password: ");
            } else {
                System.out.println("Give access level: ");
            }
            final Cell cell = row.createCell(i);
            cell.setCellValue(scanner.next());
        }
        final FileOutputStream out = new FileOutputStream(
                new File(fullPath));
        scanner.close();
        this.workbook.write(out);
        this.workbook.close();
        out.close();
    }
	
	
	
}
