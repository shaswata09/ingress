package com.leaves;

import java.util.List;

public class TempTableObject {
    private int empId;
    private String empName;
    private String project;
    private String startDate;
    private String endDate;
    private String leaveMonth;
    private int numberOfDays;
    private String typeOfLeave;
    private String location;
    private String sow;
    private String comment;

    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(final int pEmpId) {
        this.empId = pEmpId;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(final String pEmpName) {
        this.empName = pEmpName;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(final String pProject) {
        this.project = pProject;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(final String pStartDate) {
        this.startDate = pStartDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final String pEndDate) {
        this.endDate = pEndDate;
    }

    public String getLeaveMonth() {
        return this.leaveMonth;
    }

    public void setLeaveMonth(final String pLeaveMonth) {
        this.leaveMonth = pLeaveMonth;
    }

    public int getNumberOfDays() {
        return this.numberOfDays;
    }

    public void setNumberOfDays(final int pNumberOfDays) {
        this.numberOfDays = pNumberOfDays;
    }

    public String getTypeOfLeave() {
        return this.typeOfLeave;
    }

    public void setTypeOfLeave(final String pTypeOfLeave) {
        this.typeOfLeave = pTypeOfLeave;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(final String pLocation) {
        this.location = pLocation;
    }

    public String getSow() {
        return this.sow;
    }

    public void setSow(final String pSow) {
        this.sow = pSow;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String pComment) {
        this.comment = pComment;
    }

    public TempTableObject returnObject(final List<String> list) {
        final TempTableObject obj = new TempTableObject();
        for(int i = 0; i < list.size(); i++) {
            if(i == 0) {
                obj.setEmpId(Integer.parseInt(list.get(i)));
            } else if(i == 1) {
                obj.setEmpName(list.get(i));
            } else if(i == 2) {
                obj.setProject(list.get(i));
            } else if(i == 3) {
                obj.setStartDate(list.get(i));
            } else if(i == 4) {
                obj.setEndDate(list.get(i));
            } else if(i == 5) {
                obj.setLeaveMonth(list.get(i));
            } else if(i == 6) {
                obj.setNumberOfDays(Integer.parseInt(list.get(i)));
            } else if(i == 7) {
                obj.setTypeOfLeave(list.get(i));
            } else if(i == 8) {
                obj.setLocation(list.get(i));
            } else if(i == 9) {
                obj.setSow(list.get(i));
            } else if(i == 10) {
                obj.setComment(list.get(i));
            }
        }
        return obj;
    }
}