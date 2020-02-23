package com.leaves;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.PMOProperties.*;
import com.employee.Employee;
import com.features.QuarterServiceHelper;

@WebServlet("/addLeave")
public class AddLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID;
		HttpSession session = request.getSession(false);
		if(((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("0"))
			userID = Integer.parseInt(((Employee)session.getAttribute("user")).getEmployeeId());
		else
			userID = Integer.parseInt(request.getParameter("userID"));
		String leaveType = request.getParameter("leaveType");
		String startDateString= request.getParameter("startDate").toString();
		String endDateString= request.getParameter("endDate").toString();
		
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;	
		try {
			startDate = formatter.parse(startDateString);
			endDate = formatter.parse(endDateString);
		} catch (ParseException excp) {			
			excp.printStackTrace();
		}
		
		if(startDate.compareTo(endDate)<=0) {
			try { 
				if(QuarterServiceHelper.findQuarterByDate(startDateString).equals(QuarterServiceHelper.findQuarterByDate(endDateString))) {					
					new DLT(QuarterServiceHelper.findQuarterByDate(startDateString)).addLeaveIntoTables(userID, leaveType, 
							startDateString, endDateString); 
				}
				else {
					String tempQuarter = null;				
					new DLT(QuarterServiceHelper.findQuarterByDate(startDateString)).addLeaveIntoTables(userID, leaveType,
							startDateString, QuarterServiceHelper.findLastDateOfQuarter(QuarterServiceHelper.findQuarterByDate(startDateString)));
					
					tempQuarter = QuarterServiceHelper.findNextQuarter(QuarterServiceHelper.findQuarterByDate(startDateString));
					for(int i=1; i<(QuarterServiceHelper.compareQuarter(QuarterServiceHelper.findQuarterByDate(endDateString), 
							QuarterServiceHelper.findQuarterByDate(startDateString))); i++) {						
						new DLT(QuarterServiceHelper.findQuarterByDate(startDateString)).addLeaveIntoTables(userID, leaveType,
								QuarterServiceHelper.findFirstDateOfQuarter(tempQuarter), QuarterServiceHelper.findLastDateOfQuarter(tempQuarter));				
						tempQuarter = new String(QuarterServiceHelper.findNextQuarter(tempQuarter));
					}
					
					new DLT(QuarterServiceHelper.findQuarterByDate(startDateString)).addLeaveIntoTables(userID, leaveType, 
							QuarterServiceHelper.findFirstDateOfQuarter(QuarterServiceHelper.findQuarterByDate(endDateString)), endDateString);
				}		
				
				session.setAttribute("pageName", "addLeave");
				if(((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("1")) {
					request.setAttribute("userActionMessagePrimary", UserActionMessages.LEAVE_APPLIED);
				}
				else {
					session.setAttribute("userActionMessageFlag", "true");
					session.setAttribute("userActionMessagePrimary", UserActionMessages.LEAVE_APPLIED);
					if(leaveType.equalsIgnoreCase("planned"))
						session.setAttribute("userActionMessageSecondary", UserActionMessages.CONTACT_ADMIN_FOR_APPROVAL);
				}				
			} catch(ParseException e) {
				System.out.println("ERROR Occured while adding leave.");
				session.setAttribute("pageName", "error"); 
			}	
		} 		
		else {
			if(((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("1")) {
				request.setAttribute("userActionMessagePrimary", UserActionMessages.SORRY);
				request.setAttribute("userActionMessageSecondary", UserActionMessages.LEAVE_DATE_ERROR);
			}
			else {
				session.setAttribute("userActionMessageFlag", "true");
				session.setAttribute("userActionMessagePrimary", UserActionMessages.SORRY);
				session.setAttribute("userActionMessageSecondary", UserActionMessages.LEAVE_DATE_ERROR);				
			}
			System.out.println("Couldn't add leave due to date error.");
		}
		RequestDispatcher rd = request.getRequestDispatcher("SwitchPages?pageName=addLeave");
		rd.forward(request, response);			
	}
}
