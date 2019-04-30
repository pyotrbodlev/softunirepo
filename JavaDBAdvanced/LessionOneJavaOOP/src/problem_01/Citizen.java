package problem_01;

public class Citizen implements Person, Birthable, Identifiable{
    private String name;
    private int age;
    private String birthdate;
    private String id;

    public Citizen(String name, int age, String id, String birthdate) {
        setName(name);
        setAge(age);
        setId(id);
        setBirthdate(birthdate);
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public String birthdate() {
        return this.birthdate;
    }

    @Override
    public String id() {
        return this.id;
    }

    public void setBirthdate(String birthdate) {
        this.name = birthdate;
    }
}
