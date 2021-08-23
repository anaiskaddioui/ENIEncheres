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
		ArticlesManager articlesManagerVentesEnCours = new ArticlesManager();
		List<ArticleVendu> listeArticlesEnCoursConnecte;
		
		RequestDispatcher rdVenteEnCours;	
		
		
		try {
			listeArticlesEnCoursConnecte = articlesManagerVentesEnCours.selectionnerTousLesArticlesByEtat("EC");
			request.setAttribute("listeArticlesEnCoursConnecte", listeArticlesEnCoursConnecte );
			rdVenteEnCours = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
			rdVenteEnCours.forward(request, response);
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
		
		
//-________________________________________________________________________________________________________
		
		ArticlesManager articlesManagerCaseACocher = new ArticlesManager();
		List<ArticleVendu> listeArticlesSelonCasesCochees = null;
		
		RequestDispatcher rdCaseACocher;	
		
		//Recupere les valeurs des checkbox ACHAT & VENTE et les stocke dans un tableau		
		String valeursCBoxAchat[] = request.getParameterValues("cbox-achat");
		String valeursCBoxVente[] = request.getParameterValues("cbox-vente");
		
		try {		
			
//-----------------------------------------------------------------
			//TODO remplacer les SYSOUT par une requete qui récupère l'ID de l'utilisatuer, le compare à celui qui a fait la meilleure offre et affiche selon etat EC ou TE
//------------------------------------------------------------------				
			
			
		//Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
			if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
				for (int i = 0; i < valeursCBoxAchat.length; i++) {
					if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
						System.out.println("vous avez choisi encheres-ouvertes"); // A remplacer TODO
					}
					if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
						System.out.println("vous avez choisi encheres-en-cours");// A remplacer TODO
					}
					if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
						System.out.println("vous avez choisi encheres-remportees");// A remplacer TODO
					}
					else {
						System.out.println("ERREUR");
					}
				}
			}
			
//-----------------------------------------------------------------
			//TODO remplacer l'id en dur par l'id de session
//------------------------------------------------------------------			
			
			//Pour chaque ligne du tableau VENTES, si la valeur correspond à la case cochée, on affiche la requete choisie
			if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
				for (int i = 0; i < valeursCBoxVente.length; i++) {
					if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
						System.out.println("vous avez choisi ventes-en-cours");
						listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParEtatEtUserId("EC", 2);// PARAM A remplacer TODO
//AAAA						
//						List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserId("EC", 2);// PARAM A remplacer TODO
//						for (ArticleVendu article : listeTampon) {
//							listeArticlesSelonCasesCochees.add(article);
//						}
//						System.out.println(listeArticlesSelonCasesCochees);
//AAAA									
						
					}
					if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
						System.out.println("vous avez choisi ventes-non-debutees");
						listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParEtatEtUserId("ND", 2);// PARAM A remplacer TODO
					}
					if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
						System.out.println("vous avez choisi ventes-terminees");
						listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParEtatEtUserId("TE", 2);// PARAM A remplacer TODO
					}
					else {
						System.out.println("ERREUR");
					}
				}
			}
			
// 3 - On pousse les infos des champs formulaire à la JSP pour les afficher comme criteres
			request.setAttribute("listeArticlesSelonCasesCochees", listeArticlesSelonCasesCochees);	
//			request.setAttribute("libelleCategorie", libelleCategorie );	
//			request.setAttribute("articleContient", recherche );
			System.out.println(listeArticlesSelonCasesCochees);
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
			rd.forward(request, response);
						
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
