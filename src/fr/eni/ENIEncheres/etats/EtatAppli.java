package fr.eni.ENIEncheres.etats;

import java.util.List;

import javax.servlet.http.HttpSession;

import fr.eni.ENIEncheres.bll.ArticlesManager;
import fr.eni.ENIEncheres.bll.UtilisateursManager;
import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;


public class EtatAppli {

	private static Utilisateurs utilisateur;
	private static ArticleVendu article;
	private static List<ArticleVendu> listeArticles;
	private static Categorie categorie;	
	private static boolean isConnected;
	private static String idUser;
	private static HttpSession session;
	private static UtilisateursManager uManager;
	private static ArticlesManager artManager;
	
	
	public static Utilisateurs getUtilisateur() throws DALException {
		
		EtatAppli.setUtilisateur();
		
		return utilisateur;
		
	}
	public static void setUtilisateur() throws DALException {		
		
		if (session.getAttribute("idUser") != null) {
			int id = Integer.valueOf((String) session.getAttribute("idUser"));
			EtatAppli.utilisateur = uManager.selectUserById(id);
		}
	}
	
	public static ArticleVendu getArticle() {
		
			return article;		
	}
	public static void setListeArticles() throws DALException {
		
		if (session.getAttribute("idUser") != null) {
			int id = Integer.valueOf((String) session.getAttribute("idUser"));
			EtatAppli.listeArticles.addAll(artManager.selectionnerParUserId(id));
		}
	}
	
	public static List<ArticleVendu> getListeArticles () throws DALException {
		
		EtatAppli.setListeArticles();
		return listeArticles;		
	}
	
	public static Categorie getCategorie() {
		return categorie;
	}
	public static void setCategorie(Categorie c) {
		EtatAppli.categorie = c;
	}
	
	public static boolean isConnected() {
		EtatAppli.setIsConnected();
		return isConnected;
	}
	public static void setIsConnected() {
		
		if (session.getAttribute("isConnected") != null) {
			EtatAppli.isConnected = (boolean) session.getAttribute("isConnected");
		}
		else {
			EtatAppli.isConnected = false;
		}
	}
	
	public static String getIdUser() {
		return idUser;
	}
	public static void setIdUser() {
		if (session.getAttribute("idUser") != null) {
			EtatAppli.idUser = (String) session.getAttribute("idUser");
		}
	}
	
}
