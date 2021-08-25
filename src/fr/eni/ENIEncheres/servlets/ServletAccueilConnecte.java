package fr.eni.ENIEncheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String libelleCategorie = request.getParameter("categories");
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

//--------------------------		
//TODO NE FONCTIONNE PAS	
//-----------------------------		
		//Recupere l'id de session
		HttpSession session = request.getSession();
		int idSession;
		
		if(session.getAttribute("idUser")== null) {
			idSession = Integer.parseInt((String) session.getAttribute("idUser"));
		}
		else {
			idSession = 2;
		}
//-------------------------------------		
		
//-________________________________________________________________________________________________________
		
		ArticlesManager articlesManagerCaseACocher = new ArticlesManager();
		List<ArticleVendu> listeArticlesSelonCasesCochees = new ArrayList<ArticleVendu>();
		
		RequestDispatcher rdCaseACocher;	
		
		//Recupere les valeurs des checkbox ACHAT & VENTE et les stocke dans un tableau		
		String valeursCBoxAchat[] = request.getParameterValues("cbox-achat");
		String valeursCBoxVente[] = request.getParameterValues("cbox-vente");
		
		try {


			
			
//--------------------------------------------------------			
//0- Si saisie dans CATEGORIE & RECHERCHE
//--------------------------------------------------------			
				if((!recherche.contentEquals("")) && (!libelleCategorie.contentEquals(""))) {
					System.out.println("categorie selectionnée : " + libelleCategorie + " et recherche par mot " + recherche);
	//--------------------------------------------------------			
	//0- Si CATEGORIE INFORMATIQUE
	//--------------------------------------------------------										
					if(libelleCategorie.contentEquals("informatique")) {
	//------------------------------------------------------------------------------
	//0.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//0.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec categorie : " + libelleCategorie + " et recherche par mot " + recherche);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategEtMotCle("EC", 1, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("EC", idSession, 1, recherche);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees categorie : " + libelleCategorie  + " et recherche par mot " + recherche);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("TE", idSession, 1, recherche);;// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("Sortie de : 0.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie");
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//0.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//0.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategEtMotCle("EC", 1, recherche);;// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("ND", idSession, 1, recherche);;// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("TE", idSession, 1, recherche);;// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
//												if (valeursCBoxVente == null || valeursCBoxVente.length == 0){
//													System.out.println("selection vide afficahge de toutes vos ventes");
//													listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParUserId(2);// PARAM A remplacer TODO		
//												}
						else {
							System.out.println("Sortie de : 0.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie");
						}
					}
	//--------------------------------------------------------			
	//0- Si CATEGORIE AMEUBLEMENT
	//--------------------------------------------------------						
					
					if(libelleCategorie.contentEquals("ameublement")) {
	//------------------------------------------------------------------------------
	//0.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//0.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategEtMotCle("EC", 2, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("EC", idSession, 2, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("TE", idSession, 2, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("ERREUR");
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//0.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//0.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategEtMotCle("EC", 2, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("ND", idSession, 2, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("TE", idSession, 2, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
//																if (valeursCBoxVente == null || valeursCBoxVente.length == 0){
//																	System.out.println("selection vide afficahge de toutes vos ventes");
//																	listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParUserId(2);// PARAM A remplacer TODO		
//																}
						else {
							System.out.println("ERREUR");
						}
					}
	//--------------------------------------------------------			
	//0- Si CATEGORIE VETEMENT
	//--------------------------------------------------------						
					
					if(libelleCategorie.contentEquals("vetement")) {
	//------------------------------------------------------------------------------
	//0.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//0.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategEtMotCle("EC", 3, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("EC", idSession, 3, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("TE", idSession, 3, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("ERREUR");
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//0.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//0.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategEtMotCle("EC", 3, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("ND", idSession, 3, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("TE", idSession, 3, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
//									if (valeursCBoxVente == null || valeursCBoxVente.length == 0){
//										System.out.println("selection vide affichage de toutes vos ventes");
//										listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParUserId(2);// PARAM A remplacer TODO		
//									}
						else {
							System.out.println("ERREUR");
						}
					}
	//--------------------------------------------------------			
	//0- Si CATEGORIE SPORT
	//--------------------------------------------------------						
					
					if(libelleCategorie.contentEquals("sport")) {
	//------------------------------------------------------------------------------
	//0.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//0.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategEtMotCle("EC", 4, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("EC", idSession, 4, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees avec categorie : " + libelleCategorie  + " et recherche par mot " + recherche);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("TE", idSession, 4, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("ERREUR");
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//0.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//0.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategEtMotCle("EC", 4, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("ND", idSession, 4, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategEtMotCle("TE", idSession, 4, recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
//									if (valeursCBoxVente == null || valeursCBoxVente.length == 0){
//										System.out.println("selection vide affichage de toutes vos ventes");
//										listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParUserId(2);// PARAM A remplacer TODO		
//									}
						else {
							System.out.println("ERREUR");
						}
					}
				}						
			
				
			
//--------------------------------------------------------			
//1- Si saisie dans CATEGORIE
//--------------------------------------------------------	
				if(!libelleCategorie.contentEquals("") && recherche.contentEquals("")) {
					System.out.println("categorie selectionnée : " + libelleCategorie);
	//--------------------------------------------------------			
	//1- Si CATEGORIE INFORMATIQUE
	//--------------------------------------------------------						
					
					if(libelleCategorie.contentEquals("informatique")) {
	//------------------------------------------------------------------------------
	//1.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//1.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec avec categorie : " + libelleCategorie);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 1);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec avec categorie : " + libelleCategorie);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("EC", idSession, 1);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees avec categorie : " + libelleCategorie);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("TE", idSession, 1);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("Sortie de : 1- Si saisie dans CATEGORIE / Info / Achat");
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 1);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//1.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//1.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("EC", idSession, 1);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("ND", idSession, 1);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("TE", idSession, 1);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
//									if (valeursCBoxVente == null || valeursCBoxVente.length == 0){
//										System.out.println("selection vide afficahge de toutes vos ventes");
//										listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParUserId(2);// PARAM A remplacer TODO		
//									}
						else {
							System.out.println("Sortie de : 1- Si saisie dans CATEGORIE / Info / Vente");
							List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 1);
							for (ArticleVendu article : listeTampon) {
								listeArticlesSelonCasesCochees.add(article);
							}
						}
					}
	//--------------------------------------------------------			
	//1- Si CATEGORIE AMEUBLEMENT
	//--------------------------------------------------------						
					
					if(libelleCategorie.contentEquals("ameublement")) {
	//------------------------------------------------------------------------------
	//1.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//1.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec avec categorie : " + libelleCategorie);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 2);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec avec categorie : " + libelleCategorie);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("EC", idSession, 2);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees avec categorie : " + libelleCategorie);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("TE", idSession, 2);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("Sortie de : 1- Si saisie dans CATEGORIE / Ameubl / Achat");
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 2);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//1.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//1.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("EC", idSession, 2);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("ND", idSession, 2);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("TE", idSession, 2);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
//													if (valeursCBoxVente == null || valeursCBoxVente.length == 0){
//														System.out.println("selection vide afficahge de toutes vos ventes");
//														listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParUserId(2);// PARAM A remplacer TODO		
//													}
						else {
							System.out.println("Sortie de : 1- Si saisie dans CATEGORIE / Ameubl / Vente");
							List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 2);
							for (ArticleVendu article : listeTampon) {
								listeArticlesSelonCasesCochees.add(article);
							}
						}
					}
	//--------------------------------------------------------			
	//1 - Si CATEGORIE VETEMENT
	//--------------------------------------------------------						
					
					if(libelleCategorie.contentEquals("vetement")) {
	//------------------------------------------------------------------------------
	//1.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//1.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec avec categorie : " + libelleCategorie);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 3);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec avec categorie : " + libelleCategorie);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("EC", idSession, 3);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees avec categorie : " + libelleCategorie);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("TE", idSession, 3);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("Sortie de : 1- Si saisie dans CATEGORIE / Vetement / Achat");
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 3);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//1.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//1.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("EC", idSession, 3);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("ND", idSession, 3);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("TE", idSession, 3);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
						else {
							System.out.println("Sortie de : 1- Si saisie dans CATEGORIE / Vetement / Vente");
							List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 3);
							for (ArticleVendu article : listeTampon) {
								listeArticlesSelonCasesCochees.add(article);
							}
						}
					}
	//--------------------------------------------------------			
	//1- Si CATEGORIE SPORT
	//--------------------------------------------------------						
					
					if(libelleCategorie.contentEquals("sport")) {
	//------------------------------------------------------------------------------
	//1.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//1.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec avec categorie : " + libelleCategorie);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 4);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec avec categorie : " + libelleCategorie);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("EC", idSession, 4);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees avec categorie : " + libelleCategorie);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("TE", idSession, 4);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("Sortie de : 1- Si saisie dans CATEGORIE / Sport / Achat");
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 4);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//1.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//1.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("EC", idSession, 4);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("ND", idSession, 4);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtCategorie("TE", idSession, 4);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
						else {
							System.out.println("Sortie de : 1- Si saisie dans CATEGORIE / Sport / Vente");
							List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtCategorie("EC", 4);
							for (ArticleVendu article : listeTampon) {
								listeArticlesSelonCasesCochees.add(article);
							}
						}
					}
				}			
			
			
//--------------------------------------------------------			
//2- Si saisie dans RECHERCHE
//--------------------------------------------------------			
				if(!recherche.contentEquals("") && libelleCategorie.contentEquals("")) {
					System.out.println("qqchose saisi "+ recherche +" dans motcle");
	
	//------------------------------------------------------------------------------
	//2.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------
						if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//2.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------					
							for (int i = 0; i < valeursCBoxAchat.length; i++) {
								if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
									System.out.println("vous avez choisi encheres-ouvertes avec mot cle : " + recherche);
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtMotCle("EC", recherche);
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
									System.out.println("vous avez choisi vos-encheres-en-cours avec mot cle : " + recherche);// A remplacer TODO
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtMotCle("EC", idSession, recherche);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
									System.out.println("vous avez choisi vos-encheres-remportees avec mot cle : " + recherche);
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtMotCle("TE", idSession, recherche);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								else {
									System.out.println("Sortie de : 2- Si saisie dans RECHERCHE / Achat");
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerArticlesParMotCle(recherche);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
						
	//------------------------------------------------------------------------------
	//2.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------			
						if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
			//-------------------------------------------------------------------------------------------------------------					
			//2.2.1--Pour chaque ligne du tableau VENTE, si la valeur correspond à la case cochée, on affiche la requete choisie
			//--------------------------------------------------------------------------------------------------------------	
								for (int i = 0; i < valeursCBoxVente.length; i++) {
								if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
									System.out.println("vous avez choisi ventes-en-cours");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtMotCle("EC", idSession, recherche);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}														
								}
								if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
									System.out.println("vous avez choisi ventes-non-debutees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtMotCle("ND", idSession, recherche);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
								if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
									System.out.println("vous avez choisi ventes-terminees");
									//TODO remplacer l'id en dur par l'id de session
									List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserIdEtMotCle("TE", idSession, recherche);// PARAM A remplacer TODO
									for (ArticleVendu article : listeTampon) {
										listeArticlesSelonCasesCochees.add(article);
									}
								}
							}
						}
						else {
							System.out.println("Sortie de : 2- Si saisie dans RECHERCHE / Vente");
							List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerArticlesParMotCle(recherche);// PARAM A remplacer TODO
							for (ArticleVendu article : listeTampon) {
								listeArticlesSelonCasesCochees.add(article);
							}
						}				
				}
				
				
//--------------------------------------------------------------------
//3- Si rien de saisi dans RECHERCHE ni CATEGORIE
//---------------------------------------------------------------------
				if(recherche.contentEquals("") && libelleCategorie.contentEquals("")) {
					System.out.println("Pas de saisie dans recherche ou par categ");				
				
	//------------------------------------------------------------------------------
	//3.1 - Si Radio sur ACHAT
	//--------------------------------------------------------------------------------	
					if (valeursCBoxAchat != null && valeursCBoxAchat.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//3.1.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------	
						for (int i = 0; i < valeursCBoxAchat.length; i++) {
							if(valeursCBoxAchat[i].contentEquals("encheres-ouvertes")) {
								System.out.println("vous avez choisi encheres-ouvertes");
								List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerTousLesArticlesByEtat("EC");
								for (ArticleVendu article : listeTampon) {
									listeArticlesSelonCasesCochees.add(article);
								}
							}
							if(valeursCBoxAchat[i].contentEquals("encheres-en-cours")) {
								System.out.println("vous avez choisi vos-encheres-en-cours");
								//TODO remplacer l'id en dur par l'id de session								
								List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserId("EC", idSession);
								for (ArticleVendu article : listeTampon) {
									listeArticlesSelonCasesCochees.add(article);
								}									
							}
							if(valeursCBoxAchat[i].contentEquals("encheres-remportees")) {
								System.out.println("vous avez choisi vos-encheres-remportees");
								//TODO remplacer l'id en dur par l'id de session
								List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserId("TE", idSession);
								for (ArticleVendu article : listeTampon) {
									listeArticlesSelonCasesCochees.add(article);
								}		
							}
							else {
								System.out.println("Sortie de : 3- Si rien de saisi dans RECHERCHE ni CATEGORIE // Achat");
							}
						}
					}		
					
	//------------------------------------------------------------------------------
	//3.2 - Si Radio sur VENTE
	//--------------------------------------------------------------------------------	
					if (valeursCBoxVente != null && valeursCBoxVente.length != 0) {
		//-------------------------------------------------------------------------------------------------------------					
		//3.2.1--Pour chaque ligne du tableau ACHAT, si la valeur correspond à la case cochée, on affiche la requete choisie
		//--------------------------------------------------------------------------------------------------------------	
						for (int i = 0; i < valeursCBoxVente.length; i++) {
							if(valeursCBoxVente[i].contentEquals("ventes-en-cours")) {
								System.out.println("vous avez choisi ventes-en-cours");
								//TODO remplacer l'id en dur par l'id de session
								List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserId("EC", idSession);// PARAM A remplacer TODO
								for (ArticleVendu article : listeTampon) {
									listeArticlesSelonCasesCochees.add(article);
								}														
							}
							if(valeursCBoxVente[i].contentEquals("ventes-non-debutees")) {
								System.out.println("vous avez choisi ventes-non-debutees");
								//TODO remplacer l'id en dur par l'id de session
								List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserId("ND", idSession);// PARAM A remplacer TODO
								for (ArticleVendu article : listeTampon) {
									listeArticlesSelonCasesCochees.add(article);
								}
							}
							if(valeursCBoxVente[i].contentEquals("ventes-terminees")) {
								System.out.println("vous avez choisi ventes-terminees");
								//TODO remplacer l'id en dur par l'id de session
								List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerParEtatEtUserId("TE", idSession);// PARAM A remplacer TODO
								for (ArticleVendu article : listeTampon) {
									listeArticlesSelonCasesCochees.add(article);
								}
							}
						}
					}
//					if (valeursCBoxVente == null || valeursCBoxVente.length == 0){
//						System.out.println("selection vide affichage de toutes vos ventes");
//						listeArticlesSelonCasesCochees = articlesManagerCaseACocher.selectionnerParUserId(2);// PARAM A remplacer TODO		
//					}
					else {
						System.out.println("Sortie de : 3- Si rien de saisi dans RECHERCHE ni CATEGORIE // Vente");
						System.out.println("vous avez choisi ventes");
						//TODO remplacer l'id en dur par l'id de session
						List<ArticleVendu> listeTampon = articlesManagerCaseACocher.selectionnerTousLesArticles();
						for (ArticleVendu article : listeTampon) {
							listeArticlesSelonCasesCochees.add(article);
						}
					}						
					
				}
				else {
					System.out.println("Sortie de la boucle");
				}
						
						System.out.println(listeArticlesSelonCasesCochees);
						
			
// 4 - On pousse les infos des champs formulaire à la JSP pour les afficher comme criteres
			request.setAttribute("listeArticlesSelonCasesCochees", listeArticlesSelonCasesCochees);	
//			request.setAttribute("libelleCategorie", libelleCategorie );	
//			request.setAttribute("articleContient", recherche );
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
			rd.forward(request, response);
						
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//Fin du doPost

}
