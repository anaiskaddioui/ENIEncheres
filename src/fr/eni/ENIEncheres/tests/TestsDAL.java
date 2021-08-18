package fr.eni.ENIEncheres.tests;

import fr.eni.ENIEncheres.bll.UtilisateursManager;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;

public class TestsDAL {
	
	public static void main(String[] args) {
		
		Utilisateurs u1 = new Utilisateurs("monPseudo", "monNom", "monPrenom", "monMail", "02.08.09.07.80", "une rue", "59000", "Lille", "password", 0, false);
		
		UtilisateursManager manager = new UtilisateursManager();
		
		try {
			manager.ajouterUtilisateur(u1);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
