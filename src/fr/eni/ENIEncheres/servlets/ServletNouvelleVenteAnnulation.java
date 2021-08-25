package fr.eni.ENIEncheres.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEncheres.bll.ArticlesManager;
import fr.eni.ENIEncheres.bll.CategorieManager;
import fr.eni.ENIEncheres.bll.RetraitManager;
import fr.eni.ENIEncheres.bll.UtilisateursManager;
import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Retrait;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

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
		//Si on a cliqué sur le bouton "Supprimer"
		System.out.println(request.getParameter("modification"));
		if( request.getParameter("modification").equalsIgnoreCase("supprimer")) {
			//On regarde si l'enchère n'a pas déjà commencé
			Date today = new Date(System.currentTimeMillis());
			ArticlesManager manArticle = new ArticlesManager();
			int idArticle = Integer.valueOf(request.getParameter("idArticle"));
			ArticleVendu articleCourant = manArticle.selectArticleByID(idArticle);
			//Si l'enchère n'a pas commencé, on peut supprimer
			if(today.before(articleCourant.getDateDebutEncheres())) {
				manArticle.supprimerArticle(idArticle);
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
				rd.forward(request, response);
				//Si l'enchère a commencé, on ne peut pas supprimer et l'indiquer à l'utilisateur
			} else if (today.equals(articleCourant.getDateDebutEncheres()) | today.after(articleCourant.getDateDebutEncheres())) {
				request.setAttribute("erreurSuppression", "Vous ne pouvez pas supprimer un article dont la vente a commencé");
				request.setAttribute("idArticle", Integer.valueOf(request.getParameter("idArticle")));
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/ServletDetailArticle");
				rd.forward(request, response);
			}
			//Si on a cliqué sur le bouton "Modifier"
			} else if(request.getParameter("modification").equalsIgnoreCase("modifier")) {
				//On regarde si l'enchère n'a pas déjà commencé
				Date today = new Date(System.currentTimeMillis());
				ArticlesManager manArticle = new ArticlesManager();
				int idArticle = Integer.valueOf(request.getParameter("idArticle"));
				ArticleVendu articleCourant = manArticle.selectArticleByID(idArticle);
				//En fonction de si l'enchère a commencé, on va mettre un attribut de requête qui va nous dire ce qu'on pourra modifier
				if(today.equals(articleCourant.getDateDebutEncheres()) | today.after(articleCourant.getDateDebutEncheres())) {
					request.setAttribute("venteCommencee", "true");
				} 
				//On va récupérer les données à indiquer dans le formulaire
				
				//On récupère le retrait associé à l'article
				RetraitManager manRetrait = new RetraitManager();
				Retrait retrait = null;
				try {
					retrait = manRetrait.getRetraitByNoArticle(idArticle);
				} catch (DALException e) {
					e.printStackTrace();
				}
				
				
				//On récupère le nom de la catégorie
				CategorieManager manCategorie = new CategorieManager();
				Categorie categorie = null;
				try {
					categorie = manCategorie.getCategorieById(articleCourant.getIdCategorie());
				} catch (DALException e) {
					e.printStackTrace();
				}
				
				//On récupère le pseudo du vendeur
				UtilisateursManager manUtilisateur = new UtilisateursManager();
				Utilisateurs utilisateur = null;
				try {
					utilisateur = manUtilisateur.selectUserById(articleCourant.getIdUtilisateur());
				} catch (DALException e) {
					e.printStackTrace();
				}
				
				
				//On met toutes les caractéristiques en attribut de requête
				request.setAttribute("idArticle", articleCourant.getIdArticle());
				request.setAttribute("nom_article", articleCourant.getNomArticle());
				request.setAttribute("description", articleCourant.getDescription());
				request.setAttribute("prix_vente", articleCourant.getPrixVente());
				request.setAttribute("prix_initial", articleCourant.getPrixInitial());
				request.setAttribute("date_debut_encheres", articleCourant.getDateDebutEncheres());
				request.setAttribute("date_fin_encheres", articleCourant.getDateFinEncheres());
				
				if(retrait != null) {
					request.setAttribute("rue_retrait", retrait.getRue());
					request.setAttribute("codePostal_retrait", retrait.getCodePostal());
					request.setAttribute("ville_retrait", retrait.getVille());
				}
				if(categorie != null) {
					request.setAttribute("categorie", categorie.getLibelle());
				}
				if(utilisateur != null) {
					request.setAttribute("utilisateur", utilisateur.getPseudo());
					request.setAttribute("idVendeur", utilisateur.getIdUtilisateur());
				}
			
				
//				TEST RECUP VALEURS
				System.out.println(articleCourant);
				
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/WEB-INF/jsp/NouvelleVenteAnnulation.jsp");
				rd.forward(request, response);
				
			}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//Récupération des données de l'article
		String nom_article = request.getParameter("article");
		int idArticle = Integer.valueOf(request.getParameter("idArticle"));
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");	
		System.out.println(request.getParameter("prix"));
		String test = request.getParameter("prix");
		int prix = Integer.valueOf(request.getParameter("prix"));		
		Date dateDebut = new Date(
				(LocalDate.parse(request.getParameter("date-debut")).atStartOfDay(ZoneId.systemDefault()).toEpochSecond())* 1000);
		Date dateFin = new Date(
				(LocalDate.parse(request.getParameter("date-fin")).atStartOfDay(ZoneId.systemDefault()).toEpochSecond())* 1000);	
		
		//Récupération de l'idCategorie
		CategorieManager manCategorie = new CategorieManager();
		int idCategorie = 0;
		try {
			idCategorie = manCategorie.getIdCategorie(categorie);
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		//Création d'un article
		ArticleVendu nouvelArticle = new ArticleVendu(idArticle, nom_article, description, dateDebut, dateFin, prix, idCategorie);
		
		//Mise à jour de l'article
		ArticlesManager manArticle = new ArticlesManager();
		manArticle.modifierArticle(nouvelArticle);
		
		//Mise à jour du retrait
		String rue = request.getParameter("rue");	
		int codePostal = Integer.valueOf(request.getParameter("code-postal"));		
		String ville = request.getParameter("ville");
		Retrait retrait = new Retrait(idArticle, rue, codePostal, ville);
		RetraitManager manRetrait = new RetraitManager();
		try {
			manRetrait.updateRetrait(retrait);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
//		TEST RECUP VALEURS
		System.out.println(description);
		System.out.println(categorie);
		System.out.println(prix);
		System.out.println(dateDebut);
		System.out.println(dateFin);		
		System.out.println(rue);
		System.out.println(codePostal);
		System.out.println(ville);

		request.setAttribute("idArticle", idArticle);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/ServletDetailArticle");
		rd.forward(request, response);
		
	}

}
