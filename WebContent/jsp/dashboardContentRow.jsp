<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Content Row -->
    <div class="row">

      <!-- Earnings (Monthly) Card Example -->
      <div class="col-xl-3 col-md-6 mb-4">
      	<a href="SwitchPages?pageName=quarterlyLeaveReport" style="text-decoration: none;">
        <div class="card border-left-primary shadow h-100 py-2">
          <div class="card-body">
            <div class="row no-gutters align-items-center">
              <div class="col mr-2">
                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Quarterly Leave</div>
                <div class="h5 mb-0 font-weight-bold text-gray-800">Count: <c:out value="${quarterlyLeaveCount}"/></div>
              </div>
              <div class="col-auto">
                <i class="fas fa-calendar fa-2x text-gray-300"></i>
              </div>
            </div>         
          </div>
        </div>
       </a>   
      </div>
	  
	  
      <!-- Earnings (Monthly) Card Example -->
      <div class="col-xl-3 col-md-6 mb-4">
      <a href="SwitchPages?pageName=selfLeaveStatus" style="text-decoration: none;">
        <div class="card border-left-success shadow h-100 py-2">
          <div class="card-body">
            <div class="row no-gutters align-items-center">
              <div class="col mr-2">
                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Personal Leave</div>
                <div class="row no-gutters align-items-center">
                  <div class="col-auto">
                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><c:out value="${selfLeaveCount[0]}"/>:<c:out value="${selfLeaveCount[1]}"/></div>
                  </div>
                  <div class="col">
                    <div class="progress progress-sm mr-2">
                      <div class="progress-bar bg-success" role="progressbar" style="width: ${selfLeaveTypeRatio[0]}%" aria-valuenow="${selfLeaveTypeRatio[0]}" aria-valuemin="0" aria-valuemax="100"></div>
                      <div class="progress-bar bg-warning" role="progressbar" style="width: ${selfLeaveTypeRatio[1]}%" aria-valuenow="${selfLeaveTypeRatio[1]}" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-auto">
              	<i class="fas fa-hot-tub fa-2x text-gray-300"></i>                
              </div>
            </div>
          </div>
        </div>
        </a>
      </div>

      <!-- Earnings (Monthly) Card Example -->
      <div class="col-xl-3 col-md-6 mb-4">
      <a href="SwitchPages?pageName=approveLeave" style="text-decoration: none;">
        <div class="card border-left-info shadow h-100 py-2">
          <div class="card-body">
            <div class="row no-gutters align-items-center">
              <div class="col mr-2">
                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Pending Leaves</div>
                <div class="row no-gutters align-items-center">
                  <div class="col-auto">
                    <div class="h5 mb-0 font-weight-bold text-gray-800">Count: <c:out value="${pendingLeaveCount}"/></div>
                  </div>                  
                </div>
              </div>
              <div class="col-auto">
                <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
              </div>
            </div>
          </div>
        </div>
       </a>
      </div>

      <!-- Pending Requests Card Example -->
      <div class="col-xl-3 col-md-6 mb-4">
      <a href="SwitchPages?pageName=employeeDetails" style="text-decoration: none;">
        <div class="card border-left-warning shadow h-100 py-2">
          <div class="card-body">
            <div class="row no-gutters align-items-center">
              <div class="col mr-2">
                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Edit Employee</div>
                <div class="h5 mb-0 font-weight-bold text-gray-800">Count: <c:out value="${totalEmployeeCount}"/></div>
              </div>
              <div class="col-auto">
              	<i class="fas fa-portrait fa-2x text-gray-300"></i>
              </div>
            </div>
          </div>
        </div>
        </a>
      </div>
    </div>