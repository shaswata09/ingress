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

 

public class TempTable {
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    FileInputStream file;
    final String fullPath = ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.TEMP_TABLE;
    CreationHelper createHelper;
    CellStyle dateCellStyle;

    public void insert(final List<String> finalList) throws IOException {

        try {
            System.out.println("im in insert");
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);
            this.sheet = this.workbook.getSheetAt(0);
            this.createHelper = this.workbook.getCreationHelper();
            this.dateCellStyle = this.workbook.createCellStyle();
            this.dateCellStyle.setDataFormat(this.createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        } catch(final IOException io) { // if the file is not present then it will create the file
                                        // first and call again
            System.out.println("I am here");
            // Blank workbook
            this.workbook = new XSSFWorkbook();

            // Create a blank sheet
            this.sheet = this.workbook.createSheet("Temp Table");
            int lastRowNumber = this.sheet.getLastRowNum();
            final FileOutputStream out = new FileOutputStream(
                    new File(ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.TEMP_TABLE));
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
            insert(finalList);
            return;
        }
        int lastRowNumber = this.sheet.getLastRowNum();

        final Row row = this.sheet.createRow(++lastRowNumber);
        for(int i = 0; i < 11; i++) {
            final Cell cell = row.createCell(i);
            if(i == 0) {
                cell.setCellValue(Integer.parseInt(finalList.get(i)));
            } else if(i == 1) {
                cell.setCellValue(finalList.get(i));
            } else if(i == 2) {
                cell.setCellValue(finalList.get(i));
            } else if(i == 3) {
                cell.setCellValue(finalList.get(i));
                cell.setCellStyle(this.dateCellStyle);
            } else if(i == 4) {
                cell.setCellValue(finalList.get(i));
                cell.setCellStyle(this.dateCellStyle);
            } else if(i == 5) {
                cell.setCellValue(finalList.get(i));
            } else if(i == 6) {
                cell.setCellValue(Long.parseLong(finalList.get(i)));
            } else if(i == 7) {
                cell.setCellValue(finalList.get(i));
            } else if(i == 8) {
                cell.setCellValue(finalList.get(i));
            } else if(i == 9) {
                cell.setCellValue(finalList.get(i));
            } else if(i == 10) {
                cell.setCellValue(finalList.get(i));
            }
        }
        final FileOutputStream out = new FileOutputStream(
                new File(ExcelFileDetails.PROJECT_FOLDER_PATH + ExcelFileDetails.TEMP_TABLE));
        this.workbook.write(out);
        out.close();

    }
    List<String> getDetails(int empId,String startDate,String endDate)
    {
    	List<String> list= new ArrayList<String>();
    	try {
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);            
            this.sheet = this.workbook.getSheetAt(0);
        } catch(final Exception e) {
            System.out.println(e);
        }
		
        
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
        final String fullPath = "C:\\Users\\1604357\\Desktop\\Leave_Management\\PMO Application\\PMO Application\\TempTable.xlsx";
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
            int temp2 = number;
            for(int i = 1; i <= number; i++) {
                // System.out.println("Now in i=== " + i);
                final Row row = sheet.getRow(i);
                if(row == null) {
                    // System.out.println("row is null and the number is: " + i);
                    continue;
                }
                final Cell temp0 = row.getCell(0);
                final Cell temp3 = row.getCell(3);
                final Cell temp4 = row.getCell(4);
                if(temp0 == null || temp3 == null || temp4 == null) {
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
            fileOut.close();
        } catch(final Exception e) {
            e.printStackTrace();
        }
    }
    
    void decision(int empId, String startDate, String endDate, boolean approval)
	{
    	List<String> list=getDetails(empId,startDate,endDate);
		if(approval)
		{
			list.set(10,"approved");
			try {
				new DLT().addToTable(list);
				this.deleteRow(empId, startDate, endDate);
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
	}
    
    public List<List<String>> returnList() throws IOException {
        this.file = new FileInputStream(new File(this.fullPath));
        this.workbook = new XSSFWorkbook(this.file);
        this.sheet = this.workbook.getSheetAt(0);
        final List<List<String>> finalList = new ArrayList<List<String>>();

        for(int i = 1; i <= this.sheet.getLastRowNum(); i++) {
            final List<String> al = new ArrayList<String>();
            final Row row = this.sheet.getRow(i);
            if(row == null) {
                continue;
            }
            final Cell cell = row.getCell(1);
            if(cell == null) {
                System.out.println("cell is null");
                continue;
            }

            for(int j = 0; j < 11; j++) {
                final Cell cell2 = row.getCell(j);
                final CellType ct = cell2.getCellType();
                if(ct == CellType.STRING) {
                    al.add(cell2.getStringCellValue());
                } else if(ct == CellType.NUMERIC) {
                    final long temp = (long)cell2.getNumericCellValue();
                    al.add(Long.toString(temp));
                }
            }

            finalList.add(al);
        }
        return finalList;// returns null if finds nothing-- so have to handle it
    } 

    public List<TempTableObject> showTable() throws IOException {
        final TempTableObject obj = new TempTableObject();
        final List<TempTableObject> list = new ArrayList<TempTableObject>();
        final List<List<String>> list2 = returnList();
        for(int i = 1; i < list2.size(); i++) {
            final List<String> temp = list2.get(i);
            list.add(obj.returnObject(temp));
        }
        return list;
    } 
}