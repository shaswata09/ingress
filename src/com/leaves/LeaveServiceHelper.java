package com.leaves;

import java.util.List;

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
}
