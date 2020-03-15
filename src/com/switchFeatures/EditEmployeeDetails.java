package com.switchFeatures;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.EmpLookUp;
import com.employee.Employee;

@WebServlet("/EditEmployeeDetails")
public class EditEmployeeDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = request.getParameter("employeeEditSelect");
		
		if(userId != null) {
			if(request.getParameter("employeeEditType").equals("editEmployee")) {
				session.setAttribute("pageName","employeeEdit");
				EmpLookUp empLookUp = new EmpLookUp();		
				List<String> editEmployeeDetails = empLookUp.searchId(userId);
				editEmployeeDetails.add(0, "0");
				request.setAttribute("editDetailsEmployee", new Employee(editEmployeeDetails ));	//new Employee(empLookup.searchId(userId))
				session.setAttribute("editDetailsEmployeeFlag", "true");			
			}
			else {
				session.setAttribute("pageName","dashboard");				
			}	
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}
		else {
			if(request.getParameter("employeeEditType").equals("editEmployee"))
				session.setAttribute("pageName","employeeDetails");
			else
				session.setAttribute("pageName","employeeDetails");
			
			response.sendRedirect("SwitchPages?pageName=employeeDetails");
		}
			
	}
}
