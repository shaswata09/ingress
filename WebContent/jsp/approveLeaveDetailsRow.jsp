<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Begin Page Content -->
<div class="container-fluid">
<form class="user" action="DecideLeave" method="get">  
    <!-- DataTales Example -->
	<div class="card shadow mb-4">
        <div class="card-header py-3">
          <div class="row">            
          	  <h6 class="m-0 font-weight-bold text-primary">Approve Pending Leaves</h6>                        	
          </div>          
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered display" id="dataTable">
              <thead>
                <tr role="row">                             
                  <th>Name(ID)</th>
                  <th>Start Date -- End Date</th>
                  <th>Days</th>
                  <th>Month</th>
                  <th>Team</th>                  
                  <th>Action</th>                 
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <th>Name(ID)</th>
                  <th>(yyyy-MM-dd) -- (yyyy-MM-dd)</th>
                  <th>Days</th>
                  <th>Month</th>
                  <th>Team</th>                  
                  <th>Action</th>  
                </tr>
              </tfoot>                           
              <tbody>
                <!-- EditEmployeeDetails -->
                <c:forEach items="${pendingLeaveList}" var="pendingLeave">                           
                <tr>
                	<td>${pendingLeave.getEmpName()}(${ pendingLeave.getEmpId()})</td>
                	<td>${pendingLeave.getStartDate()} -- ${pendingLeave.getEndDate()}</td>
                	<td>${pendingLeave.getNumberOfDays()}</td>
                	<td>${pendingLeave.getLeaveMonth()}</td>
                	<td>${pendingLeave.getProject() }</td>
                	<td>
	                	<div class="row">
		                	<div class="col-md-6 border-right">
		                	<button type="submit" name="leaveStatusApprove" value="${pendingLeave.getEmpId()},${pendingLeave.getStartDate()},${pendingLeave.getEndDate()},${true}" class="btn btn-success btn-sm btn-circle">
		                    	<i class="fas fa-check"></i>
		               	 	</button>
		               	 	</div>	
	               	 		<div class="col-md-6 border-left" align="right">
		               	 	<button type="submit" name="leaveStatusDecline" value="${pendingLeave.getEmpId()},${pendingLeave.getStartDate()},${pendingLeave.getEndDate()},${false}"  class="btn btn-danger btn-sm btn-circle">
		                    	<i class="fas fa-trash"></i>
		               	 	</button>
		               	 	</div>	
	               	 	</div>
                  	</td>
                </tr>  
                </c:forEach>               
              </tbody>
            </table>
          </div>
        </div>
      </div>
</form> 
</div>
<!-- END Page Content -->


<style>
.border-right {
    border-right: 1px solid #ddd;
}
.borders {
    border-left: 1px solid #ddd;
    border-right: 1px solid #ddd;
    margin: -1px;
}
.border-left {
    border-left: 1px solid #ddd;
}

</style>
