import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        try {
            // 1. Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to MySQL
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/feedbackdb",
                "root",
                "PASSWORD"  // your password is empty
            );

            // 3. Just testing connection
            System.out.println("Connection Successful!");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
