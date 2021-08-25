package fr.eni.ENIEncheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		User id
		String identifiant = request.getParameter("utilisateur_identifiant");
//		User password
		String password = request.getParameter("utilisateur_motDePasse");
//		Checkbox se souvenir de moi
		String connexionAuto = request.getParameter("connexion-auto");		
		
//		TEST RECUP VALEURS
		System.out.println(identifiant);
		System.out.println(password);
		System.out.println(connexionAuto);
		
		//Instanciation de l'user connecté + stockage booléen isConnected + stockage idUser dans un cookie si user Ok : 
				UtilisateursManager userConnect = new UtilisateursManager();
				Utilisateurs userConnected;
				int idUser;
				
				HttpSession session = request.getSession(true);
				//Si "se souvenir de moi" a été sélectionné : 
				Cookie cookies[] = request.getCookies();
				
				boolean isConnected = false;
				
				System.out.println("Recherche cookie idUser : ");
				
				try { //Les vérifications BDD ont été faites par Benjamin, donc je n'en mets pas ici :
					userConnected = userConnect.selectUserByPseudo(identifiant);
					idUser = userConnected.getIdUtilisateur();
					
	
					if (userConnected != null) {
						
						System.out.println(userConnected.toString());
						
						session.setAttribute("isConnected", true);
						session.setAttribute("idUser", idUser); 
						session.setAttribute("pseudo", identifiant);
						
						isConnected = true;
						
						if (connexionAuto == "on") {
						
							for(Cookie unCookie : cookies) {
	
								if (unCookie.equals("idUser")) { //Si déjà cookie, la valeur de l'idUser est réattribuée
									
									request.setAttribute("idUser", unCookie.getValue());
									session.setAttribute("idUser", unCookie.getValue());
									//EtatAppli.setUtilisateur(userConnected);
								} else {
									
									//Stockage durable de l'identifiant côté client (1an) :
									Cookie cookie = new Cookie("idUser", Integer.toString(idUser));
									cookie.setMaxAge(60 * 60 * 24 * 365);
									response.addCookie(cookie);
									
									session.setAttribute("idUser", unCookie.getValue());
								}
							}
							request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp").forward(request, response);
						}
					 }else 
						//impossible d'attribuer ici, mais peu gênant : l'attribut de session "idUser" sera inexistant si non-connecté			
						session.setAttribute("isConnected", false);
						System.out.println(session.getAttribute("isConnected"));
						//Créer une page de transition ? (ex : "Vous n'êtes pas enregistré : Voulez-vous créer un compte ?)
						
						request.getRequestDispatcher("/WEB-INF/jsp/RefusConnexion.jsp");
						((RequestDispatcher) request).forward(request, response);

					
				} catch (DALException e) {
					e.printStackTrace();
				}
		
				//Commentaire pour pusher
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
