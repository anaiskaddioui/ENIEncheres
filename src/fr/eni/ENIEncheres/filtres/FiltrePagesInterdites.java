package fr.eni.ENIEncheres.filtres;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltrePagesInterdites
 */
@WebFilter(urlPatterns="/*",
			dispatcherTypes= {
					DispatcherType.REQUEST,
					DispatcherType.INCLUDE,
					DispatcherType.FORWARD,
					DispatcherType.ERROR
			}
		)
public class FiltrePagesInterdites extends HttpServlet implements Filter {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		
		
		if((session.getAttribute("isConnected") == "false") && (
				(httpRequest.getServletPath().toLowerCase().contains("connecte")) ||  //A compléter selon règles de vente
				(httpRequest.getServletPath().toLowerCase().contains("consultation")) ||
				(httpRequest.getServletPath().toLowerCase().contains("modification")) ||
				(httpRequest.getServletPath().toLowerCase().contains("profil")) )
				) 
		{
			
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
			
		} 
		else {
			//Ok
		}
		
		chain.doFilter(request, response);
	}

}
