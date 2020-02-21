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
import com.features.GenerateQuarterList;
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
			
			if(session.getAttribute("user") == null) {		//if user not defined, redirect to login
				session.invalidate();
				response.sendRedirect("login.jsp");
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
						session.setAttribute("filteredQuarterList", GenerateQuarterList.filteredQuarterList());
						session.setAttribute("quarterlyLeaveList", new DLT().showTable(GenerateQuarterList.getCurrentQuarter()));
						session.setAttribute("currentQuarter", GenerateQuarterList.getCurrentQuarter());
					}
					else {
						session.removeAttribute("filteredQuarterList");
						session.removeAttribute("quarterlyLeaveList");
						session.removeAttribute("currentQuarter");
					}
					
					response.sendRedirect("dashboard.jsp");
			    }
				else {
					session.invalidate();
					response.sendRedirect("login.jsp");
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
						if(request.getAttribute("changedQuarterFlag")==null) {
							request.setAttribute("filteredQuarterList", GenerateQuarterList.filteredQuarterList());
							request.setAttribute("quarterlyLeaveList",new DLT().showTable(GenerateQuarterList.getCurrentQuarter()));
							request.setAttribute("currentQuarter", GenerateQuarterList.getCurrentQuarter());	
						}
						else {
							request.removeAttribute("changedQuarterFlag");
						}
																
					} else if(pageName.equals("selfLeaveStatus")) {
						if(request.getAttribute("changedQuarterFlag")==null) {
						request.setAttribute("filteredQuarterList", GenerateQuarterList.filteredQuarterList());
						request.setAttribute("quarterlyLeaveList",new DLT().showTable(GenerateQuarterList.getCurrentQuarter()));
						request.setAttribute("currentQuarter", GenerateQuarterList.getCurrentQuarter());
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
					session.invalidate();
					response.sendRedirect("login.jsp");
				}
			}
			else {
				session.invalidate();
				response.sendRedirect("login.jsp");
			}
		}
		catch(Exception exception) {
			if(session != null)
				session.invalidate();
			response.sendRedirect("error.jsp");
			System.out.println("Exception occurred!!!");
			exception.printStackTrace();
		}		
	}			// EmployeeEdit change...
}
