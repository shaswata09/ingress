package com.features;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MaintainanceFeature")
public class FeatureToggle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IssueLogin issue = new IssueLogin();
		issue.autoFillLogin();
		HttpSession session = request.getSession(false);
		session.setAttribute("pageName","dashboard");
		response.sendRedirect("dashboard.jsp");
	}

}
