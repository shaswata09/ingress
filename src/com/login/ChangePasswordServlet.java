package com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.Employee;

@WebServlet("/ChangePassword")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			if(session.getAttribute("user") == null)
				throw new Exception("Invalid user");
			else 
				session.setAttribute("pageName","dashboard");
			
			String employeeCurrentPassword = request.getParameter("employeeCurrentPassword");
			String employeeNewPassword = request.getParameter("employeeNewPassword");
			String employeeNewPassword2 = request.getParameter("employeeRe-enteredNewPassword");
			
			System.out.println(employeeCurrentPassword);
			System.out.println(employeeNewPassword);
			System.out.println(employeeNewPassword2);
			
			if(employeeNewPassword.equals(employeeNewPassword2)) {		//password matched
				//check current password is vaild or not
//				if(current password is vaild) {
//					change password
					request.getRequestDispatcher("SwitchPages?pageName=dashboard").forward(request, response);
//				}
//				else {
//					throw new Exception("Invalid user");
//				}
			}
			else {	
				request.getRequestDispatcher("SwitchPages?pageName=changePassword").forward(request, response);
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}	
	}

}
