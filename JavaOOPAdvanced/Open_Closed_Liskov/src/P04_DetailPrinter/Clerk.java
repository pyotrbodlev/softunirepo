package P04_DetailPrinter;

public class Clerk extends Employee {
    private double salary;

    public Clerk(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public String getDetails() {
        return this.getName() + " has salary " + this.salary;
    }
}
