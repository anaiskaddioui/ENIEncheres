package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//On regarde s'il a assez de crédit
		
		//On crée une nouvelle enchère
		
	}

}
