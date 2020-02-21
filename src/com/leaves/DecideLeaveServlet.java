package com.leaves;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DecideLeave")
public class DecideLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
		String leaveStatus = request.getParameter("leaveStatusApprove")!= null ? request.getParameter("leaveStatusApprove"):request.getParameter("leaveStatusDecline");			
		String[] leaveStatusDetails = leaveStatus.split(",");
		
		new TempTable().decision(Integer.parseInt(leaveStatusDetails[0]), leaveStatusDetails[1], leaveStatusDetails[2], Boolean.parseBoolean(leaveStatusDetails[3]));		
		RequestDispatcher rd = request.getRequestDispatcher("SwitchPages?pageName=approveLeave");
		rd.forward(request, response);
		}catch(Exception exception) {
			HttpSession session = request.getSession(false);
			session.setAttribute("pageName", "error");			
			exception.printStackTrace();;
			exception.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

}
