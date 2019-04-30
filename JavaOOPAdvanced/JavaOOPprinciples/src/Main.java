import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();

        props.setProperty("user", "root");

        props.setProperty("password", "1234");

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement stmt = connection.prepareStatement

                ("SELECT * FROM employees WHERE salary > ?");

        String salary = "1200";

        stmt.setDouble(1, Double.parseDouble(salary));

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            System.out.printf("%s %s%n",

                    rs.getString("first_name"),
                    rs.getString("last_name"));
        }
    }
}
