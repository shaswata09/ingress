<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Content Row -->

<div class="row">

  <!-- Area Chart -->
  <div class="col-xl-8 col-lg-7">
    <div class="card shadow mb-4">
      <!-- Card Header - Dropdown -->
      <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h6 class="m-0 font-weight-bold text-primary">Yearly Leaves(Project)</h6>        
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
        <h6 class="m-0 font-weight-bold text-primary">Leave Ratio</h6>        
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

<!-- <script type="text/javascript">
	//Area Chart Example
	var ctx = document.getElementById("myAreaChart");
	var myLineChart = new Chart(ctx, {
	  type: 'line',
	  data: {
	    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
	    datasets: [{
	      label: "Earnings",
	      lineTension: 0.3,
	      backgroundColor: "rgba(78, 115, 223, 0.05)",
	      borderColor: "rgba(78, 115, 223, 1)",
	      pointRadius: 3,
	      pointBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointBorderColor: "rgba(78, 115, 223, 1)",
	      pointHoverRadius: 3,
	      pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointHoverBorderColor: "rgba(78, 115, 223, 1)",
	      pointHitRadius: 10,
	      pointBorderWidth: 2,
	      data: [0, 10000, 5000, 15000, 10000, 20000, 15000, 25000, 20000, 30000, 25000, 50000],
	    },
	    /* {
	        label: "Earnings1",
	        lineTension: 0.3,
	        backgroundColor: "rgba(78, 115, 223, 0.05)",
	        borderColor: "rgba(235, 63, 109, 1)",
	        pointRadius: 3,
	        pointBackgroundColor: "rgba(235, 63, 109, 1)",
	        pointBorderColor: "rgba(235, 63, 109, 1)",
	        pointHoverRadius: 3,
	        pointHoverBackgroundColor: "rgba(235, 63, 109, 1)",
	        pointHoverBorderColor: "rgba(235, 63, 109, 1)",
	        pointHitRadius: 10,
	        pointBorderWidth: 2,
	        data: [5000, 15000, 35000, 35000, 10000, 40000, 35000, 45000, 10000, 20000, 35000, 5000],
	      } */],
	  },
	  options: {
	    maintainAspectRatio: false,
	    layout: {
	      padding: {
	        left: 10,
	        right: 25,
	        top: 25,
	        bottom: 0
	      }
	    },
	    scales: {
	      xAxes: [{
	        time: {
	          unit: 'date'
	        },
	        gridLines: {
	          display: false,
	          drawBorder: false
	        },
	        ticks: {
	          maxTicksLimit: 7
	        }
	      }],
	      yAxes: [{
	        ticks: {
	          maxTicksLimit: 5,
	          padding: 10,
	          // Include a dollar sign in the ticks
	          callback: function(value, index, values) {
	            return '$' + number_format(value);
	          }
	        },
	        gridLines: {
	          color: "rgb(234, 236, 244)",
	          zeroLineColor: "rgb(234, 236, 244)",
	          drawBorder: false,
	          borderDash: [2],
	          zeroLineBorderDash: [2]
	        }
	      }],
	    },
	    legend: {
	      display: false
	    },
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      titleMarginBottom: 10,
	      titleFontColor: '#6e707e',
	      titleFontSize: 14,
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      intersect: false,
	      mode: 'index',
	      caretPadding: 10,
	      callbacks: {
	        label: function(tooltipItem, chart) {
	          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
	          return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
	        }
	      }
	    }
	  }
	});

</script>
   -->