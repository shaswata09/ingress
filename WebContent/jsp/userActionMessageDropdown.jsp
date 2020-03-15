<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li class="nav-item dropdown no-arrow d-sm-none">
  <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <div class="spinner-grow spinner-grow-sm text-secondary" role="status">
		<span class="sr-only">Loading...</span>
	</div>
  </a>
  <!-- Dropdown - Messages -->
  <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
    <div class="alert alert-secondary alert-dismissible fade show" role="alert">
	  <strong>${userActionMessagePrimary}</strong> ${userActionMessageSecondary}
	</div>
  </div>
</li>