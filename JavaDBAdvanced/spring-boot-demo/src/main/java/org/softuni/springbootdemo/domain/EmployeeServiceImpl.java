package org.softuni.springbootdemo.domain;

import org.softuni.springbootdemo.entities.Employee;
import org.softuni.springbootdemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<String> getAll() {
        List<String> result = new ArrayList<>();

        Iterable<Employee> all = employeeRepository.findAll();

        for (Employee employee : all) {
            result.add(employee.getName());
        }
        return result;
    }
}
