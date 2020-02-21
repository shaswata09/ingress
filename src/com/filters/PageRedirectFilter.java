package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.Employee;
import com.enums.AdminPages;
import com.enums.EmployeePages;

@WebFilter("/PageRedirectFilter")
public class PageRedirectFilter implements Filter {

	private ServletContext context;
	private static String pageName = null;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("PageRedirectFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		pageName = request.getParameter("pageName");
		this.context.log("Requested Resource:: "+uri);
		this.context.log("Requested URI ends With:: "+pageName);
		
		HttpSession session = req.getSession(false);
		RequestDispatcher requestDispatcher = null;		
		
		if(session.getAttribute("user")==null) {
			//send to login
		}
		else {//user not null
			if(session.getAttribute("pageName")==null) {
				//send to dash with pagename dash
			}
			else {
				if(((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("0")) {
					if (EmployeePages.contains(pageName)) {
						session.setAttribute("pageName",pageName);
						requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
						requestDispatcher.forward(request,response);
				    }
					else {
						session.invalidate();
						requestDispatcher = request.getRequestDispatcher("login.jsp");
						requestDispatcher.forward(request,response);
					}
				}
				else if(((Employee)session.getAttribute("user")).getEmployeeAccessRight().equals("1")) {
					if (AdminPages.contains(pageName)) {
						session.setAttribute("pageName",pageName);
						requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
						requestDispatcher.forward(request,response);
				    }
					else {
						session.invalidate();
						requestDispatcher = request.getRequestDispatcher("login.jsp");
						requestDispatcher.forward(request,response);
					}
				}
				//send to page with pagename
				else {
					session.invalidate();
					requestDispatcher = request.getRequestDispatcher("login.jsp");
					requestDispatcher.forward(request,response);
				}
			}
		}

	}

	public void destroy() {
		//close any resources here
	}

}
