package fr.eni.ENIEncheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Categorie;
import fr.eni.ENIEncheres.bo.Utilisateurs;
import fr.eni.ENIEncheres.dal.DALException;
import fr.eni.ENIEncheres.dal.Outils;
import fr.eni.ENIEncheres.dal.dao.DAOArticleVendu;


public class ArticleJdbcImpl implements DAOArticleVendu{

	private final static String INSERER = "INSERT INTO Article(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie, etat_vente) values (?,?,?,?,?,?,?,?,?);";
	private final static String LISTER = "SELECT no_article,nom_article,description,date_debut_encheres,prix_initial, prix_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur;";
	private final static String SELECTBYID = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ?;";
	private final static String SELECTIDCATEGORIE = "SELECT no_article,nom_article,description,date_fin_encheres,prix_initial, prix_vente, no_categorie, etat_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.no_categorie = ?;";
	private final static String RECHERCHEPARMOTCLE = "SELECT no_article,nom_article,description, date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nomArticle LIKE ?;";
	private final static String RECHERCHEPARFILTRES = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE nomArticle LIKE ?  AND no_categorie = ?;";
	private final static String SELECTBYETAT = "SELECT no_article,nom_article,description,date_debut_encheres, date_fin_encheres,prix_initial, prix_vente, etat_vente FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur WHERE ARTICLES_VENDUS.etat_vente = ?;";

	@Override
	public void insert(ArticleVendu articleVendu) throws DALException {

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
			pstmt.setInt(8, articleVendu.getIdCategorie());
			pstmt.setString(9, articleVendu.getEtatVente());

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
				//articleVendu.setPseudo(rs.getInt("pseudo"));
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


	public ArrayList<ArticleVendu> rechercheParIdCategories(int idCategorie)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTIDCATEGORIE);

			pstmt.setInt(1, idCategorie);
			rs = pstmt.executeQuery();
			Outils o = new Outils();

			while (rs.next()) {

				ArticleVendu articleVendu = new ArticleVendu();

				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(o.dateSQLEnJavaUtil(rs.getDate("date_fin_encheres")));
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				//articleVendu.setPseudo(rs.getInt("pseudo"));
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
				//articleVendu.setPseudo(rs.getInt("pseudo"));
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
				//articleVendu.setPseudo(rs.getInt("pseudo"));
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

	
	
	
	
	
	
	
	public List<ArticleVendu> selectParEtat(String etat)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTBYETAT);
			//On donne l'id catégorie au 1er argument de la requete
			pstmt.setString(1, etat);
			ResultSet rs = pstmt.executeQuery();
			Outils o = new Outils();

			ArticleVendu articleVendu = null;
			
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
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

	
	public List <ArticleVendu> selectAllArticles() {

		Statement stmtConsultation = null;
		List <ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();

		try {
			Connection cnx = ConnectionProvider.getConnection();
			stmtConsultation = cnx.createStatement();
			ResultSet rsConsultation = stmtConsultation.executeQuery(LISTER);
			
			ArticleVendu article = null;
			
	
			
			while(rsConsultation.next()) {
			
					article=new ArticleVendu();
					
					article.setNomArticle(rsConsultation.getString("nom_article"));
					article.setIdArticle(rsConsultation.getInt("no_article"));
					article.setNomArticle(rsConsultation.getString("nom_article"));
					article.setDescription(rsConsultation.getString("description"));
					article.setDateFinEncheres(rsConsultation.getDate("date_fin_encheres").toLocalDate());
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					article.setPrixInitial(rsConsultation.getInt("prix_initial"));
					article.setPrixVente(rsConsultation.getInt("prix_vente"));

					listeArticles.add(article);

			}
		} catch (SQLException e) {
			System.out.println("Erreur connexion SQL");
			e.printStackTrace();
		}
		
		return listeArticles;
		
	}
	
	
	
	public List<ArticleVendu> selectParIdCategorie(int idCategorie)
			throws DALException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SELECTIDCATEGORIE);
			//On donne l'id catégorie au 1er argument de la requete
			pstmt.setInt(1, idCategorie);
			ResultSet rs = pstmt.executeQuery();
			Outils o = new Outils();

			ArticleVendu articleVendu = null;
			
			while (rs.next()) {
				articleVendu = new ArticleVendu();
				articleVendu.setIdArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
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

	
	
	
	
	
	
	
	
	
	@Override
	public List<ArticleVendu> filterByCategory(Categorie categorie) throws DALException {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<ArticleVendu> filterByString(String filter) throws DALException {


		return null;
	}

	@Override
	public List<Integer> filterByEtat(String etat) throws DALException {


		return null;
	}

	@Override
	public List<Integer> getArticlesFromASellerAndState(Utilisateurs utilisateur, String state) throws DALException {

		return null;
	}

	@Override
	public void updateCurrentPrice(int noArticle, int newPrice) throws DALException {

		
	}

	
}
	
