package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.ErrorCodesDAL;
import fr.eni.ENIEncheres.dal.dao.DAOEnchere;

public class EnchereJdbcImpl implements DAOEnchere {

	String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
	String SELECT_ARTICLE = "SELECT * FROM encheres WHERE no_article = ? ORDER BY montant_enchere DESC";
	String DELETE = "DELETE FROM ENCHERES WHERE no_utilisateur = ?";
	
	@Override
	public void insert(Enchere enchere) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			PreparedStatement stmt = cnx.prepareStatement(INSERT);
			stmt.setInt(1, enchere.getIdUtilisateur());
			stmt.setInt(2, enchere.getIdArticle());
			stmt.setTimestamp(3, enchere.getDateEnchere());
			stmt.setInt(4, enchere.getMontantEnchere());
			stmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
			throw dalException;
		}
	}

	@Override
	public Enchere selectByIdArticle(int idArticle) throws DALException {
		
		Enchere enchere = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_ARTICLE);
			rqt.setInt(1, idArticle);
			ResultSet rs = rqt.executeQuery();
			
			while (rs.next()) {
							
				enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere"), rs.getInt("montant_enchere"));
	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enchere;
	}

	@Override
	public void delete(int idUtilisateur) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_ARTICLE);
			rqt.setInt(1, idUtilisateur);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

		
}



