<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Content Row -->

<div class="row">

  <!-- Area Chart -->
  <div class="col-xl-8 col-lg-7">
    <div class="card shadow mb-4">
      <!-- Card Header - Dropdown -->
      <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary">Yearly Leaves(Personal)</h6>        
      </div>
      <!-- Card Body -->
      <div class="card-body">
        <div class="chart-area">
          <canvas id="myAreaChart"></canvas>
        </div>
        <input id="januaryLeaveCount" type="hidden" value="${monthlyLeaveCount[0]}"/>
        <input id="februaryLeaveCount" type="hidden" value="${monthlyLeaveCount[1]}"/>
        <input id="marchLeaveCount" type="hidden" value="${monthlyLeaveCount[2]}"/>
        <input id="aprilLeaveCount" type="hidden" value="${monthlyLeaveCount[3]}"/>
        <input id="mayLeaveCount" type="hidden" value="${monthlyLeaveCount[4]}"/>
        <input id="juneLeaveCount" type="hidden" value="${monthlyLeaveCount[5]}"/>
        <input id="julyLeaveCount" type="hidden" value="${monthlyLeaveCount[6]}"/>
        <input id="augustLeaveCount" type="hidden" value="${monthlyLeaveCount[7]}"/>
        <input id="septemberLeaveCount" type="hidden" value="${monthlyLeaveCount[8]}"/>
        <input id="octoberLeaveCount" type="hidden" value="${monthlyLeaveCount[9]}"/>
        <input id="novemberLeaveCount" type="hidden" value="${monthlyLeaveCount[10]}"/>
        <input id="decemberLeaveCount" type="hidden" value="${monthlyLeaveCount[11]}"/>
      </div>
    </div>
  </div>

  <!-- Pie Chart -->
  <div class="col-xl-4 col-lg-5">
    <div class="card shadow mb-4">
      <!-- Card Header - Dropdown -->
      <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary">Leave Ratio(Personal)</h6>        
      </div>
      <!-- Card Body -->
      <div class="card-body">
        <div class="chart-pie pt-4 pb-2">
          <canvas id="myPieChart"></canvas>
        </div>
        <input id="pannedLeaveCount" type="hidden" value="${quarterlyLeaveCount[0]}"/>
        <input id="unpannedLeaveCount" type="hidden" value="${quarterlyLeaveCount[1]}"/>
        <input id="pendingLeaveCount" type="hidden" value="${pendingLeaveCount}"/>
        <div class="mt-4 text-center small">
          <span class="mr-2">
            <i class="fas fa-circle text-success"></i> Planned
          </span>
          <span class="mr-2">
            <i class="fas fa-circle text-primary"></i> Unplanned
          </span>          
          <span class="mr-2">
            <i class="fas fa-circle text-info"></i> Pending
          </span>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Page level custom scripts -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="js/demo/chart-area-demo.js"></script>
<script src="js/demo/chart-pie-demo.js"></script>
  
