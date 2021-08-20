package fr.eni.ENIEncheres.bll;

import java.util.List;

import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;
import fr.eni.ENIEncheres.dal.dao.DAOUtilisateur;


public class UtilisateursManager {

	private DAOUtilisateur utilisateurDAO;
	
	
	//Constructeur : 
	public UtilisateursManager() {
		this.utilisateurDAO=DAOFactory.getUtilisateursDAO();
	}
	
	
	//Méthodes select : 
	public List<Utilisateurs> selectionnerTousLesUtilisateurs() throws DALException {
		
		return this.utilisateurDAO.selectAll();
	}
	
	public Utilisateurs selectionnerUtilisateursParId(int id) throws DALException {
		
		return this.utilisateurDAO.selectById(id);
	}
	
	public Utilisateurs selectionnerUtilisateursParNom(String name) throws DALException {
		
		return this.utilisateurDAO.selectByName(name);
	}
	
	public Utilisateurs selectionnerUtilisateursParPseudo(String pseudo) throws DALException {
		
		return (Utilisateurs) this.utilisateurDAO.selectByPseudo(pseudo);
	}
	
	public Utilisateurs selectionnerUtilisateursParMotCle(String motCle) throws DALException {
		
		return (Utilisateurs) this.utilisateurDAO.selectByMotCle(motCle);
	}
	
	
	public void ajouterUtilisateur(Utilisateurs u) throws DALException {

		this.utilisateurDAO.insert(u);
	}
	
	public void supprimerUtilisateur(String pseudo) throws DALException {
		this.utilisateurDAO.delete(pseudo);
	}
	
}
