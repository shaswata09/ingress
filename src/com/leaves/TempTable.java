package com.leaves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

 

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.PMOProperties.ExcelFileDetails;
import com.features.QuarterServiceHelper;

 

public class TempTable {
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    FileInputStream file;
    final String fullPath = ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.TEMP_TABLE;
    CreationHelper createHelper;
    CellStyle dateCellStyle;
    
    public List<String> getDetails(int empId,String startDate,String endDate)
    {
    	List<String> list= new ArrayList<String>();
    	try {
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);            
            this.sheet = this.workbook.getSheetAt(0);
        } catch(final Exception exception) {
        	exception.printStackTrace();
        }
		
        
        final Iterator<Row> rowIterator = this.sheet.iterator();
        int countRow = 0;
        while(rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if(countRow == 0) {
                countRow++;
                continue;
            }
            if(null == row) {
                continue;
            }
        
            if(row.getCell(0).getNumericCellValue()==empId && row.getCell(3).getStringCellValue().equals(startDate)&&row.getCell(4).getStringCellValue().equals(endDate))
            {
            	final Iterator<Cell> cellIterator=row.cellIterator();
            	while(cellIterator.hasNext())
            	{
            		Cell cell= cellIterator.next();
            		CellType type=cell.getCellType();
            		if(type==CellType.STRING)
            		{
            			String s=cell.getStringCellValue();
            			list.add(s);
            		}
            		else if(type==CellType.NUMERIC)
            		{
            			String s=Double.toString(cell.getNumericCellValue());
            			list.add(s);
            		}
            	}
            }
            
        }
        return list;
    	
    }
    
    public void deleteRow(final int empId, final String startDate, final String endDate) {
        final String fullPath = this.fullPath;
        try {
            final FileInputStream file = new FileInputStream(new File(fullPath));
            final XSSFWorkbook workbook = new XSSFWorkbook(file);
            final XSSFSheet sheet = workbook.getSheetAt(0);
            // this.printSheet(sheet);
            final int number = sheet.getLastRowNum();

            /*
             * block: { final Row temp = sheet.getRow(392); final Cell temp2 = temp.getCell(2);
             * System.out.println(temp2.getStringCellValue()); }
             */
   
            for(int i = 1; i <= number; i++) {
                // System.out.println("Now in i=== " + i);
                final Row row = sheet.getRow(i);
                if(null == row) {
                    // System.out.println("row is null and the number is: " + i);
                    continue;
                }
                final Cell temp0 = row.getCell(0);
                final Cell temp3 = row.getCell(3);
                final Cell temp4 = row.getCell(4);
                if(null == temp0 || null == temp3 || null == temp4) {
                    // System.out.println("temp is null: " + i);
                }
                final CellType type0 = temp0.getCellType();
                final CellType type3 = temp3.getCellType();
                final CellType type4 = temp4.getCellType();
                double tempEmpId = 0;
                String tempStartDate = "";
                String tempEndDate = "";
                try {
                    if(type0 == CellType.NUMERIC) {
                        tempEmpId = temp0.getNumericCellValue();
                    } else {
                        tempEmpId = Double.parseDouble(temp0.getStringCellValue());
                    }
                    if(type3 == CellType.NUMERIC) {
                        tempStartDate = Double.toString(temp3.getNumericCellValue());
                    } else {
                        tempStartDate = temp3.getStringCellValue();
                    }
                    if(type4 == CellType.NUMERIC) {
                        tempEndDate = Double.toString(temp4.getNumericCellValue());
                    } else {
                        tempEndDate = temp4.getStringCellValue();
                    }
                } catch(final Exception e) {
                    continue;
                }

                if(empId == tempEmpId && startDate.equals(tempStartDate) && endDate.equals(tempEndDate)) {
                    // sheet.removeRow(row);
                    // final int rowNum = row.getRowNum();

                    if(i >= 0 && i <= number) {
                        sheet.removeRow(row);
                        // sheet.shiftRows(i + 1, temp2++, -1);
                        // sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
                        // row.getCell(4).setCellValue(12);
                        // this.printSheet(sheet);
                        i--;
                    }
                }

            }
            final FileOutputStream fileOut = new FileOutputStream(fullPath);
            workbook.write(fileOut);
            workbook.close();
            fileOut.close();
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean decision(int empId, String startDate, String endDate, boolean approval)
	{
    	List<String> list=getDetails(empId,startDate,endDate);
		if(approval)
		{
			list.set(10,"approved");
			try {
				if(!LeaveServiceHelper.checkLeaveConflict(String.valueOf(empId), "planned", startDate, endDate)) {
					new DLT(QuarterServiceHelper.findQuarterByDate(startDate)).addToTable(list);
					this.deleteRow(empId, startDate, endDate);
					return true;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}
		}
		else
		{
			list.set(10, "rejected");
			try {
				new RejectedListTable().addToTable(list);
				this.deleteRow(empId, startDate, endDate);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
    
    public List<List<String>> returnList(String employeeID) throws IOException {
        this.file = new FileInputStream(new File(this.fullPath));
        this.workbook = new XSSFWorkbook(this.file);
        this.sheet = this.workbook.getSheetAt(0);
        final List<List<String>> finalList = new ArrayList<List<String>>();

        for(int i = 1; i <= this.sheet.getLastRowNum(); i++) {
            final List<String> al = new ArrayList<String>();
            final Row row = this.sheet.getRow(i);
            if(null == row) {
                continue;
            }
            final Cell cell = row.getCell(1);
            if(null == cell) {
                continue;
            }

            if(null == employeeID) {
            	for(int j = 0; j < 11; j++) {
                    Cell cell2 = row.getCell(j);
                    CellType ct = cell2.getCellType();
                    if(ct == CellType.STRING) {
                        al.add(cell2.getStringCellValue());
                    } else if(ct == CellType.NUMERIC) {
                        final long temp = (long)cell2.getNumericCellValue();
                        al.add(Long.toString(temp));
                    }
                }
            	finalList.add(al);
            }
            else {            	
            	if(row.getCell(0).getCellType() == CellType.NUMERIC && 
            			(Long.toString((long) row.getCell(0).getNumericCellValue()).equalsIgnoreCase(employeeID))) {                		
            		for(int j = 0; j < 11; j++) {
                        Cell cell2 = row.getCell(j);
                        CellType ct = cell2.getCellType();
                        if(ct == CellType.STRING) {
                            al.add(cell2.getStringCellValue());
                        } else if(ct == CellType.NUMERIC) {
                            long temp = (long)cell2.getNumericCellValue();
                            al.add(Long.toString(temp));
                        }
                    }                		
            		finalList.add(al);            		
            	}                	
            }               
        }        
        return finalList;// returns null if finds nothing-- so have to handle it
    } 

    public List<DltObject> showTable(String employeeID){
    	try {
	        final DltObject obj = new DltObject();
	        final List<DltObject> list = new ArrayList<DltObject>();
	        final List<List<String>> list2 = returnList(employeeID);
	        if(null == employeeID) {
	        	for(int i = 0; i < list2.size(); i++) {
	                final List<String> temp = list2.get(i);
	                list.add(obj.returnObject(temp));
	            }
	        }
	        else {
	        	for(int i = 0; i < list2.size(); i++) {
	                final List<String> temp = list2.get(i);
	                list.add(obj.returnObject(temp));
	            }
	        }       
	        return list;
    	}
		catch (Exception exception) {
			System.out.println("Null Pointer Exception due to missing file.");
			return null;
		}    
    } 
}