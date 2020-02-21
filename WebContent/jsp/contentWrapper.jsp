<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Search -->
          <%-- <c:if test='${user.getEmployeeAccessRight().equals("1")}'>
          	<%@ include file="./searchBar.jsp"%>
          </c:if> --%>
          <c:if test='${userActionMessagePrimary != null || userActionMessageSecondary != null}'>          
          <!-- User Operation Message -->
          <div style="margin: 0 auto; padding: 20px; padding-top: 30px">
          	<%@ include file="./userActionMessage.jsp"%>
          </div>
          <!-- User Operation Message -->
          </c:if>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">
          
          	<c:if test='${userActionMessagePrimary != null || userActionMessageSecondary != null}'>
          	<!-- User Action Message Dropdown -->
          		<%@ include file="./userActionMessageDropdown.jsp"%>
          	<!-- User Action Message Dropdown -->
          	</c:if>

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <%-- <c:if test='${user.getEmployeeAccessRight().equals("1")}'>
            	<%@ include file="./searchDropDown.jsp" %>
            </c:if> --%>
			<!-- Nav Item - Search Dropdown (Visible Only XS) END-->
			
			
            <!-- Nav Item - Alerts -->
			<%-- <%@ include file="./navItemAlerts.jsp" %> --%>
			<!-- Nav Item - Alerts END -->
			
			
            <!-- Nav Item - Messages -->
            <%-- <%@ include file="./navItemMessage.jsp" %> --%>
            <!-- Nav Item - Messages END-->

            <div class="topbar-divider d-none d-sm-block"></div>
            
            <!-- Nav Item - User Information -->
            <%@include file="./profileItems.jsp" %>          

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div id="pageContentDiv">
        <c:choose>
	       	<c:when test='${pageName.equals("dashboard") && user!= null &&  user.getEmployeeAccessRight().equals("1")}'>
	        	<%@ include file="./pages/dashContent.jsp" %>
	        </c:when>
	        <c:when test='${pageName.equals("employeeDetails") && user!= null &&  user.getEmployeeAccessRight().equals("1")}'>
	        	<%@ include file="./pages/employeeDetailsContent.jsp" %>
	        </c:when>
	        <c:when test='${pageName.equals("myProfile") && user!= null}'>
	        	<%@ include file="./pages/myProfileContent.jsp" %>
	        </c:when>
	        <c:when test='${pageName.equals("addLeave") && user!= null}'>
	        	<%@ include file="./pages/addLeaveContent.jsp" %>
	        </c:when>
	        <c:when test='${ pageName.equals("register") && user!= null &&  user.getEmployeeAccessRight().equals("1")}'>
	        	<%@ include file="./pages/registerContent.jsp" %>
	        </c:when>
	        <c:when test='${ pageName.equals("employeeEdit") && user!= null &&  user.getEmployeeAccessRight().equals("1")}'>
	        	<%@ include file="./pages/editEmployeeContent.jsp" %>
	        </c:when>
	        <c:when test='${ pageName.equals("changePassword") && user!= null}'>
	        	<%@ include file="./pages/changePasswordContent.jsp" %>
	        </c:when>
	        <c:when test='${ pageName.equals("selfLeaveStatus") && user!= null}'>
	        	<%@ include file="./pages/leaveStatusContent.jsp" %>
	        </c:when>
	        <c:when test='${ pageName.equals("approveLeave") && user!= null && user.getEmployeeAccessRight().equals("1")}'>
	        	<%@ include file="./pages/approveLeaveContent.jsp" %>
	        </c:when>
	         <c:when test='${ pageName.equals("quarterlyLeaveReport") && user!= null && user.getEmployeeAccessRight().equals("1")}'>
	        	<%@ include file="./pages/quarterlyLeaveReportContent.jsp" %>
	        </c:when>
        </c:choose>        
        	
        </div>
        <!-- END Page Content -->
        
        
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->