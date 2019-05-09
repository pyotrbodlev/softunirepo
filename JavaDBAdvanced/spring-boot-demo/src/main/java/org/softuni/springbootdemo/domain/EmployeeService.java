package org.softuni.springbootdemo.domain;

import org.softuni.springbootdemo.entities.Employee;

import java.util.List;

public interface EmployeeService {
    void createEmployee(Employee employee);

    List<String> getAll();
}
