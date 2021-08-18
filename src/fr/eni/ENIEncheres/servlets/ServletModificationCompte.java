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
@WebServlet("/ServletModificationCompte")
public class ServletModificationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/ModificationCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");		
		String email = request.getParameter("email");		
		String telephone = request.getParameter("telephone");		
		String rue = request.getParameter("rue");		
		String codePostal = request.getParameter("code_postal");		
		String ville = request.getParameter("ville");	
		String passwordNouveau = request.getParameter("password-nouveau");
		String passwordActuel = request.getParameter("password-actuel");		
		String passwordConfirmation = request.getParameter("password-confirm");		
		
		
		
//		TEST RECUP VALEURS
		System.out.println(pseudo);
		System.out.println(nom);
		System.out.println(prenom);
		System.out.println(email);
		System.out.println(telephone);
		System.out.println(rue);
		System.out.println(codePostal);
		System.out.println(ville);
		System.out.println(passwordNouveau);		
		System.out.println(passwordActuel);
		System.out.println(passwordConfirmation);	
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
