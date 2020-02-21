package com.enums;

public enum AdminPages {
	
	dashboard, employeeDetails, addLeave, register, employeeEdit, changePassword, myProfile, selfLeaveStatus, approveLeave, error,
	quarterlyLeaveReport;

	public static boolean contains(String pageName)
	{
	    for(AdminPages page:values())
	         if (page.name().equals(pageName)) 
	            return true;
	    return false;
	}
}
