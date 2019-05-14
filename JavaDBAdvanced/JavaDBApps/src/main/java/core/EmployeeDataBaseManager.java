package core;

import entities.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataBaseManager extends JdbcDataBaseManager<Employee> {
    public EmployeeDataBaseManager(Connection connection) {
        super(connection);
    }

    @Override
    public List<Employee> toList(ResultSet executeQuery) throws SQLException {
        List<Employee> list = new ArrayList<>();

        while (executeQuery.next()) {
            String firstName = executeQuery.getString("first_name");
            String lastName = executeQuery.getString("last_name");
            double salary = executeQuery.getDouble("salary");

            Employee employee = new Employee(firstName, lastName, salary);

            list.add(employee);
        }

        return list;
    }

    @Override
    protected String getObjectValuesAsString(Employee employee) {
        return "'" + employee.getFirstName() + "', '" + employee.getLastName() + "', " + employee.getSalary() + "";
    }

    @Override
    protected String getFieldsTitles() {
        return "first_name, last_name, salary";
    }

    @Override
    public String getSchemaName() {
        return "employees_new";
    }
}
