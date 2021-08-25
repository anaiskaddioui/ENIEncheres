package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEncheres.bll.UtilisateursManager;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletCreationCompte")
public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/CreationCompte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");		
		String email = request.getParameter("email");		
		String telephone = request.getParameter("telephone");		
		String rue = request.getParameter("rue");		
		String codePostal = request.getParameter("code_postal");		
		String ville = request.getParameter("ville");		
		String password = request.getParameter("password");		
		int credit = 0;
		boolean administrateur = false;
		
		
		Utilisateurs u1 = new Utilisateurs(pseudo, password, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur);
		UtilisateursManager managerU = new UtilisateursManager();
		try {
			managerU.ajouterUtilisateur(u1);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		TEST RECUP VALEURS
		System.out.println(pseudo);
		System.out.println(nom);
		System.out.println(prenom);
		System.out.println(email);
		System.out.println(telephone);
		System.out.println(rue);
		System.out.println(codePostal);
		System.out.println(ville);
		System.out.println(password);
		
		//On renvoie vers la page d'accueil (ou de connexion ?)
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/ServletAccueil"); //Si compte créé ==> redirection vers accueil Connecté ?
		rd.forward(request, response);
		
	}

}
