<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
<div class="card o-hidden border-0 shadow-lg my-5">
  <div class="card-body p-0">
  <!-- Nested Row within Card Body -->
    <div class="row">      
      <div class="col-lg-12">
        <div class="p-5">
          <div class="text-center">
            <h1 class="h4 text-gray-900 mb-4">Add Leave! <i class="fas fa-hot-tub fa-1x"></i></h1>
          </div>
          <hr>
          <form class="user" action="addLeave" method="get">
            <div class="form-group row">              
              <div class="col-sm-6 mb-3 mb-sm-0">
              	<c:choose>
              		<c:when test='${user.getEmployeeAccessRight().equals("0")}'>              			
              			 <input type="text" class="form-control form-control-user" name="userID" placeholder="${user.getEmployeeName()}"  disabled>
              		</c:when>
              		<c:otherwise>   	
              			<select id="searchEmployeeDropdown" name="userID" class="form-control form-control-user">
						  <option value='0'>Select Employee</option>
						  <c:forEach items="${employeeList}" var="employee">
						  	<option value='${employee.getEmployeeId()}'><c:out value="${employee.getEmployeeName()}"/> (<c:out value="${employee.getEmployeeId()}"/>)</option> 
						  </c:forEach> 						  
						</select>
              		</c:otherwise>
              	</c:choose>              
              </div>
              <div class="col-sm-6 mb-3 mb-sm-0">
              	<select name="leaveType" name="leaveType" class="form-control">
              		<option value="unplanned" selected>Unplanned Leave</option>
              		<option value="planned">Planned Leave</option>
              	</select>                
              </div>              
            </div>
            <div class="form-group row">            	
            	<div class="col-sm-6 mb-3 mb-sm-0">
	            	<div class="container">
	            		<input type="date" name="startDate" class="form-control">
	            	</div>
	            	<p class="mb-2" align="center">Start date</p>
            	</div>            	
	            <div class="col-sm-6 mb-3 mb-sm-0">
	            	<div class="container">
	            		<input type="date" name="endDate" class="form-control">
	            	</div>  
	            	<p class="mb-2" align="center">End date</p>
            	</div>            	        
            </div>
            <hr>   
            <div align="center">
            	<div class="col-sm-4 mb-2 mb-sm-0" >
            		<input type="submit" value="Add" class="btn btn-info btn-user btn-block">
            	</div>    
            </div>            
          </form>          
        </div>
      </div>
    </div>
  </div>
</div>
</div>

<script>	
	$(document).ready(function(){	 
	  // Initialize select2
	  $("#searchEmployeeDropdown").select2();
	});
</script>
