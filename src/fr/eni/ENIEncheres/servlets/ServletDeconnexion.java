package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet("/ServletDeconnexion")
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		boolean deconnexion = request.getParameter("deconnexion") != null;
		
		if (deconnexion) {
			session.setAttribute("isConnected", false);
			session.removeAttribute("idUser");
	
			RequestDispatcher rd2;
			rd2 = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			rd2.forward(request, response);
		}
//		doGet(request, response);	
	}

}
