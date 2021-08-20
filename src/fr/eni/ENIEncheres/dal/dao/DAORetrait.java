package fr.eni.ENIEncheres.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.jdbc.ConnectionProvider;

public class DAORetrait {
	
	private final static String INSERER = "INSERT INTO RETRAITS(rue,code_postal,ville,) VALUES (?,?,?);";
	private final static String SELECTBYID = "SELECT rue, code_postal, ville FROM RETRAIT WHERE no_categorie = ?;";
	
	public static void ajouter(DAORetrait retrait) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(INSERER);
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCode_postal());
			pstmt.setString(3, retrait.getVille());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Probleme - ajouterRetrait - " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - fermerConnexion - " + e.getMessage());
			}
		}
	}
	private String getVille() {
		return null;
	}
	private String getCode_postal() {
		return null;
	}
	private String getRue() {
		return null;
	}
	
	public static DAORetrait selectParNoArticle(int noArticle) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;	
		ResultSet rs=null;
		DAORetrait lieuRetrait = null;
		try{
			cnx = ConnectionProvider.getConnection();
			pstmt=cnx.prepareStatement(SELECTBYID);
			pstmt.setInt(1, noArticle);
			rs=pstmt.executeQuery();
			if (rs.next()){
				lieuRetrait = new DAORetrait();
				lieuRetrait.setRue(rs.getString("rue"));
				lieuRetrait.setCode_postal(rs.getString("code_postal"));
				lieuRetrait.setVille(rs.getString("ville"));
			}
		}catch (SQLException e){
			throw new DALException ("Probleme - rechercherUtilisateur - " + e.getMessage());
		}finally{
			try{
				if (pstmt!=null) pstmt.close();
				if (cnx!=null) cnx.close();
			} catch (SQLException e){
				throw new DALException ("Probleme - FermerConnexion - " + e.getMessage());
			}
		}
		return lieuRetrait;
	}
	private void setVille(String string) {
		
	}
	private void setCode_postal(String string) {
		
	}
	private void setRue(String string) {
		
	}
}


