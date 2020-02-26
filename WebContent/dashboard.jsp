<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>INGRESS</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">
  
  <!-- Data Tables installation addons -->
  <script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-3.4.1.js"></script>
  <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
  
  <!-- Custom styles for this dataTable Page page -->
  <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
  
  <script>
	  $(document).ready( function () {
		    $('#dataTable').DataTable();
		} );
	  $(document).ready( function () {
		    $('#pendingLeaveDataTable').DataTable();
		} );
  </script>
  
  <!-- SearchEmployee Dropdown list -->
  	<!-- Script -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src='select2/dist/js/select2.min.js' type='text/javascript'></script>

	<!-- CSS -->
	<link href='select2/dist/css/select2.min.css' rel='stylesheet' type='text/css'>


</head>

<body id="page-top">
	<c:if test="${ user == null }">
		<jsp:forward page="./login.jsp"></jsp:forward>
	</c:if>

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <%@ include file="./jsp/sideBar.jsp" %>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
     <%@ include file="./jsp/contentWrapper.jsp" %>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <%@ include file="./jsp/logoutModal.jsp" %>
  

  <c:if test='${pageName.equals("employeeDetails")}'>
  	<%@ include file="./jsp/employeeDeleteModal.jsp" %>
  </c:if>


  <!-- Bootstrap core JavaScript-->
  <!-- <script src="vendor/jquery/jquery.min.js"></script> -->
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
  <script src="js/demo/datatables-demo.js"></script>
  
</body>

</html>
