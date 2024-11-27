
import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabase {
    public static void main(String[] args) {
        try (@SuppressWarnings("unused")
Connection connection = DatabaseConnector.getConnection()) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
        }
    }
}
