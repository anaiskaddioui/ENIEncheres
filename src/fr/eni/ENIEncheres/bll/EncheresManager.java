package fr.eni.ENIEncheres.bll;

import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOEnchere;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;

public class EncheresManager {

	private DAOEnchere enchereDAO;
	
	public EncheresManager() {
		this.enchereDAO = DAOFactory.getDAOEnchere();
	}
	
	public void ajouterEnchere(Enchere enchere) throws DALException {
			this.enchereDAO.insert(enchere);
	}
	
}
