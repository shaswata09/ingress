package com.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegisterEmployee")
public class RegisterEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			if(session.getAttribute("user") == null || ((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("0"))
				throw new Exception("Invalid user");
			else 
				session.setAttribute("pageName","dashboard");
			
			String employeeName = request.getParameter("employeeName");
			String employeeKITSID = request.getParameter("employeeKITSID");
			String employeeTCSID = request.getParameter("employeeTCSID");
			String employeeMobile = request.getParameter("employeeMobile");
			String employeeEmergencyContact = request.getParameter("employeeEmergencyContact");
			String employeeTeamName = request.getParameter("employeeTeamName");
			String employeeLocation = request.getParameter("employeeLocation");
			String employeeRole = request.getParameter("employeeRole");
			String employeeSOW = request.getParameter("employeeSOW");

			
			new EmpLookUp().addRow(employeeName, employeeTCSID, employeeTeamName, 
					employeeLocation, employeeRole, employeeSOW, employeeMobile, 
					employeeEmergencyContact, employeeKITSID);
			
			response.sendRedirect("dashboard.jsp");
		}
		catch(Exception exception) {
			exception.printStackTrace();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}	
	}

}
