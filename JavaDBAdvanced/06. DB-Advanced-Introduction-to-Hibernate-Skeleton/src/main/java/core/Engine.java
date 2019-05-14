package core;

import entities.*;
import javassist.compiler.ast.Pair;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.math.BigDecimal;
import java.util.*;

public class Engine {
    private final EntityManager entityManager;

    public Engine(EntityManager em) {
        this.entityManager = em;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        this.getEmployeesWithSalaryOver50000();
        this.entityManager.close();
    }

    /**
     * Problem 4 - Employees with Salary Over 50 000
     * <p>
     * Here im using two methods for executing query.
     * First with JPA CriteriaBuilder and without HQL.
     * Second with HQL query.
     * <p>
     * ResultList is the same.
     */
    private void getEmployeesWithSalaryOver50000() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> from = query.from(Employee.class);

        query.select(from).where(criteriaBuilder.greaterThan(from.get("salary"), 50000));

        List<Employee> resultList = this.entityManager.createQuery(query).getResultList();

//        String query = "FROM Employee WHERE salary > :expected";
//
//        List<Employee> resultList = this.entityManager.createQuery(query, Employee.class)
//                .setParameter("expected", BigDecimal.valueOf(50000))
//                .getResultList();
//
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
    private void getEmployeesWithAllProjects(int id) {
        try {
            Employee employee = this.entityManager.createQuery("FROM Employee WHERE id = :expected", Employee.class)
                    .setParameter("expected", id)
                    .getSingleResult();

            System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

            for (Project project : employee.getProjects()) {
                System.out.printf(" %s%n", project.getName());
            }

        } catch (NoResultException nre) {
            System.out.println(nre.getMessage());
        }
    }

    /**
     * Problem 9 - Find Latest 10 Projects
     */
    private void getLastTenProjects() {
        List<Project> resultList = this.entityManager.createQuery("from Project ORDER BY startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList();

        //Project name: All-Purpose Bike Stand
        // 	Project Description: Research, design and development of …
        // 	Project Start Date:2005-09-01 00:00:00.0
        // 	Project End Date: null
        resultList.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> {
                    System.out.printf("Project name: %s%n", p.getName());
                    System.out.printf(" Project Description: %s%n", p.getDescription());
                    System.out.printf(" Project Start Date:%s%n", p.getStartDate());
                    System.out.printf(" Project End Date: %s%n", p.getEndDate());
                });
    }

    /**
     * Problem 10 - Increase Salaries
     */
    private void increaseSalaryToEmployees() {
        List<Employee> employees = this.entityManager
                .createQuery("FROM Employee e WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')",
                        Employee.class)
                .getResultList();

        this.entityManager.getTransaction().begin();

        employees.forEach(e -> {
            e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
            System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
        });

        this.entityManager.getTransaction().commit();
    }

    /**
     * Problem 11 - Remove Towns
     *
     * @param town Expected town, which addresses need to be removed
     */
    private void deleteTown(String town) {

        List<Address> addresses = this.entityManager.createQuery("FROM Address a where a.town.name LIKE :expected", Address.class)
                .setParameter("expected", town)
                .getResultList();

        List<Employee> employees = this.entityManager.createQuery("FROM Employee WHERE address.town.name LIKE :expected", Employee.class)
                .setParameter("expected", town)
                .getResultList();

        this.entityManager.getTransaction().begin();

        employees.forEach(e -> e.setAddress(null));

        addresses.forEach(this.entityManager::remove);

        this.entityManager.getTransaction().commit();

        System.out.printf("%d addresses in %s deleted%n", addresses.size(), town);

    }

    /**
     * Problem 12 - Find Employees by First Name and sort by salary desc.
     *
     * @param pattern for searching employees.
     */
    private void findEmployeeByName(String pattern) {
        List<Employee> employees = this.entityManager.createQuery("from Employee WHERE firstName like CONCAT(:pattern, '%')", Employee.class)
                .setParameter("pattern", pattern)
                .getResultList();

        employees
                .stream()
                .sorted(Comparator.comparing(Employee::getSalary, Comparator.reverseOrder()))
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getJobTitle(),
                        e.getSalary()));
    }

    /**
     * Problem 13 - Max salary of each department.
     */
    private void maxSalaryOfDepartment() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
        Root<Employee> employee = query.from(Employee.class);
        query.groupBy(employee.get("department"));
        query.multiselect(employee.get("department"),
                criteriaBuilder.max(employee.get("salary")));
        TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);

        List<Object[]> resultList = typedQuery.getResultList();

        Map<Department, BigDecimal> map = new HashMap<>();

        for (Object[] objects : resultList) {
            Department department = (Department) objects[0];
            BigDecimal salary = (BigDecimal) objects[1];
            map.put(department, salary);
        }

        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .forEach(e -> System.out.printf("Department: %s === Max. Salary: %.2f%n", e.getKey().getName(), e.getValue()));

        entityManager.close();

    }
}
