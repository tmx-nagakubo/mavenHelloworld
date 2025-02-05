
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlInjection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private static final String USER = "yourusername";
    private static final String PASSWORD = "yourpassword";

    public static void main(String[] args) {
        String userInput = "1 OR 1=1"; // SQLインジェクション攻撃の例

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD); Statement stmt = connection.createStatement()) {

            // 脆弱なSQLクエリの構築
            String query = "SELECT * FROM users WHERE id = " + userInput;

            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    System.out.println("User ID: " + rs.getInt("id"));
                    System.out.println("Username: " + rs.getString("username"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
