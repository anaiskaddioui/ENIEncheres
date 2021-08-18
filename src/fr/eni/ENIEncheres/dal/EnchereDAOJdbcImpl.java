package fr.eni.ENIEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.eni.ENIEncheres.bo.ArticleVendu;
import fr.eni.ENIEncheres.bo.Enchere;
import fr.eni.ENIEncheres.bo.Utilisateurs;

public class EnchereDAOJdbcImpl implements DAOEnchere {
    @Override
    public void insert(Enchere enchere) throws DALException {
        Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        try {
            String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = cnx.prepareStatement(INSERT);
            stmt.setInt(1, enchere.getIdUtilisateur());
            stmt.setInt(2, enchere.getIdArticle());
            stmt.setObject(3, new Timestamp(enchere.getDateEnchere().getTime()));
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
    public List<Integer> getNoArticlesByUtilisateurAndEtat(Utilisateurs utilisateur, String state) throws DALException {
        Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        List <Integer> noArticlesMatched = new ArrayList<>();

        String SELECT_BY_UTILISATEUR_AND_ETAT = "SELECT no_article " +
                "FROM ENCHERES  " +
                "INNER JOIN ARTICLES_VENDUS ON no_article = no_article " +
                "WHERE etat_vente = ? AND no_utilisateur = ?";
        try {
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR_AND_ETAT);
            stmt.setString(1, state);
            stmt.setInt(2, utilisateur.getIdUtilisateur());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                noArticlesMatched.add(rs.getInt("no_article"));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }

        return noArticlesMatched;
    }

    @Override
    public List<Integer> getNoArticlesWonByUtilisateur(Utilisateurs utilisateur) throws DALException {
        Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        List<Integer> articlesWonByUtilisateur = new ArrayList<>();
        String SELECT_ARTICLES_WON_BY_USER =
                "SELECT no_article FROM ( " +
                "SELECT no_article, date_enchere, no_utilisateur, " +
                        "row_number() OVER (" +
                        "PARTITION BY no_article " +
                        "ORDER BY datediff(date_enchere, date_fin_encheres)) Ranking " +
                "FROM ENCHERES " +
                "         INNER JOIN ARTICLES_VENDUS  ON no_article = no_article " +
                "WHERE etat_vente = no_utilisateur = ?) t " +
                "WHERE Ranking = 1";
        try {
            PreparedStatement stmt = cnx.prepareStatement(SELECT_ARTICLES_WON_BY_USER);
            stmt.setInt(1, utilisateur.getIdUtilisateur());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                articlesWonByUtilisateur.add(rs.getInt("no_article"));
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return articlesWonByUtilisateur;
    }

    
    public HashMap<Integer, Integer> getAmountAndPseudoOfBestOffer(ArticleVendu articleVendu) throws DALException {
        Connection cnx = null;
		try {
			cnx = ConnectionProvider.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        HashMap<Integer, Integer> result = new HashMap<>();
        try {
            String GET_UTILISATEUR_AND_BEST_AUCTIONS = "SELECT no_utilisateur, " +
                    "       montant_enchere " +
                    "       FROM ( " +
                    "    SELECT no_article, date_enchere, no_utilisateur, montant_enchere, " +
                    "            row_number() OVER ( " +
                    "            PARTITION BY no_utilisateur " +
                    "            ORDER BY datediff(MI, date_enchere, date_fin_encheres)) Ranking " +
                    "    FROM ENCHERES " +
                    "    INNER JOIN ARTICLES_VENDUS no_article = no_article" +
                    "    WHERE no_article = ?) t " +
                    "    WHERE Ranking = 1;";
            PreparedStatement stmt = cnx.prepareStatement(GET_UTILISATEUR_AND_BEST_AUCTIONS);
            stmt.setInt(1, articleVendu.getIdArticle());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                result.put(rs.getInt("montant_enchere"), rs.getInt("no_utilisateur"));
            } else {
                result = null;
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.addError(ErrorCodesDAL.ERROR_SQL_SELECT);
            throw dalException;
        }
        return result;
    }

    @Override
    public Enchere selectById(int id) throws DALException {
        return null;
    }

    @Override
    public List<Enchere> selectAll() throws DALException {
        return null;
    }

    @Override
    public void update(Enchere enchere) throws DALException {

    }

    @Override
    public void delete(Enchere enchere) throws DALException {

    }

	@Override
	public List<Integer> getidArticlesByUtilisateurAndEtat(Utilisateurs utilisateur, String state) throws DALException {
		return null;
	}

	@Override
	public List<Integer> getidArticlesWonByUtilisateur(Utilisateurs utilisateur) throws DALException {
		return null;
	}

	
}
