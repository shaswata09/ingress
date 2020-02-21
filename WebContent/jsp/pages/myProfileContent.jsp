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
            <h1 class="h4 text-gray-900 mb-4"><i class="fas fa-id-badge fa-1x"></i> Profile</h1>
          </div>
          <hr>
          <form class="user">
            <div class="form-group row">
              <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" placeholder="${user.getEmployeeName()}" disabled>
              </div>
              <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user"  placeholder="${user.getEmployeeId()}" disabled>
              </div>              
            </div>
            <div class="form-group">
              <input type="email" class="form-control form-control-user" placeholder="${user.getEmployeeMailID()}" disabled>
            </div>
            <div class="form-group row">
              <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user" placeholder="Role : ${user.getEmployeeRole()}" disabled>
              </div>
              <div class="col-sm-6">
                <input type="text" class="form-control form-control-user"  placeholder="Mobile : ${user.getEmployeeMobile()}" disabled>
              </div>
            </div>
            <div class="form-group row">
              <div class="col-sm-6 mb-3 mb-sm-0">
                <input type="text" class="form-control form-control-user"  placeholder="Team : ${user.getEmployeeTeam()}" disabled>
              </div>
              <div class="col-sm-6">
                <input type="text" class="form-control form-control-user" placeholder="Location : ${user.getEmployeeLocation()}" disabled>
              </div>
            </div>
            <div class="text-center">
              <p class="mb-4">Something Wrong??? Contact Admin To Change it...<i class="fas fa-grin-beam-sweat"></i></p>              
            </div>
          </form>
          <hr>          
        </div>
      </div>
    </div>
  </div>
</div>
</div>
