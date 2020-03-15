package com.switchFeatures;

import java.io.IOException;
import java.util.List;

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
import com.leaves.DltObject;
import com.leaves.LeaveServiceHelper;
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
						List<DltObject> quarterlyLeaveList = new DLT(QuarterServiceHelper.getCurrentQuarter()).
								showTable(((Employee)session.getAttribute("user")).getEmployeeId());
						int[] quarterlyLeaveCount = LeaveServiceHelper.getLeaveTypeCount(quarterlyLeaveList);
						session.setAttribute("quarterlyLeaveCount", quarterlyLeaveCount);
						
						List<DltObject> pendingSelfLeaveList = new TempTable().showTable(((Employee)session.getAttribute("user")).getEmployeeId());
						int pendingLeaveCount = (LeaveServiceHelper.getLeaveTypeCount(pendingSelfLeaveList))[0];
						session.setAttribute("pendingLeaveCount", pendingLeaveCount);
						
						session.setAttribute("monthlyLeaveCount", LeaveServiceHelper.findYearlyLeaveCount(
								QuarterServiceHelper.findYearByQuarter(QuarterServiceHelper.getCurrentQuarter()), 
								((Employee)session.getAttribute("user")).getEmployeeId()));
						
						session.setAttribute("pendingSelfLeaveList", pendingSelfLeaveList);
						session.setAttribute("filteredQuarterList", QuarterServiceHelper.filteredQuarterList());						
						if(null == request.getAttribute("quarterlyLeaveList")) {
							session.setAttribute("currentQuarter", QuarterServiceHelper.getCurrentQuarter());
							session.setAttribute("quarterlyLeaveList", quarterlyLeaveList);														
						}
						else {
							session.setAttribute("quarterlyLeaveList", request.getAttribute("quarterlyLeaveList"));
							session.setAttribute("currentQuarter", request.getAttribute("currentQuarter"));							
						}						
					}
					else {
						session.removeAttribute("filteredQuarterList");
						session.removeAttribute("quarterlyLeaveList");
						session.removeAttribute("currentQuarter");
						session.removeAttribute("pendingSelfLeaveList");
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
						
					if(pageName.equals("dashboard")) {
						int [] quarterlyLeaveCount = LeaveServiceHelper.getLeaveTypeCount(new DLT(QuarterServiceHelper.getCurrentQuarter()).showTable(null));
						request.setAttribute("quarterlyLeaveCount", quarterlyLeaveCount);
						
						int [] selfLeaveCount = LeaveServiceHelper.getLeaveTypeCount(new DLT(QuarterServiceHelper.
								getCurrentQuarter()).showTable(((Employee)session.getAttribute("user")).getEmployeeId()));
						request.setAttribute("selfLeaveCount", selfLeaveCount);						
						int[] selfLeaveTypeRatio = LeaveServiceHelper.getLeaveTypeRatioPercentage(selfLeaveCount);						
						request.setAttribute("selfLeaveTypeRatio", selfLeaveTypeRatio);
						
						List<DltObject> pendingLeaveList = new TempTable().showTable(null);
						if(null == pendingLeaveList)
							request.setAttribute("pendingLeaveNumbers", 0);
						else
							request.setAttribute("pendingLeaveNumbers", pendingLeaveList.size());
						
						int pendingLeaveCount = (LeaveServiceHelper.getLeaveTypeCount(pendingLeaveList))[0];
						request.setAttribute("pendingLeaveCount", pendingLeaveCount);
						
						request.setAttribute("totalEmployeeCount", (new EmployeeDetailsList().returnList()).size());
						
						request.setAttribute("monthlyLeaveCount", LeaveServiceHelper.findYearlyLeaveCount(
								QuarterServiceHelper.findYearByQuarter(QuarterServiceHelper.getCurrentQuarter()), null));
						
					} 
					else if(pageName.equals("employeeDetails") || pageName.equals("addLeave")) {
						request.setAttribute("employeeList", new EmployeeDetailsList().returnList());
					} 
					else if(pageName.equals("approveLeave")) {		//creating and removing Pending leave list as required	
						List<DltObject> pendingLeaveList = new TempTable().showTable(null);
						request.setAttribute("pendingLeaveList", pendingLeaveList);
						
						if(null == pendingLeaveList)
							request.setAttribute("pendingLeaveNumbers", 0);
						else
							request.setAttribute("pendingLeaveNumbers", pendingLeaveList.size());
						
						int [] quarterlyLeaveCount = LeaveServiceHelper.getLeaveTypeCount(new DLT(QuarterServiceHelper.getCurrentQuarter()).showTable(null));
						request.setAttribute("quarterlyLeaveCount",quarterlyLeaveCount);
						int[] quarterlyLeaveTypeRatio = LeaveServiceHelper.getLeaveTypeRatioPercentage(quarterlyLeaveCount);						
						request.setAttribute("quarterlyLeaveTypeRatio", quarterlyLeaveTypeRatio);
						
					} 
					else if(pageName.equals("quarterlyLeaveReport")) {
						if(null == request.getAttribute("changedQuarterFlag")) {
							request.setAttribute("filteredQuarterList", QuarterServiceHelper.filteredQuarterList());
							request.setAttribute("quarterlyLeaveList",new DLT(QuarterServiceHelper.getCurrentQuarter()).showTable(null));
							request.setAttribute("currentQuarter", QuarterServiceHelper.getCurrentQuarter());							
						}
						else {
							request.removeAttribute("changedQuarterFlag");
						}
																
					} 
					else if(pageName.equals("selfLeaveStatus")) {
						List<DltObject> quarterlyLeaveList = new DLT(QuarterServiceHelper.getCurrentQuarter()).
								showTable(((Employee)session.getAttribute("user")).getEmployeeId());
						int[] quarterlyLeaveCount = LeaveServiceHelper.getLeaveTypeCount(quarterlyLeaveList);
						request.setAttribute("quarterlyLeaveCount", quarterlyLeaveCount);
						
						List<DltObject> pendingSelfLeaveList = new TempTable().showTable(((Employee)session.getAttribute("user")).getEmployeeId());
						int pendingLeaveCount = (LeaveServiceHelper.getLeaveTypeCount(pendingSelfLeaveList))[0];
						request.setAttribute("pendingLeaveCount", pendingLeaveCount);
						request.setAttribute("monthlyLeaveCount", LeaveServiceHelper.findYearlyLeaveCount(
								QuarterServiceHelper.findYearByQuarter(QuarterServiceHelper.getCurrentQuarter()), 
								((Employee)session.getAttribute("user")).getEmployeeId()));
						
						request.setAttribute("pendingSelfLeaveList", pendingSelfLeaveList);
						if(null == request.getAttribute("changedQuarterFlag")) {
						request.setAttribute("filteredQuarterList", QuarterServiceHelper.filteredQuarterList());
						request.setAttribute("currentQuarter", QuarterServiceHelper.getCurrentQuarter());
						request.setAttribute("quarterlyLeaveList", quarterlyLeaveList);						
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
