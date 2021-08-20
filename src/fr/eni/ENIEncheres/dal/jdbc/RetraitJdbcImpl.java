package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fr.eni.ENIEncheres.bo.Retrait;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.ErrorCodesDAL;

public class RetraitJdbcImpl implements List<Retrait> {
	/**
	 * Insert an instance into the DB and fill this instance with the id generated
	 * by mssql server
	 * 
	 * @param retrait Retrait The instance to insert into the DB
	 * @throws DALException if the SQL INSERT request is wrong
	 */
	public void insert(Retrait retrait) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			String INSERT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = cnx.prepareStatement(INSERT);
			stmt.setInt(1, retrait.getNoArticle());
			stmt.setString(2, retrait.getRue());
			stmt.setString(3, retrait.getCodePostal());
			stmt.setString(4, retrait.getVille());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_INSERT);
			throw dalException;
		}
	}

	/**
	 * Extract data from the DB by id
	 * 
	 * @param noArticle int The primary key of the Retrait to extract from the DB
	 * @return retrait An instance of the retrait
	 * @throws DALException if the SQL INSERT request is wrong
	 */
	public Retrait selectById(int noArticle) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Retrait retrait = null;
		String SELECT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article = ?";
		try {
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, noArticle);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				retrait = hydrateRetrait(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
			throw dalException;
		}
		return retrait;
	}

	public List<Retrait> selectAll() throws DALException {
		return null;
	}

	public void update(Retrait retrait) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?";
		try {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_RETRAIT);
			stmt.setString(1, retrait.getRue());
			stmt.setString(2, retrait.getCodePostal());
			stmt.setString(3, retrait.getVille());
			stmt.setInt(4, retrait.getNoArticle());
			stmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_UPDATE);
			throw dalException;
		}
	}

	public void delete(Retrait retrait) throws DALException {
		Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String DELETE = "DELETE FROM RETRAITS WHERE no_article = ? ";
		try {
			PreparedStatement stmt = cnx.prepareStatement(DELETE);
			stmt.setInt(1, retrait.getNoArticle());
			stmt.executeUpdate();
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
			DALException dalException = new DALException();
			dalException.addError(ErrorCodesDAL.ERROR_SQL_DELETE);
			throw dalException;
		}
	}

	
	private Retrait hydrateRetrait(ResultSet rs) throws SQLException {
		return new Retrait(rs.getInt("no_article"), rs.getString("rue"), rs.getString("code_postal"),
				rs.getString("ville"));
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<Retrait> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(Retrait e) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Retrait> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends Retrait> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public Retrait get(int index) {
		return null;
	}

	@Override
	public Retrait set(int index, Retrait element) {
		return null;
	}

	@Override
	public void add(int index, Retrait element) {
		
	}

	@Override
	public Retrait remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<Retrait> listIterator() {
		return null;
	}

	@Override
	public ListIterator<Retrait> listIterator(int index) {
		return null;
	}

	@Override
	public List<Retrait> subList(int fromIndex, int toIndex) {
		return null;
	}
}