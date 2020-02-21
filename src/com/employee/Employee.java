package com.employee;

import java.util.List;



public class Employee {
    private String employeeName;
    private String employeeId;
    private String employeeMailID;
    private String employeeTeam;
    private String employeeLocation;
    private String employeeRole;
    private String employeeMobile;
    private String employeeSOW;
    private String employeeEmergencyContact;
    private String employeeAccessRight; // user or admin
    private List<String> employeeNavAccessRight;

 

    public Employee() {
        this.employeeName = null;
        this.employeeId = null;
        this.employeeMailID = null;
        this.employeeTeam = null;
        this.employeeLocation = null;
        this.employeeRole = null;
        this.employeeMobile = null;
        this.employeeSOW = null;
        this.employeeEmergencyContact = null;
        this.employeeAccessRight = null;
        this.employeeNavAccessRight = null;
    }    
    
    public Employee (final List<String> list) {       
	        this.employeeName=list.get(1);
	        this.employeeId=list.get(2);
	        this.employeeMailID=list.get(9);
	        this.employeeTeam=list.get(3);
	        this.employeeLocation=list.get(4);
	        this.employeeRole=list.get(5);
	        this.employeeSOW=list.get(6);
	        this.employeeMobile=list.get(7);
	        this.employeeEmergencyContact=list.get(8);
	        this.employeeAccessRight=list.get(0);
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(final String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(final String emplyeeId) {
        this.employeeId = emplyeeId;
    }

    public String getEmployeeMailID() {
        return this.employeeMailID;
    }

    public void setEmployeeMailID(final String employeeMailID) {
        this.employeeMailID = employeeMailID;
    }

    public String getEmployeeTeam() {
        return this.employeeTeam;
    }

    public void setEmployeeTeam(final String employeeTeam) {
        this.employeeTeam = employeeTeam;
    }

    public String getEmployeeLocation() {
        return this.employeeLocation;
    }

    public void setEmployeeLocation(final String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }

    public String getEmployeeRole() {
        return this.employeeRole;
    }

    public void setEmployeeRole(final String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeMobile() {
        return this.employeeMobile;
    }

    public void setEmployeeMobile(final String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public String getEmployeeSOW() {
        return this.employeeSOW;
    }

    public void setEmployeeSOW(final String employeeSOW) {
        this.employeeSOW = employeeSOW;
    }

    public String getEmployeeEmergencyContact() {
        return this.employeeEmergencyContact;
    }

    public void setEmployeeEmergencyContact(final String employeeEmergencyContact) {
        this.employeeEmergencyContact = employeeEmergencyContact;
    }

    public String getEmployeeAccessRight() {
        return this.employeeAccessRight;
    }

    public void setEmployeeAccessRight(final String employeeAccessRight) {
        this.employeeAccessRight = employeeAccessRight;
    }

    public List<String> getEmployeeNavAccessRight() {
        return this.employeeNavAccessRight;
    }

    public void setEmployeeNavAccessRight(final List<String> employeeNavAccessRight) {
        this.employeeNavAccessRight = employeeNavAccessRight;
    }

}