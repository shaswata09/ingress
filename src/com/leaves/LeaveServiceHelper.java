package com.leaves;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.features.QuarterServiceHelper;

public class LeaveServiceHelper {
	
	public static int[] getLeaveTypeCount(List<DltObject> leaveList) {
    	int plannedLeaveCount = 0;
    	int unplannedLeaveCount = 0;
    	for(DltObject object : leaveList) {
    		if(object.getTypeOfLeave().equalsIgnoreCase("planned")) {
    			plannedLeaveCount += object.getNumberOfDays();
    		}
    		else if(object.getTypeOfLeave().equalsIgnoreCase("unplanned")) {
    			unplannedLeaveCount += object.getNumberOfDays();
    		}    		
    	}
    	int[] leaveTypeCount = {plannedLeaveCount, unplannedLeaveCount};
		return leaveTypeCount;    	
    }
	
	public static int[] getLeaveTypeRatioPercentage(int[] leaveTypeCount) {
		int plannedLeavePercentage = 0;
		int unplannedLeaveTypePercentage = 0;
		int totalLeave = leaveTypeCount[0]+leaveTypeCount[1];
		plannedLeavePercentage = (int)(((double)leaveTypeCount[0]/totalLeave)*100);
		unplannedLeaveTypePercentage = (int)(((double)leaveTypeCount[1]/totalLeave)*100);
		int[] leaveTypeRatioPercentage = {plannedLeavePercentage, unplannedLeaveTypePercentage};
		return leaveTypeRatioPercentage;
	}
	
	private static boolean checkIfLeaveLiesBetween(String startDate, String endDate, String checkStartDate, String checkEndDate) {
		try {
			Date startingDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date endingDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			Date checkingStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkStartDate);
			Date checkingEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(checkEndDate);
			
			if(startingDate.compareTo(checkingStartDate) * checkingStartDate.compareTo(endingDate) >= 0) 
				return true;
			else if(startingDate.compareTo(checkingEndDate) * checkingEndDate.compareTo(endingDate) >= 0)
				return true;
			else if(checkingStartDate.compareTo(startingDate) * startingDate.compareTo(checkingEndDate) >= 0)
				return true;
			
			return false;
		} catch (ParseException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
			return true;
		}
		
	}
	
	public static boolean checkLeaveConflict(String employeeID, String typeOfLeave, String leaveStartDate, String leaveEndDate) {
    	List<DltObject> employeeapprovedLeaves = null;
    	if(typeOfLeave.equalsIgnoreCase("unplanned")) {
    		employeeapprovedLeaves = new DLT(QuarterServiceHelper.findQuarterByDate(leaveStartDate)).showTable(employeeID);
    	}
    	else if(typeOfLeave.equalsIgnoreCase("planned")) {
    		employeeapprovedLeaves = new DLT(QuarterServiceHelper.findQuarterByDate(leaveStartDate)).showTable(employeeID);
    		employeeapprovedLeaves.addAll(new TempTable().showTable(employeeID));
    	}
    	
    	
    	for(DltObject approvedLeave : employeeapprovedLeaves) {
    		if(checkIfLeaveLiesBetween(approvedLeave.getStartDate(), approvedLeave.getEndDate(), leaveStartDate, leaveEndDate))
    			return true;
    	}
    	return false;
    }
	
	public static List<DltObject> findYearlyLeaveList(String year, String employeeID){
		List<String> yearlyQuartersList = QuarterServiceHelper.findQuartersByYear(year);
		List<DltObject> yearlyLeaveList = new ArrayList<DltObject>();
		
		for(String quarter : yearlyQuartersList) {
			yearlyLeaveList.addAll(new DLT(quarter).showTable(employeeID));
		}
		return yearlyLeaveList;
	}
	
	public static int[] findMonthlyLeaveCount(List<DltObject> yearlyLeaveList) {
		int[] monthlyLeaveCount = new int [12];
		for(DltObject leave : yearlyLeaveList) {
			if(leave.getLeaveMonth().equalsIgnoreCase("January"))
				monthlyLeaveCount[0]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("February"))
				monthlyLeaveCount[1]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("March"))
				monthlyLeaveCount[2]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("April"))
				monthlyLeaveCount[3]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("May"))
				monthlyLeaveCount[4]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("June"))
				monthlyLeaveCount[5]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("July"))
				monthlyLeaveCount[6]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("August"))
				monthlyLeaveCount[7]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("September"))
				monthlyLeaveCount[8]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("October"))
				monthlyLeaveCount[9]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("November"))
				monthlyLeaveCount[10]+=leave.getNumberOfDays();
			else if(leave.getLeaveMonth().equalsIgnoreCase("December"))
				monthlyLeaveCount[11]+=leave.getNumberOfDays();
		}
		return monthlyLeaveCount;
	}
	
	public static int[] findYearlyLeaveCount(String year, String employeeID) {
		return findMonthlyLeaveCount(findYearlyLeaveList(year, employeeID));
	}
	
	public static void main(String args[]) {
		for(int i : findYearlyLeaveCount("2020", null))
			System.out.println(i);
	}
}
