package P04_DetailPrinter;

public class Manager extends Employee {

    private Iterable<String> documents;

    public Manager(String name, Iterable<String> documents) {
        super(name);
        this.documents = documents;
    }

    @Override
    public String getDetails() {
        return this.getName() + " " + this.documents.toString().replaceAll("[\\[\\]]", "");
    }
}
