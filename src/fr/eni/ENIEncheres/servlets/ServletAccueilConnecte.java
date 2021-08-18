package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueilConnecte")
public class ServletAccueilConnecte extends HttpServlet {
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
//		Liste deroulante catégorie
		String libelleCategorie = request.getParameter("categorie");
//		Text input Recherche
		String recherche = request.getParameter("recherche");
		
//		Radio Achats/vente
		String achatsVentes = request.getParameter("achats-ventes");
		
//		Checkbox Encheres 
		String encheresOuvertes = request.getParameter("encheres-ouvertes");
		String encheresEnCours = request.getParameter("encheres-en-cours");		
		String encheresRemportees = request.getParameter("encheres-remportees");	
		
//		Checkbox Vente
		String ventesEnCours = request.getParameter("ventes-en-cours");
		String ventesNonDebutees = request.getParameter("ventes-non-debutees");		
		String ventesTerminees = request.getParameter("ventes-terminees");		
		
		
		
		
//		TEST RECUP VALEURS
		System.out.println(recherche);
		System.out.println(libelleCategorie);	
		System.out.println(achatsVentes);	
		System.out.println(encheresOuvertes);	
		
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
