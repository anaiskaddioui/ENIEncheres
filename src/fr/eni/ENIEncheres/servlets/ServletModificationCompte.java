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
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletModificationCompte")
public class ServletModificationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * On arrive depuis la JSP ConsultationCompteBouton.jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère le pseudo de l'utilisateur stocké dans la session
				HttpSession session = request.getSession();
				String pseudoUtilisateur = (String) session.getAttribute("pseudoUtilisateur");
				
				//On va chercher cet utilisateur dans la base de données et stocker ses valeurs en attribut de requête
				Utilisateurs user = null;
				try {
					user = new UtilisateursManager().selectUserByPseudo(pseudoUtilisateur);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(user != null) {
				request.setAttribute("pseudo", user.getPseudo());
				request.setAttribute("nom", user.getNom());
				request.setAttribute("prenom", user.getPrenom());
				request.setAttribute("email", user.getEmail());
				request.setAttribute("telephone", user.getTel());
				request.setAttribute("rue", user.getRue());
				request.setAttribute("codePostal", user.getCoPostal());
				request.setAttribute("ville", user.getVille());
				request.setAttribute("password", user.getPassword());
				request.setAttribute("credit", user.getCredit());
				}
				
				//On renvoie à la jsp d'affichage du profil qui se mettra à jour avec les informations attribuées		
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/WEB-INF/jsp/ModificationCompte.jsp");
				rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Ici, on va modifier les données d'un utilisateur
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//On récupère les changements dans le formulaire
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");		
		String email = request.getParameter("email");		
		String telephone = request.getParameter("telephone");		
		String rue = request.getParameter("rue");		
		String codePostal = request.getParameter("code_postal");		
		String ville = request.getParameter("ville");
		String passwordNouveau = null;
		if(request.getParameter("password-nouveau") != null) {
			passwordNouveau = request.getParameter("password-nouveau");
		}
		
		
		//On récupère l'utilisateur associé à la session 
		HttpSession session = request.getSession();
		String pseudoUtilisateur = (String) session.getAttribute("pseudoUtilisateur");
		Utilisateurs oldUser = null;
		UtilisateursManager managerU = new UtilisateursManager();
		try {
			oldUser = managerU.selectUserByPseudo(pseudoUtilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//On récupère l'ID de l'utilisateur, son crédit ainsi que savoir si c'est un administrateur ou pas
		int idUtilisateur = oldUser.getIdUtilisateur();
		int credit = oldUser.getCredit();
		boolean admin = oldUser.isAdministrateur();
		
		//On crée un nouvel utilisateur avec les nouvelles valeurs
		Utilisateurs newUser = null;
		if(passwordNouveau == null) {
			newUser = new Utilisateurs(idUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, admin);
		} else {
			newUser = new Utilisateurs(idUtilisateur, pseudo, passwordNouveau, nom, prenom, email, telephone, rue, codePostal, ville, credit, admin);
		}
		
		//On modifie l'ancien utilisateur avec les nouvelles valeurs
		try {
			managerU.modifierUtilisateur(newUser);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//On change le pseudo de l'utisateur en attribut de session
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!A FAIRE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		
//		TEST RECUP VALEURS
		System.out.println(pseudo);
		System.out.println(nom);
		System.out.println(prenom);
		System.out.println(email);
		System.out.println(telephone);
		System.out.println(rue);
		System.out.println(codePostal);
		System.out.println(ville);
		
		//On renvoie vers la visualisation du profil
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/ServletConsultationCompteBouton");
				rd.forward(request, response);
		
		
	}

}
