package fr.eni.ENIEncheres.bll;

import java.util.List;

import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;
import fr.eni.ENIEncheres.dal.dao.DAOUtilisateur;


public class UtilisateursManager {

	private DAOUtilisateur utilisateurDAO;
	
	
	
	public UtilisateursManager() {
		this.utilisateurDAO=DAOFactory.getDAOUtilisateur();
	}
	
	
	
	public List<Utilisateurs> selectAllUsers() throws DALException {
		
		return this.utilisateurDAO.selectAll();
	}
	
	public Utilisateurs selectUserById(int id) throws DALException {
		
		return this.utilisateurDAO.selectById(id);
	}
	
	public Utilisateurs selectUserByName(String name) throws DALException {
		
		return this.utilisateurDAO.selectByName(name);
	}
	
	public Utilisateurs selectUserByPseudo(String pseudo) throws DALException {
		
		return this.utilisateurDAO.selectByPseudo(pseudo);
	}

	public Utilisateurs selectUserByEmail(String email) throws DALException {
		
		return (Utilisateurs) this.utilisateurDAO.selectByEmail(email);
	}
	
	public Utilisateurs selectUserByKey(String motCle) throws DALException {
		
		return (Utilisateurs) this.utilisateurDAO.selectByMotCle(motCle);
	}
	
	

	public void ajouterUtilisateur(Utilisateurs u) throws DALException {

		this.utilisateurDAO.insert(u);

	}
	
	public void supprimerUtilisateur(int idUtilisateur) throws DALException {
		this.utilisateurDAO.delete(idUtilisateur);
	}
	
	public void modifierUtilisateurSansMDP(Utilisateurs u) throws DALException {
		this.utilisateurDAO.updateWithoutPassword(u);
	}

	public void modifierUtilisateurAvecMDP(Utilisateurs u) throws DALException {
		this.utilisateurDAO.updateWithPassword(u);
	}
	
	public void misAJourCredit(int idUtilisateur, int credit) throws DALException{
		this.utilisateurDAO.updateCredit(idUtilisateur, credit);
	}
	
}
