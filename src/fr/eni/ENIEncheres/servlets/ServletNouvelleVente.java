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

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.DAOFactory;

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
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Date dateDebut = new Date(
				(LocalDate.parse(request.getParameter("date-debut")).atStartOfDay(ZoneId.systemDefault()).toEpochSecond())
				* 1000);
		
		Date dateFin = new Date(
				(LocalDate.parse(request.getParameter("date-fin")).atStartOfDay(ZoneId.systemDefault()).toEpochSecond())
				* 1000);
	
		String nomArticle = request.getParameter("article");
		String description = request.getParameter("description");
		String categorie = request.getParameter("categorie");		
		int prix = Integer.valueOf(request.getParameter("prix"));		
		//String dateDebut = request.getParameter("date-debut");		
		//String dateFin = request.getParameter("date-fin");	
		String rue = request.getParameter("rue");	
		String codePostal = request.getParameter("code-postal");		
		String ville = request.getParameter("ville");				
		
		int prixVente = 0;
		int idUtilisateur = 1;
		int idCategorie = 2;
		String etat_vente = "EC";
		
		ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebut, dateFin, prix, prixVente, etat_vente, idCategorie, idUtilisateur);
		
		DAOFactory articleDAO = new DAOFactory();
		try {
			articleDAO.getArticlesDAO().insert(article);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		TEST RECUP VALEURS
		System.out.println(article);
		System.out.println(description);
		System.out.println(categorie);
		System.out.println(prix);
		System.out.println(dateDebut);
		System.out.println(dateFin);		
		System.out.println(rue);
		System.out.println(codePostal);
		System.out.println(ville);

		
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
