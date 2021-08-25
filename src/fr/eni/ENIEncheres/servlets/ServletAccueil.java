package fr.eni.ENIEncheres.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEncheres.bll.ArticlesManager;
import fr.eni.ENIEncheres.bll.CategorieManager;
import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.dal.DALException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//------------------------------------------------------------------------------------
		//1 - MISE A JOUR DU STATUT ENCHERE A TERMINE SI INF A  DATE DU JOUR 
		//------------------------------------------------------------------------------------
		
		//Recupere la date du jour et la convertit en STRING
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
	   //OU 
	   //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
	   LocalDateTime now = LocalDateTime.now();  
	   String dateDuJour = (String) dtf.format(now); 
	   System.out.println(dateDuJour); 
	
	   ArticlesManager articlesManagerFinEnchere = new ArticlesManager();
	   
	   
	   try {
		articlesManagerFinEnchere.miseAJourDateFinEnchere(dateDuJour);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
		//------------------------------------------------------------------------------------
		//2 - AFFICHE LES ENCHERES EN COURS 
		//------------------------------------------------------------------------------------	

		//Recupere champs du formulaire de la JSP
		String libelleCategorie = request.getParameter("categorie");	
		String recherche = request.getParameter("recherche");	
		
		//Procede a la requete de selection des articles en cours d'encheres
		//Puis les affiche dans la JSP
		ArticlesManager articlesManager2 = new ArticlesManager();
		List<ArticleVendu> listeArticlesEnCours;
		
		RequestDispatcher rdis;	
		
		try {
			listeArticlesEnCours = articlesManager2.selectionnerTousLesArticlesByEtat("EC");
			request.setAttribute("listeArticlesEnCours", listeArticlesEnCours );	
			rdis = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			rdis.forward(request, response);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//-----------------------------------------------------------------------------------------------------------------------------------------

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticlesManager articlesManager = new ArticlesManager();
		List<ArticleVendu> listeArticlesFiltre= null;
		
//		Recupere champs du formulaire de la JSP
		String libelleCategorie = request.getParameter("categorie");	
		String recherche = request.getParameter("recherche");		

	
//-----------------------------------------------------------------
// SECTION DES FILTRES DE RECHERCHE
//-------------------------------------------------------------------
		
		try {
			
//	1 -- Si saisie dans la barre de recherche + filtre categorie
			if((!recherche.contentEquals("")) && (!libelleCategorie.contentEquals(""))) {
				
		//	1.1 -- On recherche selon le filtre categories et la valeur du mot		
				if(libelleCategorie.contentEquals("informatique")) {
					listeArticlesFiltre = articlesManager.selectionnerArticlesParCategEtMotCle(recherche, 1);
				}
				else if(libelleCategorie.contentEquals("ameublement")) {
					listeArticlesFiltre = articlesManager.selectionnerArticlesParCategEtMotCle(recherche, 2);
				}
				else if(libelleCategorie.contentEquals("vetement")) {
					listeArticlesFiltre = articlesManager.selectionnerArticlesParCategEtMotCle(recherche, 3);
				}
				else if(libelleCategorie.contentEquals("sport")) {
					listeArticlesFiltre = articlesManager.selectionnerArticlesParCategEtMotCle(recherche, 4);	
				}
				
			}
			
//	2- Sinon si uniquement filtre catégorie		
			else if(recherche.contentEquals("")) {
	//	2.1 -- On recherche selon le filtre categories		
				if(libelleCategorie.contentEquals("informatique")) {
	
					listeArticlesFiltre = articlesManager.selectionnerParIdCategorie(1);
				}
				else if(libelleCategorie.contentEquals("ameublement")) {
					listeArticlesFiltre = articlesManager.selectionnerParIdCategorie(2);
				}
				else if(libelleCategorie.contentEquals("vetement")) {
					listeArticlesFiltre = articlesManager.selectionnerParIdCategorie(3);
						
				}
				else if(libelleCategorie.contentEquals("sport")) {
					listeArticlesFiltre = articlesManager.selectionnerParIdCategorie(4);
				}
				else {
					listeArticlesFiltre = articlesManager.selectionnerTousLesArticlesByEtat("EC");	
				}
			}
			
// 2 - Sinon si uniquement saisie
			else {
				listeArticlesFiltre = articlesManager.selectionnerArticlesParMotCle(recherche);

			}
			
// 3 - On pousse les infos des champs formulaire à la JSP pour les afficher comme criteres
			request.setAttribute("listeArticlesEnCours", listeArticlesFiltre );	
			request.setAttribute("libelleCategorie", libelleCategorie );	
			request.setAttribute("articleContient", recherche );
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
			rd.forward(request, response);
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
