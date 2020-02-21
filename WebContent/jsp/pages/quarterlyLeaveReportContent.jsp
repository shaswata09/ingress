<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- DataTales Example -->
	<div class="card shadow mb-4">
        <div class="card-header py-3">
          <div class="row">
            <div class="col-lg-4 col-md-5 col-sm-6">
          	  <h6 class="m-0 font-weight-bold text-primary">Quarterly Leave Report</h6>
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
							  		<option value="${quarter}" selected><c:out value="${quarter}"/></option>
							  	</c:when>
							  	<c:otherwise>
							  		<option value="${quarter}"><c:out value="${quarter}"/></option> 
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
                  <th>Name(ID)</th>
                  <th>Start Date -- End Date</th>
                  <th>Days</th>
                  <th>Month</th>
                  <th>Type</th>
                  <th>Team</th>
                  <th>Location</th>                  
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <th>Name(ID)</th>
                  <th>Start Date -- End Date</th>
                  <th>Days</th>
                  <th>Month</th>
                  <th>Type</th>
                  <th>Team</th>
                  <th>Location</th>
                </tr>
              </tfoot>                           
              <tbody>                
                <c:forEach items="${quarterlyLeaveList}" var="approvedLeave">                           
                <tr>
                	<td>${approvedLeave.getEmpName()}(${ approvedLeave.getEmpId()})</td>
                	<td>${approvedLeave.getStartDate()} -- ${approvedLeave.getEndDate()}</td>
                	<td>${approvedLeave.getNumberOfDays()}</td>
                	<td>${approvedLeave.getLeaveMonth()}</td>
                	<td>${approvedLeave.getTypeOfLeave()}</td>
                	<td>${approvedLeave.getProject()}</td>     
                	<td>${approvedLeave.getLocation()}</td>           	
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

