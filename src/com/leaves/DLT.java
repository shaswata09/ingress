package com.leaves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.*;
import com.PMOProperties.*;
import com.features.*;;

public class DLT {    
	final String filePath = ExcelFileDetails.LEAVES_FOLDER_PATH;
	final String fileName = ExcelFileDetails.DIGITAL_LEAVE_TRACKER;
    String fullPath = null;
    FileInputStream file;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    
    public DLT(String quarterName) {
        try {      
        	this.fullPath = this.filePath + quarterName + "\\" + this.fileName;
            this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);            
            this.sheet = this.workbook.getSheetAt(0);
        } catch(final Exception exception) {
        	System.out.println("File Not found Exception.");
        }
    }

    public boolean addLeaveIntoTables(final int empId, final String typeOfLeave, final String start, final String end)
            throws IOException, ParseException {
    	
    	if(LeaveServiceHelper.checkLeaveConflict(String.valueOf(empId), typeOfLeave, start, end)) {
    		return false;
    	}
    	else {
    		 final EmpLookUp empObj = new EmpLookUp();
    	        final List<String> empDetails = empObj.searchId(Integer.toString(empId));
    	        final List<List<String>> tempLeave = getLeave(start, end);
    	        for(int i = 0; i < tempLeave.size(); i++) {

    	            final List<String> finalList = new ArrayList<String>();

    	            final List<String> temp = tempLeave.get(i);
    	            final String month = temp.get(0).split("-")[1];
    	            final long noOfDays = this.dateDiff(temp.get(0), temp.get(1))
    	                    - getWorkingDaysBetweenTwoDates(temp.get(0), temp.get(1));

    	            if(typeOfLeave.equalsIgnoreCase("unplanned")) {
    	                finalList.add(empDetails.get(1));
    	                finalList.add(empDetails.get(0));
    	                finalList.add(empDetails.get(2));
    	                finalList.add(temp.get(0));
    	                finalList.add(temp.get(1));
    	                finalList.add(getMonth(month));
    	                finalList.add(Long.toString(noOfDays));
    	                finalList.add("unplanned");
    	                finalList.add(empDetails.get(3));
    	                finalList.add(empDetails.get(5));
    	                finalList.add("approved");
    	                addToTable(finalList);

    	            } else if(typeOfLeave.equalsIgnoreCase("planned")) {
    	                final TempTable tableObj = new TempTable();
    	                finalList.add(empDetails.get(1));
    	                finalList.add(empDetails.get(0));
    	                finalList.add(empDetails.get(2));
    	                finalList.add(temp.get(0));
    	                finalList.add(temp.get(1));
    	                finalList.add(getMonth(month));
    	                finalList.add(Long.toString(noOfDays));
    	                finalList.add("planned");
    	                finalList.add(empDetails.get(3));
    	                finalList.add(empDetails.get(5));
    	                finalList.add("pending");

    	                tableObj.insert(finalList);
    	            }
    	        }
    	        return true;
    	}      
    }

    public List<List<String>> getLeave(final String startMain, final String endMain) {
        
        
        final List<List<String>> finalList = new ArrayList<List<String>>();

        String start = startMain;
        final String[] date = start.split("-");
        final String end = endMain;
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        final int day = Integer.parseInt(date[2]);
        long dateDiff = dateDiff(start, end);
        long daysInMon = daysInMonth(year, month) - day + 1;
       

        while(true) {
            if(dateDiff == 0) {
                break;
            }
            final List<String> temp = new ArrayList<String>();
            byte flag=0;
            if(dateDiff >= daysInMon) {
            	flag=1;
                dateDiff -= daysInMon;
                temp.add(start);
                temp.add(Long.toString(year) + "-" + Long.toString(month) + "-"
                        + Long.toString(daysInMonth(year, month)));
                finalList.add(temp);
                month++;

                if(month == 13) {
                    month = 1;
                    year++;
                }
                daysInMon = daysInMonth(year, month);
                start = Long.toString(year) + "-" + Long.toString(month) + "-01";
            } else {
            	if(flag==0)
            	{
            		temp.add(start);
                    temp.add(end);
                    finalList.add(temp);
            	}
            	else {
                temp.add(Long.toString(year) + "-" + Long.toString(month) + "-" + Long.toString(1));
                temp.add(end);
                finalList.add(temp);}
                break;
            }
        }
        return finalList;

    }

    public long dateDiff(final String dateStart, final String dateStop) {


        // HH converts hour in 24 hours format (0-23), day calculation
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = null;
        Date d2 = null;
        long diffDays = 0;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            // in milliseconds
            final long diff = d2.getTime() - d1.getTime();
            diffDays = diff / (24 * 60 * 60 * 1000);

        } catch(final Exception e) {
            e.printStackTrace();

        }
        return diffDays + 1;
    }

    public int daysInMonth(final int year, final int month) {
        final YearMonth yearMonthObject = YearMonth.of(year, month);
        final int daysInMonth = yearMonthObject.lengthOfMonth();
        return daysInMonth;
    }


    public void addToTable(final List<String> finalList) throws IOException {
 
        final CreationHelper createHelper = this.workbook.getCreationHelper();
        final CellStyle dateCellStyle = this.workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        int lastRowNumber = this.sheet.getLastRowNum();

        final Row row = this.sheet.createRow(++lastRowNumber);
        for(int i = 0; i < finalList.size(); i++) {
            final Cell cell = row.createCell(i);
            if(i == 3 || i == 4) {
                cell.setCellValue(finalList.get(i));
                cell.setCellStyle(dateCellStyle);

            } else if(i == 6 || i == 0) {
                cell.setCellValue(Double.parseDouble(finalList.get(i)));

            } else {
                cell.setCellValue(finalList.get(i));
            }

        }
        final FileOutputStream out = new FileOutputStream(
                new File(fullPath));
        this.workbook.write(out);
        out.close();
    }

    public String getMonth(final String s) {
        final byte mon = Byte.parseByte(s);
        switch(mon) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return null;
        }
    }

    public static int getWorkingDaysBetweenTwoDates(final String startDate, final String endDate)
            throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date date1 = format.parse(startDate);
        final Date date2 = format.parse(endDate);
        final Calendar startCal = Calendar.getInstance();
        startCal.setTime(date1);

        final Calendar endCal = Calendar.getInstance();
        endCal.setTime(date2);

        int weekEnds = 0;

        if(startCal.getTimeInMillis() > endCal.getTimeInMillis()) {

            startCal.setTime(date2);
            endCal.setTime(date1);
        }

        while(startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
            // excluding start date

            if(startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                ++weekEnds;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } // excluding end date

        return weekEnds;
    }   
    
    public List<DltObject> showTable(String employeeID){			//call to get quarterly leave report
    	try {    		
            final DltObject obj = new DltObject();
            final List<DltObject> list = new ArrayList<DltObject>();            
            final List<List<String>> list2 = returnList(employeeID);
            for(int i = 0; i < list2.size(); i++) {
                final List<String> temp = list2.get(i);
                list.add(obj.returnObject(temp));
            }
            return list;
    	}
    	catch (Exception exception) {
    		System.out.println("Null Pointer Exception due to missing file.");
    		return null;
    	}    	
    }     

    private List<List<String>> returnList(String employeeID) throws IOException {
    	try {
    		this.file = new FileInputStream(new File(this.fullPath));
            this.workbook = new XSSFWorkbook(this.file);
            this.sheet = this.workbook.getSheetAt(0);
            List<List<String>> finalList = new ArrayList<List<String>>();
            
        	for(int i = 2; i <= this.sheet.getLastRowNum(); i++) {
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
    	catch(Exception exception) {
    		System.out.println("File Not found Exception.");
    		return null;
    	}        
    }
}
