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
@WebServlet("/ServletConsultationCompteBouton")
public class ServletConsultationCompteBouton extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère l'identifiant de l'utilisateur stocké dans la session
		HttpSession session = request.getSession();
		int idUtilisateur = 6;
				//(int) session.getAttribute("idUser");
		
		//On va chercher cet utilisateur dans la base de données et stocker ses valeurs en attribut de requête
		Utilisateurs user = null;
		try {
			user = new UtilisateursManager().selectUserById(idUtilisateur);
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
		rd = request.getRequestDispatcher("/WEB-INF/jsp/ConsultationCompteBouton.jsp");
		rd.forward(request, response);
	}

	/**
	 * On arrive dans cette section en cliquant sur le bouton "Supprimer le profil"
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère l'identifiant de l'utilisateur stocké dans la session
		HttpSession session = request.getSession();
		int idUtilisateur = 6;
				//(int) session.getAttribute("idUser");
		
		//On supprime l'utilisateur
		UtilisateursManager managerU = new UtilisateursManager();
		try {
			managerU.supprimerUtilisateur(idUtilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//On le déconnecte 
		//!!!!!!!!!!!!!!!!!!!!!RESTE A FAIRE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		//On le renvoie vers la page d'accueil
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/ServletAccueil");
		rd.forward(request, response);
		
	}

	
	
}
