<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	
	<!-- Earnings (Monthly) Card Example -->
      <div class="col-xl-8 col-md-6 mb-4">
      <a href="SwitchPages?pageName=quarterlyLeaveReport" style="text-decoration: none;">
        <div class="card border-left-info border-bottom-info shadow h-100 py-2">
          <div class="card-body">
            <div class="row no-gutters align-items-center">
              <div class="col mr-2">
                <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Planned VS Unplanned Leaves (Current Quarter)</div>
                <div class="row no-gutters align-items-center">
                  <div class="col-auto">
                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">
                    ${quarterlyLeaveCount[0]}:${quarterlyLeaveCount[1]}
                    </div>
                  </div>
                  <div class="col">
                    <div class="progress progress-sm mr-2">
                      <div class="progress-bar bg-info" role="progressbar" 
                      style="width:  ${quarterlyLeaveTypeRatio[0]}%" 
                      aria-valuenow="${quarterlyLeaveTypeRatio[0]}" aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
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
      
     <div class="col-xl-4 col-md-6 mb-4">
      <a href="SwitchPages?pageName=approveLeave" style="text-decoration: none;">
      <div class="card border-left-warning border-bottom-warning shadow h-100 py-2">
        <div class="card-body">
          <div class="row no-gutters align-items-center">
            <div class="col mr-2">
              <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Pending Requests (Quarterly)</div>
              <div class="h5 mb-0 font-weight-bold text-gray-800">${pendingLeaveNumbers} Pending Leaves</div>
            </div>
            <div class="col-auto">
              <i class="fas fa-comments fa-2x text-gray-300"></i>
            </div>           
          </div>
        </div>
      </div>
      </a>
    </div>
</div>
