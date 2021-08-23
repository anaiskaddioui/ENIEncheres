package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.ErrorCodesDAL;
import fr.eni.ENIEncheres.dal.dao.DAOEnchere;

public class EnchereJdbcImpl implements DAOEnchere {

	String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
	
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
	public Enchere selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Enchere enchere) throws DALException {
		// TODO Auto-generated method stub
		
	}



}
