package com.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.PMOProperties.*;
import com.employee.Employee;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String pageName = null;
		try {
			Integer.parseInt(userId);
			Validate validate = new Validate();		
			Employee employee = validate.checkCredential(userId, password);
			if(employee == null) {
				new Exception("Null User");
			}
			else if(employee.getEmployeeAccessRight().equals("1")) {
				HttpSession session = request.getSession();
				session.setAttribute("user", employee);
				session.setAttribute("pageName","dashboard");
				pageName = "dashboard";
				request.setAttribute("userActionMessagePrimary", UserActionMessages.WELCOME_MESSAGE);
				request.setAttribute("userActionMessageSecondary", UserActionMessages.HAPPY_EXPERIENCE);							
			}
			else if(employee.getEmployeeAccessRight().equals("0")) {
				HttpSession session = request.getSession();
				session.setAttribute("user", employee);
				session.setAttribute("pageName","selfLeaveStatus");
				pageName = "selfLeaveStatus";
				session.setAttribute("userActionMessagePrimary", UserActionMessages.WELCOME_MESSAGE);
				session.setAttribute("userActionMessageSecondary", UserActionMessages.HAPPY_EXPERIENCE);				
			}			
			RequestDispatcher rd = request.getRequestDispatcher("SwitchPages?pageName="+ pageName);
			rd.forward(request, response);	
		}
		catch(Exception exception) {
			exception.printStackTrace();
			response.sendRedirect("login.jsp");			
		}
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
}
