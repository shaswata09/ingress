<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="SwitchPages?pageName=dashboard">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">INGRESS<sup>PMO</sup></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item">
        <a class="nav-link" href="SwitchPages?pageName=dashboard">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>
	
	
	  <c:if test='${user.getEmployeeAccessRight().equals("1")}'>
      <!-- Divider -->
      <hr class="sidebar-divider">
	
	 
      <!-- Heading -->
      <div class="sidebar-heading">
        Interface
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
     
      	<%@ include file="./navItemComponentCollapseMenu.jsp"%>
      
	  <!-- Nav Item - Pages Collapse Menu END-->  
	  
      <!-- Nav Item - Utilities Collapse Menu -->
 
      <%@ include file="./navUtilitiesCollapseMenu.jsp"%>
      </c:if>
      <!-- Nav Item - Utilities Collapse Menu END-->

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Addons
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
	  <%@ include file="./navItemPagesCollapseMenu.jsp"%>
      <!-- Nav Item - Pages Collapse Menu END-->
	
	  <!-- Chart & Table Sidebars -->
	  <c:if test='${user.getEmployeeAccessRight().equals("1")}'>
	  	  <!-- Nav Item - Charts -->
	      <li class="nav-item">
	        <a class="nav-link" href="charts.html">
	          <i class="fas fa-fw fa-chart-area"></i>
	          <span>Charts</span></a>
	      </li>
	
	      <!-- Nav Item - Tables -->
	      <li class="nav-item">
	        <a class="nav-link" href="tables.html">
	          <i class="fas fa-fw fa-table"></i>
	          <span>Tables</span></a>
	      </li>
	
	      <!-- Divider -->
	      <hr class="sidebar-divider d-none d-md-block">
	  </c:if>
	  <!-- Chart & Table Sidebars END-->
     

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->
    
