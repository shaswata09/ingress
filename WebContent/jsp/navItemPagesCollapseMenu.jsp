<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


  <li class="nav-item <% if((session.getAttribute("pageName").equals("employeeDetails")) || 
		  (session.getAttribute("pageName").equals("register")) || 
		  (session.getAttribute("pageName").equals("approveLeave")) || 
		  (session.getAttribute("pageName").equals("myProfile")) || 
		  (session.getAttribute("pageName").equals("addLeave")) ||
		  (session.getAttribute("pageName").equals("selfLeaveStatus"))){	out.write("active");} %>">
    <a class="nav-link <% if(!((session.getAttribute("pageName").equals("employeeDetails")) || 
    		(session.getAttribute("pageName").equals("register")) || 
    		(session.getAttribute("pageName").equals("approveLeave")) || 
    		(session.getAttribute("pageName").equals("myProfile")) || 
    		(session.getAttribute("pageName").equals("addLeave")) ||
    		(session.getAttribute("pageName").equals("selfLeaveStatus")))){	out.write("collapsed");} %>" 
    		href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
      <i class="fas fa-exclamation"></i>&nbsp;
      <span>Actions</span>
    </a>
    <div id="collapsePages" class="collapse <% if((session.getAttribute("pageName").equals("employeeDetails")) || 
    		(session.getAttribute("pageName").equals("register")) || 
    		(session.getAttribute("pageName").equals("approveLeave")) || 
    		(session.getAttribute("pageName").equals("myProfile")) || 
    		(session.getAttribute("pageName").equals("addLeave")) || 
    		(session.getAttribute("pageName").equals("selfLeaveStatus"))){	out.write("show");} %>"
    		 aria-labelledby="headingPages" data-parent="#accordionSidebar">
      <div class="bg-white py-2 collapse-inner rounded">
        <h6 class="collapse-header">Employee ISSUES:</h6>
        <c:choose>
	        <c:when test='${user.getEmployeeAccessRight().equals("1")}'>
	        	<a class="collapse-item <% if((session.getAttribute("pageName")!=null) && (session.getAttribute("pageName").equals("employeeDetails"))){	out.write("active");} %>" href="SwitchPages?pageName=employeeDetails"><i class="fas fa-address-book"></i>&nbsp;Employee Details</a>         
	        	<a class="collapse-item <% if((session.getAttribute("pageName")!=null) && (session.getAttribute("pageName").equals("register"))){	out.write("active");} %>" href="SwitchPages?pageName=register"><i class="fas fa-user-plus"></i>&nbsp;Add Employee</a>
	        	<a class="collapse-item <% if((session.getAttribute("pageName")!=null) && (session.getAttribute("pageName").equals("approveLeave"))){	out.write("active");} %>" href="SwitchPages?pageName=approveLeave"><i class="fas fa-smile"></i>&nbsp;Approve Leave</a>

	        </c:when>
	        <c:when test='${user.getEmployeeAccessRight().equals("0")}'>
	        	<a class="collapse-item <% if(session.getAttribute("pageName").equals("myProfile")){	out.write("active");} %>" href="SwitchPages?pageName=myProfile"><i class="fas fa-id-card-alt"></i>&nbsp;My Profile</a>
	        </c:when>	       
        </c:choose>
        <a class='collapse-item <% if((session.getAttribute("pageName")!=null) && (session.getAttribute("pageName").equals("addLeave"))){	out.write("active");} %>' href="SwitchPages?pageName=addLeave"><i class="fas fa-hot-tub"></i>&nbsp;Add Leave</a>       
        <c:if test='${user.getEmployeeAccessRight().equals("1")}'>
        	<div class="collapse-divider"></div>
        	<h6 class="collapse-header">Personal Pages:</h6>
        </c:if>
        <a class="collapse-item <% if(session.getAttribute("pageName").equals("selfLeaveStatus")){	out.write("active");} %>" href="SwitchPages?pageName=selfLeaveStatus"><i class="fas fa-question-circle"></i>&nbsp;Leave Status</a>  
      </div>
    </div>
  </li>

  
  