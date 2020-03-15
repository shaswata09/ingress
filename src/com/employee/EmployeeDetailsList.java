package com.employee;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PMOProperties.ExcelFileDetails;

public class EmployeeDetailsList {
	
	final String fileName = ExcelFileDetails.EMPLOYEE_LOOKUP_FILE_NAME;
    final String fullPath = ExcelFileDetails.PROJECT_FOLDER_PATH + this.fileName;
    FileInputStream file;
    XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	public List<Employee> returnList() {
		
		try {
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);            
            this.sheet = this.workbook.getSheetAt(0);
        } catch(final Exception e) {
            System.out.println(e);
        }
		
        final List<Employee> empList = new ArrayList<Employee>();
        final Iterator<Row> rowIterator = this.sheet.iterator();
        int countRow = 0;
        while(rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if(countRow == 0) {
                countRow++;
                continue;
            }
            if(row == null) {
                continue;
            }
        
            final Iterator<Cell> cellIterator = row.cellIterator();
            final Employee e = new Employee();
            int count = 0;


            while(cellIterator.hasNext()) {
                final Cell cell = cellIterator.next();
                if(cell == null) {
                    count++;
                    continue;
                }
                final CellType type = cell.getCellType();


                if(count == 0) {
                    if(type == CellType.STRING) {
                    	String s=cell.getStringCellValue();
                        e.setEmployeeName(s);
                      
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Double.toString(cell.getNumericCellValue());
                        e.setEmployeeName(temp);   
                        
                    }
                } else if(count == 1) {
                    if(type == CellType.STRING) {
                        e.setEmployeeId(cell.getStringCellValue());                        
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Integer.toString((int)cell.getNumericCellValue());
                        e.setEmployeeId(temp);                        
                    }
                } else if(count == 2) {
                    if(type == CellType.STRING) {
                        e.setEmployeeTeam(cell.getStringCellValue());                        
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Integer.toString((int)cell.getNumericCellValue());
                        e.setEmployeeTeam(temp);                        
                    }
                } else if(count == 3) {
                    if(type == CellType.STRING) {
                        e.setEmployeeLocation(cell.getStringCellValue());                       
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Integer.toString((int)cell.getNumericCellValue());
                        e.setEmployeeLocation(temp);                        
                    }
                } else if(count == 4) {
                    if(type == CellType.STRING) {
                        e.setEmployeeRole(cell.getStringCellValue());                        
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Integer.toString((int)cell.getNumericCellValue());
                        e.setEmployeeRole(temp);                       
                    }
                } else if(count == 5) {
                    if(type == CellType.STRING) {
                        e.setEmployeeSOW(cell.getStringCellValue());                        
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Integer.toString((int)cell.getNumericCellValue());
                        e.setEmployeeSOW(temp);                        
                    }
                } else if(count == 6) {
                    if(type == CellType.STRING) {
                        e.setEmployeeMobile(cell.getStringCellValue());                        
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Long.toString((long)cell.getNumericCellValue());
                        e.setEmployeeMobile(temp);     
                    }
                } else if(count == 7) {
                    if(type == CellType.STRING) {
                        e.setEmployeeEmergencyContact(cell.getStringCellValue());                       
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Long.toString((long)cell.getNumericCellValue());
                        e.setEmployeeEmergencyContact(temp);                        
                    }
                }
                else if(count == 8) {
                    if(type == CellType.STRING) {
                        e.setEmployeeMailID(cell.getStringCellValue());                        
                    } else if(type == CellType.NUMERIC) {
                        final String temp = Integer.toString((int)cell.getNumericCellValue());
                        e.setEmployeeMailID(temp);                        
                    }
                } 
                count++;
            }
         
            empList.add(e);
            countRow++;
        }
        
        return empList;
    }
}
