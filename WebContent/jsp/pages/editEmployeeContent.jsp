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
              <h1 class="h4 text-gray-900 mb-4">Edit Profile! <i class="fas fa-user-edit fa-1x"></i></h1>
            </div>
            <form class="user" action="UpdateEmployeeDetails" method="get">
              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">                	
                	<input type="text" class="form-control form-control-user" name="employeeName" value="${editDetailsEmployee.getEmployeeName()}" required>
                </div>
                <div class="col-sm-6 mb-3 mb-sm-0">
                <c:choose>
                	<c:when test='${ !editDetailsEmployee.getEmployeeMailID().equals("") }'>       
                		<input type="text" class="form-control form-control-user" name="employeeKITSID" value="${editDetailsEmployee.getEmployeeMailID()}">       
                	</c:when>
                	<c:otherwise>
                		<input type="text" class="form-control form-control-user" name="employeeKITSID" placeholder="KITS ID">
                	</c:otherwise>
                </c:choose>                	
              	</div>
              </div>	              	 
              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                <c:choose>
                	<c:when test='${!editDetailsEmployee.getEmployeeId().equals("") }'>
                		<input type="text" class="form-control form-control-user" name="employeeTCSID" value="${editDetailsEmployee.getEmployeeId()}" required>              
                	</c:when>
                	<c:otherwise>
                		<input type="text" class="form-control form-control-user" name="employeeTCSID" placeholder="TCS ID" required>
                	</c:otherwise>
                </c:choose>                  
                </div>
                <div class="col-sm-6 mb-3 mb-sm-0">
	                <div class="form-group row">
	                	<div class="col-sm-6 mb-3 mb-sm-0">
	                	<c:choose>
		                	<c:when test='${!editDetailsEmployee.getEmployeeMobile().equals("") }'>    
		                		<input type="text" class="form-control form-control-user" name="employeeMobile" value="${editDetailsEmployee.getEmployeeMobile()}">          
		                	</c:when>
		                	<c:otherwise>
		                		<input type="text" class="form-control form-control-user" name="employeeMobile" placeholder="Mobile">
		                	</c:otherwise>
		                </c:choose>	                		
	                	</div>
	                	<div class="col-sm-6 mb-3 mb-sm-0">
	                	<c:choose>
		                	<c:when test='${!editDetailsEmployee.getEmployeeEmergencyContact().equals("") }'>      
		                		<input type="text" class="form-control form-control-user" name="employeeEmergencyContact" value="${editDetailsEmployee.getEmployeeEmergencyContact()}">        
		                	</c:when>
		                	<c:otherwise>
		                		<input type="text" class="form-control form-control-user" name="employeeEmergencyContact" placeholder="Emergency">
		                	</c:otherwise>
		                </c:choose>	                		
	                	</div>
	                </div>	                  
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                <c:choose>
                	<c:when test='${!editDetailsEmployee.getEmployeeTeam().equals("") }'> 
                		<input type="text" class="form-control form-control-user" name="employeeTeamName" value="${editDetailsEmployee.getEmployeeTeam()}">             
                	</c:when>
                	<c:otherwise>
                		<input type="text" class="form-control form-control-user" name="employeeTeamName" placeholder="Team Name">
                	</c:otherwise>
                </c:choose>	                  
                </div>
                <div class="col-sm-6">
                <c:choose>
                	<c:when test='${!editDetailsEmployee.getEmployeeLocation().equals("") }'> 
                		<input type="text" class="form-control form-control-user" name="employeeLocation" value="${editDetailsEmployee.getEmployeeLocation()}">             
                	</c:when>
                	<c:otherwise>
                		<input type="text" class="form-control form-control-user" name="employeeLocation" placeholder="Employee Location">
                	</c:otherwise>
                </c:choose>                  
                </div>
              </div>
              <div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                <c:choose>
                	<c:when test='${!editDetailsEmployee.getEmployeeRole().equals("") }'>        
                		<input type="text" class="form-control form-control-user" name="employeeRole" value="${editDetailsEmployee.getEmployeeRole()}">      
                	</c:when>
                	<c:otherwise>
                		<input type="text" class="form-control form-control-user" name="employeeRole" placeholder="Role">
                	</c:otherwise>
                </c:choose>                  
                </div>
                <div class="col-sm-6">
                <c:choose>
                	<c:when test='${!editDetailsEmployee.getEmployeeSOW().equals("") }'>  
                		<input type="text" class="form-control form-control-user" name="employeeSOW" value="${editDetailsEmployee.getEmployeeSOW()}">            
                	</c:when>
                	<c:otherwise>
                		<input type="text" class="form-control form-control-user" name="employeeSOW" placeholder="SOW">
                	</c:otherwise>
                </c:choose>                  
                </div>
              </div>
              <hr> 
              <div class="form-group row">
              <div class="col-sm-5 mb-3 mb-sm-0 row">
              	<div class="col-sm-5 mb-2 mb-sm-0">
              		<button type="submit" class="btn btn-block btn-success" disabled>
					    Issue Login <i class="fas fa-thumbs-up"></i> 
					</button> 
              	</div>      
              	<div class="col-sm-1 mb-0 mb-sm-0"></div>   	
              	<div class="col-sm-6 mb-3 mb-sm-0">
              		<button type="submit" class="btn btn-block btn-secondary">
					    Reset Password <i class="fas fa-thumbs-up"></i> 
					</button> 
              	</div>
              </div>              
              	
              	<div class="col-sm-3 mb-1 mb-sm-0"></div>
              	<div class="col-sm-4 mb-2 mb-sm-0">
              		<div class="form-group row" align="right">
              			<div class="col-sm-6 mb-3 mb-sm-0">
	              			<button type="submit" class="btn btn-block btn-warning">
							    <i class="fas fa-user-check"></i> Save
							</button>               				
              			</div>
              			<div class="col-sm-6 mb-3 mb-sm-0">
              				<a class="btn btn-block btn-info" href="SwitchPages?pageName=dashboard">
							    <i class="fas fa-times"></i> Discard
							</a>   
              			</div>
              		</div>
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