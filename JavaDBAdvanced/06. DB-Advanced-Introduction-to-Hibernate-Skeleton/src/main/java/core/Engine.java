package core;

import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Engine {
    private final EntityManager entityManager;

    public Engine(EntityManager em) {
        this.entityManager = em;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        int id = scanner.nextInt();

        this.getEmployeesWithAllProjects(id);

        this.entityManager.close();
    }

    /**
     * Problem 4 - Employees with Salary Over 50 000
     */
    private void getEmployeesWithSalaryOver50000() {
        String query = "FROM Employee WHERE salary > :expected";

        List<Employee> resultList = this.entityManager.createQuery(query, Employee.class)
                .setParameter("expected", BigDecimal.valueOf(50000))
                .getResultList();

        resultList.forEach(e -> System.out.println(e.getFirstName()));
    }

    /**
     * Problem 5 - Employees from Department
     */
    private void getEmployeesFromDepartments() {
        List<Employee> resultList = this.entityManager
                .createQuery("FROM Employee e WHERE e.department.name LIKE :name ORDER BY e.salary asc, e.id asc ", Employee.class)
                .setParameter("name", "Research and Development")
                .getResultList();

        resultList.forEach(e -> System.out.printf("%s %s from Research and Development - $%.2f%n", e.getFirstName(), e.getLastName(), e.getSalary()));
    }

    /**
     * Problem 6 - Adding a New Address and Updating Employee
     */
    private void addingNewAddress(String lastName) {
        this.entityManager.getTransaction().begin();

        try {
            Employee employee = this.entityManager.createQuery("FROM Employee e where e.lastName = :last_name", Employee.class)
                    .setParameter("last_name", lastName)
                    .getSingleResult();

            employee.setAddress(this.createNewAddress());

            this.entityManager.persist(employee);
            this.entityManager.getTransaction().commit();

        } catch (NoResultException nre) {
            this.entityManager.getTransaction().rollback();
        }
    }

    /**
     * Creates new address with text "Vitoshka 15" if not exist
     *
     * @return address
     */
    private Address createNewAddress() {
        Town sofia = this.entityManager.createQuery("FROM Town WHERE name = 'Sofia'", Town.class).getSingleResult();

        Address address = new Address();
        address.setTown(sofia);
        address.setText("Vitoshka 15");
        try {
            return this.entityManager.createQuery("FROM Address where text LIKE 'Vitoshka 15'", Address.class).getSingleResult();
        } catch (NoResultException nre) {
            this.entityManager.persist(address);

            return address;
        }
    }

    /**
     * Problem 7 - Addresses with Employee Count
     */
    private void getEmployeesCountByAddress() {
        List<Address> resultList = this.entityManager.createQuery("FROM Address ORDER BY employees.size desc, town.id asc", Address.class)
                .setMaxResults(10)
                .getResultList();

        resultList.forEach(r -> {
            System.out.printf("%s, %s - %d employees%n", r.getText(), r.getTown().getName(), r.getEmployees().size());
        });
    }

    /**
     * Problem 8 - Get Employee with Project
     */
    //TODO try to configure with this code - jdbc:mysql://yourHostName:3306/soft_uni?useUnicode=true&useFastDateParsing=false&characterEncoding=UTF-8
    private void getEmployeesWithAllProjects(int id) {
        try {
            Employee employee = this.entityManager.createQuery("FROM Employee WHERE id = ?", Employee.class)
                    .setParameter(0, id)
                    .getSingleResult();

            System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

            for (Project project : employee.getProjects()) {
                System.out.printf(" %s%n", project.getName());
            }

        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }
    }

    private void test(int id){
        Project project = this.entityManager.createQuery("from Project where id = :expected", Project.class)
                .setParameter("expected", id)
                .getSingleResult();

        System.out.println(project.getEndDate());
    }
}
