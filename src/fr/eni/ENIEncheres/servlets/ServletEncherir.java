package fr.eni.ENIEncheres.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ENIEncheres.bll.ArticlesManager;
import fr.eni.ENIEncheres.bll.EncheresManager;
import fr.eni.ENIEncheres.bll.UtilisateursManager;
import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * On va ajouter une nouvelle enchère à l'article
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère l'id de l'utilisateur de la session qui enchérit
		HttpSession session = request.getSession();
		int idUtilisateur = 3;
				//(int) session.getAttribute("idUser");
		
		//On récupère l'id de l'article
		int idArticle = Integer.valueOf(request.getParameter("idArticle"));
		
		//On sélectionne l'utilisateur
		Utilisateurs user = null;
		UtilisateursManager manUtilisateur = new UtilisateursManager();
		try {
			user = manUtilisateur.selectUserById(idUtilisateur);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		//S'il n'a pas assez de crédit on le renvoie vers la page avec un message d'erreur
		if(user.getCredit() < Integer.valueOf(request.getParameter("proposition"))) {
			request.setAttribute("erreurCredit", "Vous n'avez pas assez de crédit disponible. Crédit disponible : " + user.getCredit());
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/ServletDetailArticle");
			rd.forward(request, response);
		} else {
		
		//On rembourse l'utilisateur d'avant
			//On récupère la dernière enchère sur cet article
		EncheresManager manEnchere = new EncheresManager();
		Enchere oldEnchere = null;
		try {
			oldEnchere = manEnchere.rechercherEncheresArticle(idArticle);
		} catch (DALException e1) {
			e1.printStackTrace();
		}
			//On rembourse la dernière personne à avoir fait une enchère
		if(oldEnchere != null) {
			Utilisateurs userOldEnchere;
			try {
				userOldEnchere = manUtilisateur.selectUserById(oldEnchere.getIdUtilisateur());
				int creditBack = userOldEnchere.getCredit() + oldEnchere.getMontantEnchere();
				manUtilisateur.misAJourCredit(userOldEnchere.getIdUtilisateur(), creditBack);
				//On supprime cette enchère
				manEnchere.supprimerEnchere(userOldEnchere.getIdUtilisateur());
			} catch (DALException e1) {
				e1.printStackTrace();
			}
		}
			
		//On crée une nouvelle enchère
		int montantEnchere = Integer.valueOf(request.getParameter("proposition"));
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		Enchere enchere = new Enchere(idUtilisateur, idArticle, date, montantEnchere);
		
		try {
			manEnchere.ajouterEnchere(enchere);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		//On met à jour le montant du prix dans l'article
		ArticlesManager manArticle = new ArticlesManager();
		manArticle.misAJourPrixVente(idArticle, montantEnchere);
		
		//On enlève le crédit utilisé à l'utilisateur
		int creditRestant = user.getCredit() - montantEnchere;
		try {
			manUtilisateur.misAJourCredit(idUtilisateur, creditRestant);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
		//On renvoie à la jsp en passant par la servlet afin d'inclure les données de l'article
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/ServletDetailArticle");
		rd.forward(request, response);
		}
		
	}

}
