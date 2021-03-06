package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEncheres.bll.ArticlesManager;
import fr.eni.ENIEncheres.bll.CategorieManager;
import fr.eni.ENIEncheres.bll.EncheresManager;
import fr.eni.ENIEncheres.bll.RetraitManager;
import fr.eni.ENIEncheres.bll.UtilisateursManager;
import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.bo.Retrait;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletDetailArticle")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//On récupère l'id de l'article sur lequel on a cliqué
		int idArticle = 0;
		System.out.println(request.getParameter("idArticle"));
		if(request.getAttribute("idArticle") != null) {
		idArticle = (int) request.getAttribute("idArticle");
		} else if(request.getParameter("idArticle") != null) {
			idArticle =Integer.valueOf(request.getParameter("idArticle"));
		}
		
		//On sélectionne l'article
		ArticlesManager manArticle = new ArticlesManager();
		ArticleVendu articleCourant = manArticle.selectArticleByID(idArticle);
		
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
		
		//On récupère le pseudo de la personne qui a fait la meilleure enchère
		EncheresManager manEnchere = new EncheresManager();
		Enchere enchere = null;
		String pseudoEnchere = new String();
		int idUserEnchere = 0;
		try {
			enchere = manEnchere.rechercherEncheresArticle(idArticle);
			if(enchere != null) {
				Utilisateurs userEnchere = manUtilisateur.selectUserById(enchere.getIdUtilisateur());
				pseudoEnchere = userEnchere.getPseudo();
				idUserEnchere = enchere.getIdUtilisateur();
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
		//On met toutes les caractéristiques en attribut de requête
		request.setAttribute("idArticle", articleCourant.getIdArticle());
		request.setAttribute("nom_article", articleCourant.getNomArticle());
		request.setAttribute("description", articleCourant.getDescription());
		request.setAttribute("prix_vente", articleCourant.getPrixVente());
		request.setAttribute("prix_initial", articleCourant.getPrixInitial());
		request.setAttribute("date_fin_encheres", articleCourant.getDateFinEncheres());
		request.setAttribute("etat_vente", articleCourant.getEtatVente());
		
		if(retrait != null) {
			request.setAttribute("rue_retrait", retrait.getRue());
			request.setAttribute("codePostal_retrait", retrait.getCodePostal());
			request.setAttribute("ville_retrait", retrait.getVille());
		}
		if(categorie != null) {
			request.setAttribute("categorie", categorie.getLibelle());
		}
		if(utilisateur != null) {
			request.setAttribute("vendeur", utilisateur.getPseudo());
			request.setAttribute("idVendeur", utilisateur.getIdUtilisateur());
		}
		if(enchere != null) {
			request.setAttribute("pseudoEnchere", pseudoEnchere);
			request.setAttribute("idUserEnchere", idUserEnchere);
		}
		
		
//		TEST RECUP VALEURS
		System.out.println(articleCourant);
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/DetailArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
