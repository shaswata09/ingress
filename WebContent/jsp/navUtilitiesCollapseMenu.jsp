<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<li class="nav-item <% if((session.getAttribute("pageName").equals("quarterlyLeaveReport"))){	out.write("active");} %>">
	  <a class="nav-link <% if(!((session.getAttribute("pageName").equals("quarterlyLeaveReport"))  )){	out.write("collapsed");} %>" 
    		href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
	    <i class="fas fa-fw fa-chart-area"></i>
	    <span>Reports</span>
	  </a>
	  <div id="collapseUtilities" class="collapse <% if((session.getAttribute("pageName").equals("quarterlyLeaveReport"))){	out.write("show");} %>"
    		aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
	    <div class="bg-white py-2 collapse-inner rounded">
	      <h6 class="collapse-header">LEAVES:</h6>
	      <a class="collapse-item <% if((session.getAttribute("pageName")!=null) && (session.getAttribute("pageName").equals("quarterlyLeaveReport"))){	out.write("active");} %>" href="SwitchPages?pageName=quarterlyLeaveReport"><i class="fas fa-thermometer-quarter"></i>&nbsp;Quarterly Report</a>
	      <a class="collapse-item" href="leaveSummary.jsp"><i class="fas fa-poll-h"></i>&nbsp;Leave Summary</a>
	    </div>
	  </div>
	</li>