package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ENIEncheres.bo.Retrait;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.ErrorCodesDAL;
import fr.eni.ENIEncheres.dal.dao.DAORetrait;

public class RetraitJdbcImpl implements DAORetrait {
	
	private final static String INSERER = "INSERT INTO RETRAITS(no_article, rue,code_postal,ville,) VALUES (?, ?, ?, ?);";
	private final static String SELECT_BY_ID = "SELECT rue, code_postal, ville FROM RETRAITS WHERE no_article = ?;";
	private final static String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
	
	//Ajouter un nouveau retrait
	@Override
	public void ajouterRetrait(Retrait retrait) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(INSERER);
			stmt.setInt(1, retrait.getIdArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setInt(3, retrait.getCodePostal());
			stmt.setString(4, retrait.getVille());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
			throw dalException;
		}
		
	}


	//Sélectionne un retrait par l'id d'article
	@Override
	public Retrait selectRetraitParIdArticle(int idArticle) throws DALException {
		Retrait retrait = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_ID);
			rqt.setInt(1, idArticle);
			ResultSet rs = rqt.executeQuery();
			
			
			while (rs.next()) {
							
				retrait = new Retrait(idArticle, rs.getString("rue"), rs.getInt("code_postal"), rs.getString("ville"));
	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrait;
	}
	
	@Override
	public void updateRetrait(Retrait retrait) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE);
			stmt.setString(1, retrait.getRue());
			stmt.setInt(2, retrait.getCodePostal());
			stmt.setString(3, retrait.getVille());
			stmt.setInt(4, retrait.getIdArticle());
			stmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_UPDATE);
			throw dalException;
		}
	}



	
}