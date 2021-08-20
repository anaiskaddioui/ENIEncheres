package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ENIEncheres.bll.UtilisateursManager;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

/**
 * Servlet implementation class ServletVerificationPseudo
 */
@WebServlet("/ServletVerification")
public class ServletVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//On va chercher si un utilisateur dans la BDD possède le même pseudo
		Utilisateurs uPseudo = null;
		String pseudo = request.getParameter("pseudo");
		
		UtilisateursManager managerU = new UtilisateursManager();
		try {
			uPseudo = managerU.selectUserByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//On va chercher si un utilisateur dans la BDD possède le même email
		Utilisateurs uEmail = null;
		String email = request.getParameter("email");
		HttpSession session = request.getSession();
		String pseudoSession = (String) session.getAttribute("pseudoUtilisateur");
		try {
			uEmail = managerU.selectUserByEmail(email);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Si l'email existe on va comparer s'il appartient à l'utilisateur de la session ou s'il appartient à un autre utilisateur
		if(uEmail != null) {
			boolean mailUserSession = uEmail.getPseudo().equals(pseudoSession);
			if(mailUserSession) {
				uEmail = null;
			}
		}
		
		//Si seulement le pseudo existe
		if(uPseudo != null & uEmail == null) {
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
			
			//Crée un attribut pour indiquer que le pseudo existe
			request.setAttribute("pseudoValidation", "Ce pseudo existe déjà");
			
			//Renvoie vers le formulaire correspondant en fonction d'où vient la requête (création ou modification de compte) avec les champs préremplis
			if(request.getParameter("hiddenPath").equals("creation")) {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/WEB-INF/jsp/CreationCompte.jsp");
			rd.forward(request, response);
			} else if(request.getParameter("hiddenPath").equals("modification")) {
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/WEB-INF/jsp/ModificationCompte.jsp");
				rd.forward(request, response);
				}
			
			//Si seulement l'email existe
		} else if(uPseudo == null & uEmail != null) {
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
			
			//Crée un attribut pour indiquer que le pseudo existe
			request.setAttribute("emailValidation", "Cet email est déjà associé à un compte");
			
			//Renvoie vers le formulaire correspondant en fonction d'où vient la requête (création ou modification de compte) avec les champs préremplis
			if(request.getParameter("hiddenPath").equals("creation")) {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/WEB-INF/jsp/CreationCompte.jsp");
			rd.forward(request, response);
			} else if(request.getParameter("hiddenPath").equals("modification")) {
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/WEB-INF/jsp/ModificationCompte.jsp");
				rd.forward(request, response);
				}
			
			//Si l'email et le pseudo existe
		} else if(uPseudo != null & uEmail != null) {
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
			
			//Crée un attribut pour indiquer que le pseudo existe
			request.setAttribute("pseudoValidation", "Ce pseudo existe déjà");
			request.setAttribute("emailValidation", "Cet email est déjà associé à un compte");
			
			//Renvoie vers le formulaire correspondant en fonction d'où vient la requête (création ou modification de compte) avec les champs préremplis
			if(request.getParameter("hiddenPath").equals("creation")) {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/WEB-INF/jsp/CreationCompte.jsp");
			rd.forward(request, response);
			} else if(request.getParameter("hiddenPath").equals("modification")) {
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/WEB-INF/jsp/ModificationCompte.jsp");
				rd.forward(request, response);
				}
			
			//Si aucun n'existe
		} else	{
			if(request.getParameter("hiddenPath").equals("creation")) {
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("/ServletCreationCompte");
			rd.forward(request, response);
			} else if(request.getParameter("hiddenPath").equals("modification")) {
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/ServletConsultationCompteBouton");
				rd.forward(request, response);
				}
		}
	}

}
