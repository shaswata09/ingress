package com.leaves;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.PMOProperties.UserActionMessages;
import com.features.QuarterServiceHelper;

@WebServlet("/ChangeQuarterServlet")
public class ChangeQuarterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String quarterInfo = request.getParameter("selectQuarter");
			HttpSession session = request.getSession(false);
			if(QuarterServiceHelper.isQuarterDirectoryPresent(quarterInfo)) {	//query data for that quarter
				request.removeAttribute("quarterlyLeaveList");
				request.removeAttribute("currentQuarter");
				request.setAttribute("quarterlyLeaveList",new DLT(quarterInfo).showTable());				
				request.setAttribute("currentQuarter", quarterInfo);
				request.setAttribute("filteredQuarterList", QuarterServiceHelper.filteredQuarterList());
				request.setAttribute("changedQuarterFlag", true);
				request.setAttribute("userActionMessagePrimary", UserActionMessages.QUARTER_CHANGED);
				request.setAttribute("userActionMessageSecondary", UserActionMessages.TO+" "+ quarterInfo);
			} 
			else {	//show incorrect quarter message				
				request.setAttribute("userActionMessagePrimary", UserActionMessages.INCORRECT_QUARTER);
				request.setAttribute("userActionMessageSecondary", UserActionMessages.QUARTER_DATA_NOT_FOUND+" "+UserActionMessages.TRY_AGAIN);
			}
			RequestDispatcher rd = request.getRequestDispatcher("SwitchPages?pageName="+session.getAttribute("pageName"));
			rd.forward(request, response);
		}
		catch(Exception exception) {
			HttpSession session = request.getSession(false);
			session.setAttribute("pageName", "error");			
			exception.printStackTrace();
			exception.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}

}
