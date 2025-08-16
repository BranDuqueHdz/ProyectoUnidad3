import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
    private static final String URL = "jdbc:mysql://biugdxp2akrzywrjd2y4-mysql.services.clever-cloud.com:3306/biugdxp2akrzywrjd2y4";
    private static final String USER = "uqea9d1pldqu3pd2";
    private static final String PASSWORD = "oUSQ4gkwC09Psv4sLtC0";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}