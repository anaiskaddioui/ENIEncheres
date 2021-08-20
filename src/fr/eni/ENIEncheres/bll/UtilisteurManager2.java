package fr.eni.ENIEncheres.bll;

import java.util.List;
import java.util.regex.Pattern;

import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOFactory;

public class UtilisteurManager2 {

	private static fr.eni.ENIEncheres.dal.dao.DAOUtilisateur dao;

	static {
		dao = DAOFactory.getDAOUtilisateur();
	}

	
	public void createUtilisateur(Utilisateurs utilisateur) throws BLLException, DALException {
		BLLException bllException = validateUtilisateur(utilisateur);
		if (!dao.checkForUniquePseudoAndMail(utilisateur.getPseudo(), utilisateur.getEmail())) {
			bllException.addError(ErrorCodesBLL.ERROR_PSEUDO_OR_MAIL_ALREADY_TAKEN);
		}
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			dao.insert(utilisateur);
		}
	}

	
	public Utilisateurs getUtilisateurById(int id) throws DALException {
		return dao.selectById(id);
	}

	
	public Utilisateurs getUtilisateurByPseudo(String pseudo) throws DALException {
		return dao.selectByPseudo(pseudo);
	}

	
	public List<Utilisateurs> getAllUtilisateurs() throws DALException {
		return dao.selectAll();
	}

	
	public void updateUtilisateur(Utilisateurs utilisateur) throws BLLException, DALException {
		BLLException bllException = validateUtilisateur(utilisateur);
		if (!dao.checkForUniquePseudoAndMail(utilisateur.getPseudo(), utilisateur.getEmail())) {
			bllException.addError(ErrorCodesBLL.ERROR_PSEUDO_OR_MAIL_ALREADY_TAKEN);
		}
		if (bllException.hasErrors()) {
			throw bllException;
		} else {
			dao.update(utilisateur);
		}
	}

	
	public void deleteUtilisateur(Utilisateurs utilisateur) throws DALException {
		dao.delete(utilisateur);
	}

	
	private BLLException validateUtilisateur(Utilisateurs utilisateur) {
		String pseudoValidationRegEx = "[A-Za-z0-9]+";
		
		String emailValidationRegEx = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
		
		String telephoneNumberValidationRegEx = "^0[1-9][0-9]{8}$";
		BLLException bllException = new BLLException();

		if (utilisateur.getPseudo().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_PSEUDO_UTILISATEUR);
		}
		if (!Pattern.matches(pseudoValidationRegEx, utilisateur.getPseudo())) {
			bllException.addError(ErrorCodesBLL.ERROR_PSEUDO_NOT_ALPHANUMERIC);
		}
		if (utilisateur.getNom().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_NOM_UTILISATEUR);
		}
		if (utilisateur.getPrenom().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_PRENOM_UTILISATEUR);
		}
		if (utilisateur.getEmail().length() > 40) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_EMAIL_UTILISATEUR);
		}
		if (!Pattern.matches(emailValidationRegEx, utilisateur.getEmail())) {
			bllException.addError(ErrorCodesBLL.ERROR_FORMAT_EMAIL_UTILISATEUR);
		}
		if (utilisateur.getTel().length() > 15) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_TELEPHONE_UTILISATEUR);
		}
		if (!Pattern.matches(telephoneNumberValidationRegEx, utilisateur.getTel())) {
			bllException.addError(ErrorCodesBLL.ERROR_FORMAT_TELEPHONE_UTILISATEUR);
		}
		if (utilisateur.getRue().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_RUE_UTILISATEUR);
		}
		if (utilisateur.getCoPostal().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_CODE_POSTAL_UTILISATEUR);
		}
		if (utilisateur.getVille().length() > 30) {
			bllException.addError(ErrorCodesBLL.ERROR_LENGTH_VILLE_UTILISATEUR);
		}

		return bllException;
	}
}
