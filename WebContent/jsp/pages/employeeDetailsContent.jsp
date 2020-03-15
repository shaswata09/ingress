<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Begin Page Content -->
<div class="container-fluid">
<form class="user" action="EditEmployeeDetails" method="get">  
    <!-- DataTales Example -->
	<div class="card shadow mb-4">
        <div class="card-header py-3">
          <div class="row">
            <div class="col-lg-4">
          	  <h6 class="m-0 font-weight-bold text-primary">Employee details</h6>
            </div>
            <div class="col-lg-4 col-md-2 col-sm-0"></div>
          	<div class="col-lg-2" align="right">
          	 <button type="submit" name="employeeEditType" value="editEmployee" class="btn btn-block btn-info btn-sm btn-icon-split">
	          	 <span class="icon text-white-70"><i class="fas fa-user-edit"></i></span>
	          	 <span class="text">Edit</span>			      
			 </button>               				
          	</div> 
          	<div class="col-lg-2" align="right">
          	  <a href="#" class="btn btn-danger btn-icon-split btn-sm btn-block disabled" data-toggle="modal" data-target="#employeeDeleteModal">
                <span class="icon text-white-70">
                  <i class="fas fa-trash"></i>
                </span>
                <span class="text">Delete</span>
              </a>
          	</div>          	
          </div>          
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered display" id="dataTable">
              <thead>
                <tr role="row">   
                  <th>Sl.</th>            
                  <th>Name</th>
                  <th>ID</th>
                  <th>Email</th>
                  <th>Team</th>
                  <th>Location</th>
                  <th>Role</th>
                  <th>SOW</th>
                  <th>Mobile</th>
                  <th>Emergency</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <th>Sl.</th>
                  <th>Name</th>
                  <th>ID</th>
                  <th>Email</th>
                  <th>Team</th>
                  <th>Location</th>
                  <th>Role</th>
                  <th>SOW</th>
                  <th>Mobile</th>
                  <th>Emergency</th>
                </tr>
              </tfoot>                           
              <tbody>
                <!-- EditEmployeeDetails -->
                <c:forEach items="${employeeList}" var="employee">
                	<tr>
	                  <td><input type="radio" name="employeeEditSelect" value='${employee.getEmployeeId()}'></td>
	                  <td><c:out value='${employee.getEmployeeName()}'/></td>
	                  <td><c:out value='${employee.getEmployeeId()}'/></td>
	                  <td><c:out value='${employee.getEmployeeMailID()}'/></td>
	                  <td><c:out value='${employee.getEmployeeTeam()}'/></td>
	                  <td><c:out value='${employee.getEmployeeLocation()}'/></td>
	                  <td><c:out value='${employee.getEmployeeRole()}'/></td>
	                  <td><c:out value='${employee.getEmployeeSOW()}'/></td>
	                  <td><c:out value='${employee.getEmployeeMobile()}'/></td>
	                  <td><c:out value='${employee.getEmployeeEmergencyContact()}'/></td>
	                </tr>
                </c:forEach>
              
              </tbody>
            </table>
          </div>
        </div>
      </div>
   	<%@ include file="../employeeDeleteModal.jsp" %>
</form> 
</div>
<!-- END Page Content -->
