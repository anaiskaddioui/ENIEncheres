package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.ErrorCodesDAL;
import fr.eni.ENIEncheres.dal.dao.DAOCategorie;

public class CategorieJdbcImpl implements DAOCategorie {
	
	String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
	String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (?)";
	String SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle = ?";
	
	@Override
	public void insert(Categorie categorie) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			
			PreparedStatement stmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, categorie.getLibelle());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				categorie.setIdCategorie(rs.getInt(1));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
			throw dalException;
		}
	}

	@Override
	public Categorie selectByIdCategorie(int idCategorie) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Categorie categorie = null;
		try {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, idCategorie);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
			throw dalException;
		}
		return categorie;
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Categorie> categories = new ArrayList<>();
		try {
			Statement stmt = cnx.createStatement();
			String SELECT_ALL = "SELECT * FROM CATEGORIES";
			stmt.execute(SELECT_ALL);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				categories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
			throw dalException;
		}
		return categories;
	}

	 
	public boolean checkForUniqueCategorieLibelle(String libelleToCheck) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean isUnique = true;
		try {
			String CHECK_IF_LIBELLE_IS_UNIQUE = "SELECT * FROM CATEGORIES WHERE libelle LIKE ?";
			PreparedStatement stmt = cnx.prepareStatement(CHECK_IF_LIBELLE_IS_UNIQUE);
			stmt.setString(1, libelleToCheck);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				isUnique = false;
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
			throw dalException;
		}
		return isUnique;
	}

	@Override
	public int selectIdByLibelle(String libelle) throws DALException {
		int idCategorie = 0;
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_LIBELLE);
			rqt.setString(1, libelle);
			rqt.execute();
			ResultSet rs = rqt.getResultSet();
			while (rs.next()) {
				idCategorie = rs.getInt("no_categorie");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return idCategorie;
	}

}
