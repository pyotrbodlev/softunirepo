import core.EmployeeDataBaseManager;
import domain.DataBaseManager;
import entities.Employee;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();

        props.setProperty("user", "root");

        props.setProperty("password", "1234");

        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        DataBaseManager<Employee> manager = new EmployeeDataBaseManager(connection);

        Employee peshkata = new Employee("Pesho", "Peshev", 1000000d);

        manager.insert(peshkata);

        List<Employee> pesho = manager.getEntitiesByName("Pesho");

        for (Employee employee : pesho) {
            System.out.println(employee.toString());
        }
    }
}
