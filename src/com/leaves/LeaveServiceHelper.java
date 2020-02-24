package com.leaves;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
