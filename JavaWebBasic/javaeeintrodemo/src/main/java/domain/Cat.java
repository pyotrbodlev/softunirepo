package domain;

public class Cat {
    private String name;
    private String breed;
    private String color;
    private Integer age;

    public Cat(String name, String breed, String color, Integer age) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("Hello, my name is %s!%nI'm a %s cat!", this.getName(), this.getBreed());
    }
}
