package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletNouvelleVenteAnnulation")
public class ServletNouvelleVenteAnnulation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/NouvelleVenteAnnulation.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");		
		String prix = request.getParameter("prix");		
		String dateDebut = request.getParameter("date-debut");		
		String dateFin = request.getParameter("date-fin");	
		String rue = request.getParameter("rue");	
		String codePostal = request.getParameter("code-postal");		
		String ville = request.getParameter("ville");				
		
		
		
//		TEST RECUP VALEURS
		System.out.println(article);
		System.out.println(description);
		System.out.println(categorie);
		System.out.println(prix);
		System.out.println(dateDebut);
		System.out.println(dateFin);		
		System.out.println(rue);
		System.out.println(codePostal);
		System.out.println(ville);

		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
