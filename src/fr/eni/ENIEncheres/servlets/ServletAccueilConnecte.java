package fr.eni.ENIEncheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEncheres.bll.ArticlesManager;
import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.dal.DALException;

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
		
		//Procede a la requete de selection des articles en cours d'encheres
		//Puis les affiche dans la JSP
		ArticlesManager articlesManager2 = new ArticlesManager();
		List<ArticleVendu> listeArticlesEnCours;
		
		RequestDispatcher rdis;	
		
		
		try {
			listeArticlesEnCours = articlesManager2.selectionnerTousLesArticlesByEtat("EC");
			request.setAttribute("listeArticlesEnCours", listeArticlesEnCours );	
			rdis = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
			rdis.forward(request, response);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		System.out.println("Radio boutoon sur : " + achatsVentes);	
		System.out.println("Checkbox encheres-ouvertes : "+ encheresOuvertes);	
		System.out.println("Checkbox encheres-en-cours : "+ encheresEnCours);	
		System.out.println("Checkbox encheres-remportees : "+ encheresRemportees);	
		System.out.println("Checkbox ventes-en-cours : "+ ventesEnCours);	
		System.out.println("Checkbox ventes-non-debutees : "+ ventesNonDebutees);	
		System.out.println("Checkbox ventes-terminees : "+ ventesTerminees);		
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
