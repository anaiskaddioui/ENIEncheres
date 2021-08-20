package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ENIEncheres.bll.UtilisateursManager;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

/**
 * Servlet implementation class ServletVerificationPseudo
 */
@WebServlet("/ServletVerificationPseudo")
public class ServletVerificationPseudo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Utilisateurs u = null;
		
		String pseudo = request.getParameter("pseudo");
		
		UtilisateursManager managerU = new UtilisateursManager();
		try {
			u = managerU.selectionnerUtilisateursParPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(u != null) {
			//Met les données du formulaire en tant qu'attribut de requête afin de les afficher dans le formulaire pour ne pas avoir à retaper toutes les informations
			request.setAttribute("pseudo", request.getParameter("pseudo"));
			request.setAttribute("nom", request.getParameter("nom"));
			request.setAttribute("prenom", request.getParameter("prenom"));
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("telephone", request.getParameter("telephone"));
			request.setAttribute("rue", request.getParameter("rue"));
			request.setAttribute("codePostal", request.getParameter("code_postal"));
			request.setAttribute("ville", request.getParameter("ville"));
			request.setAttribute("password", request.getParameter("password"));
			
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/WEB-INF/jsp/CreationCompte.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/ServletCreationCompte");
			rd.forward(request, response);
		}
	}

}
