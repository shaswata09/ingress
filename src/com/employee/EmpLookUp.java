package com.employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PMOProperties.ExcelFileDetails;
 

public class EmpLookUp {
    final String fileName = ExcelFileDetails.EMPLOYEE_LOOKUP_FILE_NAME;
    final String fullPath = ExcelFileDetails.EMPLOYEE_FOLDER_PATH + this.fileName;
    FileInputStream file;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    @Override
    protected void finalize() throws Throwable {
        // Keep some resource closing operations here

    }

    public EmpLookUp() {
        try {
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);
         
            this.sheet = this.workbook.getSheetAt(0);
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> searchId(final String empId)// for searching employee id in the look up table
    {
        final List<String> al = new ArrayList<String>();
        
        for(int i = 1; i <= this.sheet.getLastRowNum(); i++) {
            final Row row = this.sheet.getRow(i);
            if(null == row) {
                continue;
            }
            final Cell cell = row.getCell(1);
            if(null == cell) {
                continue;
            }
            final int cellEmpId = (int)cell.getNumericCellValue();
            if(cellEmpId == Integer.parseInt(empId)) {
                for(int j = 0; j < 9; j++) {
                    final Cell cell2 = row.getCell(j);
                    try {
                    	final CellType ct = cell2.getCellType();
                        if(ct == CellType.STRING) {
                            al.add(cell2.getStringCellValue());
                        } else if(ct == CellType.NUMERIC) {
                            final long temp = (long)cell2.getNumericCellValue();
                            al.add(Long.toString(temp));
                        }
                        else {
                        	al.add("");
                        }
                    }
                    catch(Exception exception) {
                    	exception.printStackTrace();
                    	al.add("");
                    }
                }
                return al;
            }
        }
      return null;// returns null if finds nothing-- so have to handle it
    }

    public void addRow(String empName, String empID, String empTeam, 
    		String empLocation, String empRole, String empSOW, String empMobile, 
    		String empEmergency, String empKITSID) throws IOException {       
        try {          
            int lastRow = this.sheet.getLastRowNum();
            final Row row = this.sheet.createRow(++lastRow); 
            row.createCell(0).setCellValue(empName);
            row.createCell(1).setCellValue(empID);
            row.createCell(2).setCellValue(empTeam);
            row.createCell(3).setCellValue(empLocation);
            row.createCell(4).setCellValue(empRole);
            row.createCell(5).setCellValue(empSOW);
            row.createCell(6).setCellValue(empMobile);
            row.createCell(7).setCellValue(empEmergency);
            row.createCell(8).setCellValue(empKITSID);
            final FileOutputStream fileOut = new FileOutputStream(this.fullPath);
            this.workbook.write(fileOut);
            fileOut.close();
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }

    public void printSheet() throws IOException {      
        final Iterator<Row> rowIterator = this.sheet.iterator();
        while(rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            // For each row, iterate through all the columns
            final Iterator<Cell> cellIterator = row.cellIterator();
            
            while(cellIterator.hasNext()) {
                final Cell cell = cellIterator.next();
                final CellType type = cell.getCellType();
                if(type == CellType.STRING) {
                    System.out.print(cell.getStringCellValue() + "   ");
                } else if(type == CellType.NUMERIC) {
                    System.out.print(cell.getNumericCellValue() + "  ");
                }
            }
        }
        this.file.close();
    }
}