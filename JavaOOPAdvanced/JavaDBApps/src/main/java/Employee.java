public class Employee {
    private String firstName;
    private String lastName;
    private double salary;

    public Employee(String firstName, String lastName, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("Hello! My name is %s %s! My salary is %.2f", this.firstName, this.lastName, this.salary);
    }
}
