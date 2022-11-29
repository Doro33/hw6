package ir.maktab_hw6.repository;

import ir.maktab_hw6.config.ApplicationConnection;
import ir.maktab_hw6.entity.User;

import java.sql.*;

public class UserRepository {
    public static void insert(User user) throws SQLException {
        String sql = """
                INSERT INTO userInfo(username, password, nationalCode, birthday)
                VALUES (?, ?, ?, ?)
                """;
        PreparedStatement ps = ApplicationConnection.getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getUSERNAME());
        ps.setString(2, user.getPASSWORD());
        ps.setString(3, user.getNationalCode());
        ps.setDate(4, Date.valueOf(user.getBirthday()));

        ps.execute();
        ResultSet generatedIds = ps.getGeneratedKeys();
        generatedIds.next();
        int id = generatedIds.getInt("id");
        user.setId(id);
        ps.close();
        generatedIds.close();
    }

    public static boolean usernameContains(String username) throws SQLException {
        String sql = """
                SELECT username from userInfo
                WHERE username=? ;
                """;
        PreparedStatement ps = ApplicationConnection.getConnection().prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static boolean nationalCodeContains(String nationalCode) throws SQLException {
        String sql = """
                SELECT nationalCode from userInfo
                WHERE nationalCode=? ;
                """;
        PreparedStatement ps = ApplicationConnection.getConnection().prepareStatement(sql);
        ps.setString(1, nationalCode);

        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public static int findIdByUsername(String username) throws SQLException {
        int id = 0;
        if (usernameContains(username)) {
            String sql = """
                    SELECT id from userInfo
                    WHERE username=?;
                    """;
            PreparedStatement ps = ApplicationConnection.getConnection()
                    .prepareStatement(sql);
            ps.setString(1, username);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            rs.next();

            id = rs.getInt("id");
            ps.close();
            rs.close();
        }
        return id;
    }

    public static String findPasswordByUsername(String username) throws SQLException {
        String password = "";
        if (usernameContains(username)) {
            String sql = """
                    SELECT password from userInfo
                    WHERE username=?;
                    """;
            PreparedStatement ps = ApplicationConnection.getConnection()
                    .prepareStatement(sql);
            ps.setString(1, username);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            rs.next();
            password = rs.getString("password");
            ps.close();
            rs.close();
        }
        return password;
    }

    public static void changePassword(int id, String password) throws SQLException {
        String sql = """
                UPDATE userInfo
                SET  password=?
                WHERE id = ?;
                """;
        PreparedStatement ps = ApplicationConnection.getConnection()
                .prepareStatement(sql);
        ps.setString(1, password);
        ps.setInt(2, id);
        ps.execute();
        ps.close();
    }

    /* public static void update(int id, User user) throws SQLException {
        String sql = """
                UPDATE userInfo
                SET  username=? , password=? , nationalCode=? , birthday=?
                WHERE id = ?
                """;
        PreparedStatement updateStatement = ApplicationConnection.getConnection()
                .prepareStatement(sql);
        updateStatement.setString(1, user.getUsername());
        updateStatement.setString(2, user.getPassword());
        updateStatement.setString(3, user.getNationalCode());
        updateStatement.setDate(4, Date.valueOf(user.getBirthday()));
        updateStatement.setInt(5, id);

        updateStatement.execute();
        updateStatement.close();
    }*/
}