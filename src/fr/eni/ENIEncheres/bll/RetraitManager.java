package fr.eni.ENIEncheres.bll;

import fr.eni.ENIEncheres.bo.Retrait;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAO;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;

public class RetraitManager {
	


	

	
	    private static DAO<Retrait> dao;

	    static {
	        dao = DAOFactory.getDAORetrait();
	    }

	    /**
	     * Create part of CRUD
	     * @param retrait The instance to add to the DB
	     * @throws BLLException If there is any format issues with the instance
	     * @throws DALException If there is any issues with the DAL part
	     */
	    public void createRetrait(Retrait retrait) throws DALException, BLLException {
	        BLLException bllException = validateRetrait(retrait);
	        if (bllException.hasErrors()) {
	            throw bllException;
	        } else {
	            dao.insert(retrait);
	        }
	    }

	    public Retrait getRetraitByNoArticle(int noArticle) throws DALException {
	        return dao.selectById(noArticle);
	    }

	    public void updateRetrait(Retrait retrait) throws DALException, BLLException {
	        BLLException bllException = validateRetrait(retrait);
	        if (bllException.hasErrors()) {
	            throw bllException;
	        } else {
	            dao.update(retrait);
	        }
	    }

	    public void deleteRetrait(Retrait retrait) throws DALException {
	        dao.delete(retrait);
	    }

	    /**
	     * Validate the format of different fields from an instance of Utilisateurs before any actions with the DB
	     * Check length of the different fields
	     * @param retraitToValidate The instance to validate
	     * @return An instance of BLLException filled with the error codes that been raised
	     */
	    private BLLException validateRetrait(Retrait retraitToValidate) {
	        BLLException bllException = new BLLException();
	        if (retraitToValidate.getRue().length() > 30) {
	            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_RUE_RETRAIT);
	        }
	        if (retraitToValidate.getCodePostal().length() > 15) {
	            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_CODE_POSTAL_RETRAIT);
	        }
	        if (retraitToValidate.getVille().length() > 30) {
	            bllException.addError(ErrorCodesBLL.ERROR_LENGTH_VILLE_RETRAIT);
	        }
	        return bllException;
	    }
	}


