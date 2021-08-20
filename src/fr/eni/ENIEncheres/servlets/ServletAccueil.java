package fr.eni.ENIEncheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEncheres.bll.ArticlesManager;
import fr.eni.ENIEncheres.bo.ArticleVendu;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rdis;	
		rdis = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		rdis.forward(request, response);

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Recupere champs formulaire

		
		ArticlesManager articlesManager = new ArticlesManager();
		List<ArticleVendu> listeArticlesEnCours;
		
//		Recupere champs Catégorie et filtre la selection SINON affiche les ventes en cours
		String libelleCategorie = request.getParameter("categorie");
		System.out.println(libelleCategorie);
		
		String recherche = request.getParameter("recherche");		

//		TEST RECUP VALEURS
//		System.out.println(recherche);
	
//		Filtre la selection selon la catégorie selectionnée
		try {
			if(libelleCategorie=="informatique") {

				listeArticlesEnCours = articlesManager.selectionnerParIdCategorie(1);
				System.out.println("requete 1");				
			}
			else if(libelleCategorie=="ameublement") {
					listeArticlesEnCours = articlesManager.selectionnerParIdCategorie(2);
					System.out.println("requete 2");				
			}
			else if(libelleCategorie=="vetement") {
					listeArticlesEnCours = articlesManager.selectionnerParIdCategorie(3);
					System.out.println("requete 3");				
					
			}
			else if(libelleCategorie=="sport") {
					listeArticlesEnCours = articlesManager.selectionnerParIdCategorie(4);
					System.out.println("requete 4");				
			}
			else {
					listeArticlesEnCours = articlesManager.selectionnerTousLesArticlesByEtat("EC");	
					System.out.println("requete else");
			}
			request.setAttribute("listeArticlesEnCours", listeArticlesEnCours );		
			RequestDispatcher rdis;
			rdis = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			rdis.forward(request, response);
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// TODO Auto-generated method stub
//		doGet(request, response);

}
