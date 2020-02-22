package com.switchFeatures;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.Employee;
import com.employee.EmployeeDetailsList;
import com.enums.AdminPages;
import com.enums.EmployeePages;
import com.features.QuarterServiceHelper;
import com.leaves.DLT;
import com.leaves.TempTable;

@WebServlet("/SwitchPages")
public class SwitchPagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String pageName = null;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			pageName = request.getParameter("pageName");
			//redirecting to error page.			
			if(pageName.equalsIgnoreCase("error"))
				response.sendRedirect("error.jsp");
			
			if(null == session.getAttribute("user")) {		//if user not defined, redirect to login
				sessionInvalidate(response, session);
			}		
			else if(((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("0")) {	//logged in as user 
				if (EmployeePages.contains(pageName)) {
					if(session.getAttribute("userActionMessageFlag")=="true") 
						session.removeAttribute("userActionMessageFlag");				
					else {
						session.removeAttribute("userActionMessagePrimary");
						session.removeAttribute("userActionMessageSecondary");
					}
					
					if(pageName.equalsIgnoreCase("dashboard")) 
						session.setAttribute("pageName","selfLeaveStatus");									
					else
						session.setAttribute("pageName",pageName);
					
					if((session.getAttribute("pageName").toString()).equalsIgnoreCase("selfLeaveStatus")) {					
						session.setAttribute("filteredQuarterList", QuarterServiceHelper.filteredQuarterList());
						session.setAttribute("quarterlyLeaveList", new DLT(QuarterServiceHelper.getCurrentQuarter()).showTable());
						session.setAttribute("currentQuarter", QuarterServiceHelper.getCurrentQuarter());
					}
					else {
						session.removeAttribute("filteredQuarterList");
						session.removeAttribute("quarterlyLeaveList");
						session.removeAttribute("currentQuarter");
					}
					
					response.sendRedirect("dashboard.jsp");
			    }
				else {
					sessionInvalidate(response, session);
				}
			}
			else if(((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("1")) {	//logged in as admin
				if (AdminPages.contains(pageName)) {
					// removing editEmployee Details while page redirection
					if(session.getAttribute("editDetailsEmployeeFlag")=="true") 
						session.removeAttribute("editDetailsEmployeeFlag");				
					else
						session.removeAttribute("editDetailsEmployee");
						
					
					if(pageName.equals("employeeDetails") || pageName.equals("addLeave")) {
						EmployeeDetailsList employeeDetailsList = new EmployeeDetailsList();
						request.setAttribute("employeeList", employeeDetailsList.returnList());
					} else if(pageName.equals("approveLeave")) {		//creating and removing Pending leave list as required						
						request.setAttribute("pendingLeaveList", new TempTable().showTable());
					} else if(pageName.equals("quarterlyLeaveReport")) {
						if(null == request.getAttribute("changedQuarterFlag")) {
							request.setAttribute("filteredQuarterList", QuarterServiceHelper.filteredQuarterList());
							request.setAttribute("quarterlyLeaveList",new DLT(QuarterServiceHelper.getCurrentQuarter()).showTable());
							request.setAttribute("currentQuarter", QuarterServiceHelper.getCurrentQuarter());	
						}
						else {
							request.removeAttribute("changedQuarterFlag");
						}
																
					} else if(pageName.equals("selfLeaveStatus")) {
						if(null == request.getAttribute("changedQuarterFlag")) {
						request.setAttribute("filteredQuarterList", QuarterServiceHelper.filteredQuarterList());
						request.setAttribute("quarterlyLeaveList",new DLT(QuarterServiceHelper.getCurrentQuarter()).showTable());
						request.setAttribute("currentQuarter", QuarterServiceHelper.getCurrentQuarter());
						}
						else {
							request.removeAttribute("changedQuarterFlag");
						}
					}					
					
					session.setAttribute("pageName",pageName);
					RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
					rd.forward(request, response);			
			    }
				else {
					sessionInvalidate(response, session);
				}
			}
			else {
				sessionInvalidate(response, session);
			}
		}
		catch(Exception exception) {
			session.invalidate();
			response.sendRedirect("error.jsp");
			exception.printStackTrace();
		}		
	}			// EmployeeEdit change...

	private void sessionInvalidate(HttpServletResponse response, HttpSession session) throws IOException {
		session.invalidate();
		response.sendRedirect("login.jsp");
	}
}
