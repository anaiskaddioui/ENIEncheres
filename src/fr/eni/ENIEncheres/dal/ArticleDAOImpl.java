package fr.eni.ENIEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.ENIEncheres.bo.ArticleVendu;

public class ArticleDAOImpl implements DAOArticleVendu<ArticleVendu>{

	private final static String INSERER = "INSERT INTO Article(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie, etat_vente) values (?,?,?,?,?,?,?,?,?);";
	private final static String LISTER = "SELECT no_article,nom_article,description,date_debut_encheres,prix_initial, prix_vente, FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur;";
	private final static String SELECTBYID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_ategorie = ?;";
	private final static String RECHERCHEPARIDCATEGORIE = "SELECT no_article,nom_article,description,date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.idUtilisateur WHERE ARTICLES_VENDUS.no_categorie = ?;";
	private final static String RECHERCHEPARMOTCLE = "SELECT no_article,nom_article,description,date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nomArticle LIKE ?;";
	private final static String RECHERCHEPARFILTRES = "SELECT no_article,nom_article,description,date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nomArticle LIKE ?  AND no_categorie = ?;";

	public  void insert(ArticleVendu articleVendu) throws DALException {

		Connection cnx = null;
		PreparedStatement pstmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			Outils o = new Outils();

			pstmt = cnx.prepareStatement(INSERER);
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, o.dateJavaUtilEnDateSQL(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, o.dateJavaUtilEnDateSQL(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getPrixInitial());
			pstmt.setInt(6, articleVendu.getPrixVente());
			pstmt.setInt(7, articleVendu.getIdUtilsateur());
			pstmt.setString(8, articleVendu.getEtatVente());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Probleme - ajouterEnchere - " + e.getMessage());
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

	public  ArrayList<ArticleVendu> selectAll() throws DALException {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(LISTER);
			Outils o = new Outils();
			ArticleVendu articleVendu;
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.getNomArticle();
				if (rs.wasNull())
					articleVendu.setNomArticle("inconnu");
				else
					articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.getDescription();
				if (rs.wasNull())
					articleVendu.setDescription("inconnu");
				else
					articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(o.dateSQLEnJavaUtil(rs.getDate("date_fin_encheres")));
				articleVendu.getPrixInitial();
				if (rs.wasNull())
					articleVendu.setPrixInitial(0);
				else
					articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.getPrixVente();
				if (rs.wasNull())
					articleVendu.setPrixVente(0);
				else
					articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getInt("pseudo"));
				listeArticle.add(articleVendu);

			}

		} catch (SQLException e) {
			throw new DALException("Probleme - listeCategorie -" + e.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - FermerConnexion - " + e.getMessage());
			}
		}

			return listeArticle;
		}
		

	public static ArticleVendu article(int idCategorie) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVendu articleVendu = null;
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTBYID);
			pstmt.setInt(1, idCategorie);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdCategorie(rs.getInt("no_categorie"));
			}
		} catch (SQLException e) {
			throw new DALException("Probleme - rechercherUtilisateur - " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - FermerConnexion - " + e.getMessage());
			}
		}

		return articleVendu;
	}

	public static ArrayList<ArticleVendu> rechercheParIdCategories(int idCategorie, ArticleVendu articleVendu)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(RECHERCHEPARIDCATEGORIE);
			pstmt.setInt(1, idCategorie);
			rs = pstmt.executeQuery();
			Outils o = new Outils();

			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(o.dateSQLEnJavaUtil(rs.getDate("date_fin_encheres")));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getInt("pseudo"));
				listeArticle.add(articleVendu);
			}

		} catch (SQLException e) {
			throw new DALException("Probleme - listeCategorie -" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - FermerConnexion - " + e.getMessage());
			}
		}

		return listeArticle;
	}

	public  ArrayList<ArticleVendu> selectByMoCle(String nomArticle) throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(RECHERCHEPARMOTCLE);
			pstmt.setString(1, "%" + nomArticle + "%");
			rs = pstmt.executeQuery();
			Outils o = new Outils();
			ArticleVendu articleVendu = null;
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(o.dateSQLEnJavaUtil(rs.getDate("date_fin_encheres")));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getInt("pseudo"));
				listeArticle.add(articleVendu);
			}

		} catch (SQLException e) {
			throw new DALException("Probleme - listeCategorie -" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - FermerConnexion - " + e.getMessage());
			}
		}

		return listeArticle;

	}

	public static ArrayList<ArticleVendu> rechercheParDeuxFiltres(String nomArticle, int idCategorie)throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(RECHERCHEPARFILTRES);
			pstmt.setString(1, "%" + nomArticle + "%");
			pstmt.setInt(2, idCategorie);
			rs = pstmt.executeQuery();
			Outils o = new Outils();
			ArticleVendu articleVendu;
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(o.dateSQLEnJavaUtil(rs.getDate("date_fin_encheres")));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				articleVendu.setPseudo(rs.getInt("pseudo"));
				listeArticle.add(articleVendu);
			}

		} catch (SQLException e) {
			throw new DALException("Probleme - listeCategorie -" + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (cnx != null)
					cnx.close();
			} catch (SQLException e) {
				throw new DALException("Probleme - FermerConnexion - " + e.getMessage());
			}
		}

			return listeArticle;
	}
		
}
	
