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
            <h1 class="h4 text-gray-900 mb-4"><i class="fas fa-pen"></i> Change Password</h1>
          </div>
          <hr>
          <form class="user" action="ChangePassword" method="post">
            <div class="form-group row">
              <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" placeholder="${user.getEmployeeName()}  (${user.getEmployeeId()})" disabled>
              </div>
              <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="password" class="form-control form-control-user" name="employeeCurrentPassword" placeholder="Current Password" required>
              </div>              
            </div>            
            <div class="form-group row">
              <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="password" class="form-control form-control-user" name="employeeNewPassword" placeholder="Enter new Password" required>
              </div>
              <div class="col-sm-6">
                <input type="password" class="form-control form-control-user" name="employeeRe-enteredNewPassword" placeholder="Re-enter new Password" required>
              </div>
            </div>
            <hr>
            <div class="form-group row">              
              	<div class="col-sm-8 mb-4 mb-sm-0"></div>
              	<div class="col-sm-4 mb-2 mb-sm-0">
              		<div class="form-group row" align="right">
              			<div class="col-sm-6 mb-3 mb-sm-0">
	              			<button type="submit" class="btn btn-block btn-warning">
							    <i class="fas fa-user-check"></i> Save
							</button>               				
              			</div>
              			<div class="col-sm-6 mb-3 mb-sm-0">
              				<a class="btn btn-block btn-secondary" href="SwitchPages?pageName=dashboard">
							    <i class="fas fa-times"></i> Discard
							</a>   
              			</div>
              		</div>
              	</div>	              	          
              </div>            
            <div class="text-center">
              <p class="mb-4">Something went Wrong??? Contact Admin To Resolve...<i class="fas fa-grin-beam-sweat"></i></p>           
            </div>
          </form>
          <hr>
          
        </div>
      </div>
    </div>
  </div>
</div>
</div>
