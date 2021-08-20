package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.dao.DAOUtilisateur;

public class UtilisateursJdbcImpl implements DAOUtilisateur {
	

	private static final String SELECT_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur " +
			" FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur " +  
			" FROM UTILISATEURS";
	private static final String UPDATE = "UPDATE UTILISATEURS set pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur=?";
	private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String DELETE = "DELETE FROM UTILISATEURS WHERE pseudo=?";
	private static final String SELECT_BY_NAME = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur " + 
													" FROM UTILISATEURS WHERE nom = ?";
	private static final String SELECT_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur " + 
			" FROM UTILISATEURS WHERE pseudo = ?";
	private static final String SELECT_BY_MOT_CLE = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur " + 
													" FROM UTILISATEURS WHERE nom LIKE ? OR prenom LIKE ? OR pseudo LIKE ? OR ville LIKE ?";

	
	//Méthode d'ajout d'utilisateur héritée de l'interface DAO<T> : 

	public void insert(Utilisateurs utilisateur) throws DALException {
		
		if(utilisateur==null) //Business exception + messages à créer ultérieurement, en cas de mauvaise manip' : 
		{

			/*BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;*/

		}
			
		
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTel());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCoPostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getPassword());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			
			
			pstmt.executeUpdate();
			cnx.commit();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setIdUtilisateur(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		
	}

	//Liste de tous les utilisateurs : 
	@Override
	public List<Utilisateurs> selectAll() throws DALException {
		
		List<Utilisateurs> utilisateurs = new ArrayList<Utilisateurs>();
		
		Utilisateurs utilisateur = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Statement rqt = cnx.createStatement();
			ResultSet rs = rqt.executeQuery(SELECT_ALL);
			
			while (rs.next()) {
							
				utilisateur = new Utilisateurs(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),	
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur"));
	
				utilisateurs.add(utilisateur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}

	//Sélection utilisateurs par ID : 
	@Override
	public Utilisateurs selectById(int id) throws DALException {
		
		Utilisateurs utilisateur = null;
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_ID);
			rqt.setInt(1, id);
			ResultSet rs = rqt.executeQuery();
			
			
			while (rs.next()) {
							
				utilisateur = new Utilisateurs(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),	
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur"));
	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}

	
	@Override
	public Utilisateurs selectByName(String name) throws DALException {
		
		
		Utilisateurs utilisateur = null;

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_NAME);
			rqt.setString(1, name);
			ResultSet rs = rqt.executeQuery();
			
			
			while (rs.next()) {
							
				utilisateur = new Utilisateurs(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),	
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur"));
	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	
	public Utilisateurs selectByPseudo(String pseudo) throws DALException {
	
		
		Utilisateurs utilisateur = null;

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			rqt.setString(1, pseudo);
			ResultSet rs = rqt.executeQuery();
			
			
			while (rs.next()) {
							
				utilisateur = new Utilisateurs(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("mot_de_passe"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),	
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur"));
	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
		
	}

	@Override
	public List<Utilisateurs> selectByMotCle(String motCle) throws DALException {

		List<Utilisateurs> utilisateurs = new ArrayList<Utilisateurs>();
		
		Utilisateurs utilisateur = null;

		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement rqt = cnx.prepareStatement(SELECT_BY_MOT_CLE);
			rqt.setString(1, motCle);
			ResultSet rs = rqt.executeQuery();
			
			
			while (rs.next()) {
							
				utilisateur = new Utilisateurs(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),	
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getBoolean("administrateur"));
	
				utilisateurs.add(utilisateur);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}

	@Override
	public void update(Utilisateurs maj) throws DALException {
		
		PreparedStatement rqt = null;
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{
			cnx.setAutoCommit(false);
			rqt = cnx.prepareStatement(UPDATE);
			
			rqt.setString(1, maj.getPseudo());
			rqt.setString(2, maj.getNom());
			rqt.setString(3, maj.getPrenom());
			rqt.setString(4, maj.getEmail());
			rqt.setString(5, maj.getTel());
			rqt.setString(6, maj.getRue());
			rqt.setString(7, maj.getCoPostal());
			rqt.setString(9, maj.getVille());
			rqt.setString(10, maj.getPassword());
			rqt.setInt(11, maj.getCredit());
			rqt.setBoolean(12, maj.isAdministrateur());
			

			rqt.executeUpdate();
			cnx.commit();
			rqt.close();

		} catch (SQLException e) {
			throw new DALException("Update article failed - " + maj, e);
		} 
	}


	@Override
	public void delete(String pseudo) throws DALException {

		
		PreparedStatement rqt = null;
		
		try (Connection cnx = ConnectionProvider.getConnection())
		{		
			cnx.setAutoCommit(false);
			rqt = cnx.prepareStatement(DELETE);

			rqt.setString(1, pseudo);

			rqt.executeUpdate();
			
			cnx.commit();
			rqt.close();
			
		} catch (SQLException e) {

			throw new DALException("Delete utilisateur failed - pseudo=" + pseudo, e);
		} 
	}

	
	

}
