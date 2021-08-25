package fr.eni.ENIEncheres.bll;

import fr.eni.ENIEncheres.bo.Retrait;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;
import fr.eni.ENIEncheres.dal.dao.DAORetrait;

public class RetraitManager {
	
	    private DAORetrait retraitDAO;

	    //Constructeur
	    public RetraitManager() {
	    	this.retraitDAO = DAOFactory.getDAORetrait();
	    }

	    public void insertRetrait(Retrait retrait) throws DALException, BLLException {
	      
	    	this.retraitDAO.ajouterRetrait(retrait);
	    }

	    public Retrait getRetraitByNoArticle(int noArticle) throws DALException {
	    	return retraitDAO.selectRetraitParIdArticle(noArticle);
	    }
	    
	    public void updateRetrait(Retrait retrait) throws DALException {
	    	this.retraitDAO.updateRetrait(retrait);
	    }

	}


