package com.enums;

public enum EmployeePages {
	dashboard, myProfile, addLeave, error, changePassword, selfLeaveStatus;

	public static boolean contains(String pageName)
	{
	    for(EmployeePages page:values())
	         if (page.name().equals(pageName)) 
	            return true;
	    return false;
	}
};