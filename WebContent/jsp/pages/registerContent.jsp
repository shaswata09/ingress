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
	              <h1 class="h4 text-gray-900 mb-4">Create an Account! <i class="fas fa-user-plus fa-1x"></i></h1>
	            </div>
	            <form class="user" action="RegisterEmployee" method="get">
	              <div class="form-group row">
	                <div class="col-sm-6 mb-3 mb-sm-0">
	                  <input type="text" class="form-control form-control-user" name="employeeName" placeholder="Employee Name" required>
	                </div>
	                <div class="col-sm-6 mb-3 mb-sm-0">
	                <input type="text" class="form-control form-control-user" name="employeeKITSID" placeholder="KITS ID (e.g. titlef10)">
	              </div>
	              </div>	              	 
	              <div class="form-group row">
	                <div class="col-sm-6 mb-3 mb-sm-0">
	                  <input type="number" min="0" class="form-control form-control-user" name="employeeTCSID" placeholder="TCS ID" required>
	                </div>
	                <div class="col-sm-6 mb-3 mb-sm-0">
		                <div class="form-group row">
		                	<div class="col-sm-6 mb-3 mb-sm-0">
		                		<input type="number" min="0" class="form-control form-control-user" name="employeeMobile" placeholder="Mobile Number" value="" required>
		                	</div>
		                	<div class="col-sm-6 mb-3 mb-sm-0">
		                		<input type="number" min="0" class="form-control form-control-user" name="employeeEmergencyContact" placeholder="Emergency Contact">
		                	</div>
		                </div>	                  
	                </div>
	              </div>
	              <div class="form-group row">
	                <div class="col-sm-6 mb-3 mb-sm-0">
	                  <input type="text" class="form-control form-control-user" name="employeeTeamName" placeholder="KITS Team Name">
	                </div>
	                <div class="col-sm-6">
	                  <input type="text" class="form-control form-control-user" name="employeeLocation" placeholder="Location">
	                </div>
	              </div>
	              <div class="form-group row">
	                <div class="col-sm-6 mb-3 mb-sm-0">
	                  <input type="text" class="form-control form-control-user" name="employeeRole" placeholder="Role">
	                </div>
	                <div class="col-sm-6">
	                  <input type="number" min="0" class="form-control form-control-user" name="employeeSOW" placeholder="SOW">
	                </div>
	              </div>
	              <hr>  
	              <div align="center"> 
	              	<div class="col-sm-4 mb-2 mb-sm-0">
	              		<button type="submit" class="btn btn-success btn-user btn-block">
			              Create Account
			            </button>  
	              	</div>	              	          
	              </div>	              
	            </form>	             
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>	
	</div>