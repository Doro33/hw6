package ir.maktab_hw6.repository;

import ir.maktab_hw6.config.ApplicationConnection;
import ir.maktab_hw6.entity.Article;

import java.sql.*;

public class ArticleRepository {
    public static void insert(Article article) throws SQLException {
        String sql = """
                INSERT INTO article(title, brief, content,createDate,isPublished,userId)
                VALUES (?,?,?,?,?,?)
                """;
        PreparedStatement ps = ApplicationConnection.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, article.getTitle());
        ps.setString(2, article.getBrief());
        ps.setString(3, article.getContent());
        ps.setDate(4, Date.valueOf(article.getCreateDate()));
        ps.setBoolean(5, article.isPublished());
        ps.setInt(6, article.getUserId());

        ps.execute();
        ResultSet generatedIds = ps.getGeneratedKeys();
        generatedIds.next();
        int id = generatedIds.getInt("id");
        article.setArticleId(id);
        ps.close();
        generatedIds.close();
    }

    public static Article[] showPublishedArticle() throws SQLException {
        String sql = """
                Select * from article
                where isPublished = true
                order by id
                """;
        PreparedStatement ps = ApplicationConnection.getConnection()
                .prepareStatement(sql);
        //showPublishedArticleStatement.execute();
        ResultSet rs = ps.executeQuery();
        Article[] articles = new Article[100];

        int index = 0;
        while (rs.next()) {
            Article article = new Article();
            article.setArticleId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setBrief(rs.getString("brief"));
            article.setContent(rs.getString("content"));
            article.setCreateDate(rs.getDate("createDate").toLocalDate());
            article.setPublished(rs.getBoolean("isPublished"));
            article.setUserId(rs.getInt("userId"));
            articles[index] = article;
            index++;
        }
        ps.close();
        rs.close();
        return articles;
    }

    public static Article[] showUserArticle(int userId) throws SQLException {
        String sql = """
                Select * from article
                where userId=?
                order by id
                """;
        PreparedStatement ps = ApplicationConnection.getConnection()
                .prepareStatement(sql);
        ps.setInt(1, userId);

        //showPublishedArticleStatement.execute();
        ResultSet rs = ps.executeQuery();
        Article[] articles = new Article[100];
        int index = 0;
        while (rs.next()) {
            Article article = new Article();
            article.setArticleId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setBrief(rs.getString("brief"));
            article.setContent(rs.getString("content"));
            article.setCreateDate(rs.getDate("createDate").toLocalDate());
            article.setPublished(rs.getBoolean("isPublished"));
            article.setUserId(userId);
            articles[index] = article;
            index++;
        }
        ps.close();
        rs.close();
        return articles;
    }

    public static void update(int articleId, Article article) throws SQLException {
        String sql = """
                UPDATE article
                SET title=?, brief=?, content=?,createDate=?,isPublished=?--userId=?,--
                WHERE id = ?
                """;
        PreparedStatement ps = ApplicationConnection.getConnection()
                .prepareStatement(sql);
        ps.setString(1, article.getTitle());
        ps.setString(2, article.getBrief());
        ps.setString(3, article.getContent());
        ps.setDate(4, Date.valueOf(article.getCreateDate()));
        ps.setBoolean(5, article.isPublished());
        //ps.setInt(6,article.getUserId());
        ps.setInt(6, articleId);

        ps.execute();
        ps.close();
    }

    public static Article getArticle(int articleId) throws SQLException {
        String sql = """
                Select * from article
                where id=?;
                """;
        PreparedStatement ps = ApplicationConnection.getConnection()
                .prepareStatement(sql);
        ps.setInt(1, articleId);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Article article = new Article();
        article.setTitle(rs.getString("title"));
        article.setBrief(rs.getString("brief"));
        article.setContent(rs.getString("content"));
        article.setCreateDate(rs.getDate("createDate").toLocalDate());
        article.setPublished(rs.getBoolean("isPublished"));
        article.setUserId(rs.getInt("userId"));
        ps.execute();
        ps.close();
        rs.close();
        return article;
    }

    public static void changePublishStatus(int articleId) throws SQLException {
        Article newArticle = getArticle(articleId);
        boolean isPublished = !newArticle.isPublished();
        String sql = """
                UPDATE article
                SET isPublished=?
                WHERE id = ?
                """;
        PreparedStatement ps = ApplicationConnection.getConnection()
                .prepareStatement(sql);

        ps.setBoolean(1, isPublished);
        ps.setInt(2, articleId);
        ps.execute();
        ps.close();
    }

    public static boolean titleContains(String title) throws SQLException {
        String sql = """
                SELECT title from article
                WHERE title=? ;
                """;
        PreparedStatement ps = ApplicationConnection.getConnection().prepareStatement(sql);
        ps.setString(1, title);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
}