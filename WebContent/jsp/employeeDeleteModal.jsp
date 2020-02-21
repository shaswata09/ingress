<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <!-- EmployeeDeleteModal Modal-->
  <div class="modal fade" id="employeeDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Are you sure to delete?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">Ã</span>
          </button>
        </div>
        <div class="modal-body">Select "Yes! Delete" below if you are ready to delete all records of this employee.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>          
          	<button type="submit" name="employeeEditType" value="deleteEmployee" class="btn btn-danger">
			    <i class="fas fa-user-slash"></i> "Yes! Delete"
			</button>          	
        </div>
      </div>
    </div>
  </div>