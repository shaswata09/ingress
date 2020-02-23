package com.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateEmployeeDetails")
public class UpdateEmployeeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			if(null == session.getAttribute("user") || ((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("0"))
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
			String employeeEditCategory = request.getParameter("employeeEditCategory");
			
			System.out.println(employeeName);
			System.out.println(employeeKITSID);
			System.out.println(employeeTCSID);
			System.out.println(employeeMobile);
			System.out.println(employeeEmergencyContact);
			System.out.println(employeeTeamName);
			System.out.println(employeeLocation);
			System.out.println(employeeRole);
			System.out.println(employeeSOW);
			System.out.println(employeeEditCategory);
			//send code to insert function.
			
			response.sendRedirect("dashboard.jsp");
		}
		catch(Exception exception) {
			exception.printStackTrace();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
	}

}
