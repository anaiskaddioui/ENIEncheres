package fr.eni.ENIEncheres.bll;

import java.util.List;

import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.DAOFactory;
import fr.eni.ENIEncheres.dal.DAOUtilisateur;


public class UtilisateursManager {

	private DAOUtilisateur utilisateurDAO;
	private boolean isError = false;
	
	
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
		
		this.validerPseudo(u.getPseudo());
		
		if (!this.isError) {
			this.utilisateurDAO.insert(u);
		} 
	}
	
	//Vérifications avant méthodes UPDATE ou DELETE : 
	private boolean validerPseudo(String pseudo) throws DALException {
		
		List<Utilisateurs> utilisateurs = this.utilisateurDAO.selectAll();
		
		for (Utilisateurs u : utilisateurs) {
			if(pseudo == u.getPseudo());
			this.isError = true;
		}
		
		if (pseudo.length() > 30) {
			this.isError = true;
		}
		
		return this.isError;
	}
}
