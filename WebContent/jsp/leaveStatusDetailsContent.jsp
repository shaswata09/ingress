<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="col-lg-11 col-md-11 col-sm-12" style="margin: 0 auto;">
	
	<div class="card shadow mb-4">
      <!-- Card Header - Accordion -->
      <a href="#collapseCardExample" class="d-block card-header py-3" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="collapseCardExample">
        <h6 class="m-0 font-weight-bold text-primary">Pending Leaves</h6>
      </a>
      <!-- Card Content - Collapse -->
      <div class="collapse" id="collapseCardExample" style="">
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered display" id="pendingLeaveDataTable">
              <thead>
                <tr role="row">  
                  <th>Start Date -- End Date</th>
                  <th>Days</th>
                  <th>Month</th>                
                </tr>
              </thead>
              <tfoot>
                <tr>                  
                  <th>yyyy-MM-dd -- yyyy-MM-dd</th>
                  <th>Days</th>
                  <th>Month</th>  
                </tr>
              </tfoot>                           
              <tbody>
              	<c:forEach items="${pendingSelfLeaveList}" var="pendingLeave">                           
                <tr>	                	
                	<td>${pendingLeave.getStartDate()} -- ${pendingLeave.getEndDate()}</td>
                	<td>${pendingLeave.getNumberOfDays()}</td>
                	<td>${pendingLeave.getLeaveMonth()}</td>
                </tr>  
                </c:forEach>	                  
              </tbody>
            </table>
          </div>  
        </div>
      </div>
    </div>
	</div>
	
	
    <!-- DataTables Example -->
	<div class="card shadow mb-4">
        <div class="card-header py-3">
          <div class="row">
            <div class="col-lg-4 col-md-5 col-sm-6">
          	  <h6 class="m-0 font-weight-bold text-primary">Quarterly Leave Report(Approved)</h6>
            </div>
            <div class="col-lg-4 col-md-3 col-sm-0"></div>
            <div class="col-lg-4 col-md-4 col-sm-0 form-group">
          	<form class="user" action="ChangeQuarterServlet" method="get">           	
          		<div class="row">
	          		<div class="col-lg-7 col-md-8 col-sm-6">
		          		<select id="searchEmployeeDropdown" name="selectQuarter" class="form-control">
						  <c:forEach items="${filteredQuarterList}" var="quarter">
							  <c:choose>
							  	<c:when test="${ quarter.equals(currentQuarter)}">
							  		<option value="${quarter}" selected>${quarter}</option>
							  	</c:when>
							  	<c:otherwise>
							  		<option value="${quarter}">${quarter}</option> 
							  	</c:otherwise>
							  </c:choose>					  	
						  </c:forEach>				  					  
						</select>
	          		</div>	          		
					&nbsp;&nbsp;&nbsp;
          			<button type="submit" class="btn btn-success btn-icon-split btn-sm">
			          	 <span class="icon text-white-70"><i class="fab fa-rev"></i></span>	
			          	 <span class="text">Change</span>			          	 		      
				 	</button> 
          		</div>			             	  
			</form>           				
          	</div>                    	
          </div>          
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered display" id="dataTable">
              <thead>
                <tr role="row">   
                  <th>Start Date</th>            
                  <th>End Date</th>
                  <th>Days</th>                  
                  <th>Month</th>
                  <th>Type</th>                                 
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <th>(yyyy-MM-dd)</th>            
                  <th>(yyyy-MM-dd)</th>
                  <th>Days</th>                  
                  <th>Month</th> 
                  <th>Type</th>                                 
                </tr>
              </tfoot>                           
              <tbody>                
                <c:forEach items="${quarterlyLeaveList}" var="approvedLeave">                           
	                <tr>
	                	<td>${approvedLeave.getStartDate()}</td>
	                	<td>${approvedLeave.getEndDate()}</td>
	                	<td>${approvedLeave.getNumberOfDays()}</td>	                	
	                	<td>${approvedLeave.getLeaveMonth()}</td>
	                	<td>${approvedLeave.getTypeOfLeave()}</td>	                	
	                </tr>  
                </c:forEach>                  
              </tbody>
            </table>
          </div>
        </div>
      </div>
</div>
<!-- END Page Content -->
<script>	
	$(document).ready(function(){	 
	  // Initialize select2
	  $("#searchEmployeeDropdown").select2();
	});
</script>