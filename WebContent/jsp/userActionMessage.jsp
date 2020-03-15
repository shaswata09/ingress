<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100">
		<div class="alert alert-secondary alert-dismissible fade show" role="alert">
		  <strong><c:out value='${userActionMessagePrimary}'/></strong> <c:out value='${userActionMessageSecondary}'/>
		  <div class="spinner-grow spinner-grow-sm text-info" role="status">
			  <span class="sr-only">Loading...</span>
			</div>
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	</div>
	
	