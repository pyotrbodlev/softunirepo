package org.softuni.springbootdemo.app;

import org.softuni.springbootdemo.domain.EmployeeServiceImpl;
import org.softuni.springbootdemo.entities.Employee;
import org.softuni.springbootdemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Application implements CommandLineRunner {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Override
    public void run(String... args) throws Exception {
        Employee employee = new Employee("New Empl");

        employeeService.createEmployee(employee);

        List<String> all = employeeService.getAll();

        System.out.println(all);
    }
}
