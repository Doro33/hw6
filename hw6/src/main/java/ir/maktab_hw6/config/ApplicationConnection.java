package ir.maktab_hw6.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ApplicationConnection {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "vaqtinichegerist";
    private static Connection connection;

    static {
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/maktab_hw_6",
                            USERNAME,
                            PASSWORD
                    );
            //System.out.println("connected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
