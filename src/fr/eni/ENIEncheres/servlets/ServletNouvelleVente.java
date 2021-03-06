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
import javax.servlet.http.HttpSession;

import fr.eni.ENIEncheres.bll.ArticlesManager;
import fr.eni.ENIEncheres.bll.BLLException;
import fr.eni.ENIEncheres.bll.CategorieManager;
import fr.eni.ENIEncheres.bll.RetraitManager;
import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Retrait;
import fr.eni.ENIEncheres.dal.DALException;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date dateMin = java.sql.Date.valueOf(LocalDate.now(ZoneId.systemDefault()));
		Date dateFin = java.sql.Date.valueOf(LocalDate.now(ZoneId.systemDefault()).plusDays(1));
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Date dateDebutEncheres = new Date(
				(LocalDate.parse(request.getParameter("date-debut")).atStartOfDay(ZoneId.systemDefault()).toEpochSecond())
				* 1000);
		
		Date dateFinEncheres = new Date(
				(LocalDate.parse(request.getParameter("date-fin")).atStartOfDay(ZoneId.systemDefault()).toEpochSecond())
				* 1000);
	
		String nomArticle = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");		
		int prixInitial = Integer.valueOf(request.getParameter("prixInitial"));		
		//String dateDebut = request.getParameter("date-debut");		
		//String dateFin = request.getParameter("date-fin");	
		String rue = request.getParameter("rue");	
		String codePostal = request.getParameter("code-postal");		
		String ville = request.getParameter("ville");				
		
		int prixVente = 0;
		String idUtilisateurString = String.valueOf(session.getAttribute("idUser"));	
		int idUtilisateur = Integer.valueOf(idUtilisateurString);
		int idCategorie = 0;
		try {
			idCategorie = new CategorieManager().getIdCategorie(categorie);
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		String etatVente = "ND";
		
		ArticleVendu newAdd = new ArticleVendu(nomArticle,description, dateDebutEncheres, dateFinEncheres,
				prixInitial, prixVente, etatVente,idUtilisateur, idCategorie);
		ArticlesManager articlesManager = new ArticlesManager();
		ArticleVendu monArticle = null;
		
		

		try {
			monArticle = articlesManager.insertArticle(newAdd);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		
		//Insertion Adresse dans table retrait
		try {
			Retrait retrait = new Retrait(monArticle.getIdArticle(), rue, codePostal, ville);
			RetraitManager retraitManager = new RetraitManager();
			retraitManager.insertRetrait(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("idArticle", monArticle.getIdArticle());
		
//		TEST RECUP VALEURS
		System.out.println(newAdd);
		System.out.println(description);
		System.out.println(categorie);
		System.out.println(prixInitial);
		System.out.println(dateDebutEncheres);
		System.out.println(dateFinEncheres);		
		System.out.println(rue);
		System.out.println(codePostal);
		System.out.println(ville);
		

		RequestDispatcher rd2;
		rd2 = request.getRequestDispatcher("/ServletDetailArticle");
		rd2.forward(request, response);
		
		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
