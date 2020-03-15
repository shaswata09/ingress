package com.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.EmpLookUp;
import com.employee.Employee;
import com.PMOProperties.ExcelFileDetails;;

public class Validate {
	final String fullPath = ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.EMPLOYEE_LOGIN_FILE_NAME;
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    FileInputStream file;
	//private static List<Object> userInfo = null; 
	
    public Employee checkCredential(final String id, final String password) throws IOException {
        final List<String> al = new ArrayList<String>();
        final EmpLookUp elu = new EmpLookUp();
        try {
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);
            this.sheet = this.workbook.getSheetAt(0);
        } catch(final IOException e) {
            e.printStackTrace();
        }
        final Iterator<Row> rowIterator = this.sheet.iterator();


        while(rowIterator.hasNext()) {
        	System.out.println("step 1---> in while");
            final Row row = rowIterator.next();
            final Cell cellId = row.getCell(0);
            final CellType type = cellId.getCellType();


            if(type == CellType.STRING) {
            	System.out.println("step 2---> celltype is string");
                final String cellValue = cellId.getStringCellValue();
                if(cellValue.equals(id)) {
                    final Cell cellPassword = row.getCell(1);
                    if(cellPassword.getStringCellValue().equals(password)) {
                        if(row.getCell(2).getStringCellValue().equals("1")) {
                        	System.out.println("step 2.1---> password matched and is admin");
                            al.add("1");
                            final List<String> al2 = elu.searchId(id);
                            if(al2 == null) {
                                System.out.println("Found in login table but not present in ELT");
                                return null;
                            }
                            al.addAll(al2);
                            return new Employee(al);
                        } 
                        else {
                        	System.out.println("step 2.2---> password matched and is user");
                            al.add("0");
                            final List<String> al2 = elu.searchId(id);
                            if(al2 == null) {
                                System.out.println("Found in login table but not present in ELT");
                                return null;
                            }
                            al.addAll(al2); 
                            return  new Employee(al);
                        }
                    }
                }
            }
            else if(type==CellType.NUMERIC) {
            	System.out.println("step 3---> celltype is numeric");
            	final int cellValue=(int) cellId.getNumericCellValue();
            	if(cellValue == Integer.parseInt(id))
            	{
            		System.out.println("step 3.1---> user id found");
            		final Cell cellPassword = row.getCell(1);
            		if(cellPassword.toString().equals(password))  {            		
            			if(Integer.toString(((int)row.getCell(2).getNumericCellValue())).equals("1")) {
            				System.out.println("step 3.2.1---> password matched and is admin");
                            al.add("1");
                            final List<String> al2 = elu.searchId(id);
                            if(al2 == null) {
                                System.out.println("Found in login table but not present in ELT");
                                return null;
                            }
                            al.addAll(al2);
                            return new Employee(al);
            			}
            			else {
            				System.out.println("step 3.2.2---> password matched and is user");
                            al.add("0");
                            final List<String> al2 = elu.searchId(id);
                            if(al2 == null) {
                                System.out.println("Found in login table but not present in ELT");
                                return null;
                            }
                            al.addAll(al2); 
                            return  new Employee(al);
            			}
            		}
            	}
            }
        }
        return null;// if not present in the table
    }

}
