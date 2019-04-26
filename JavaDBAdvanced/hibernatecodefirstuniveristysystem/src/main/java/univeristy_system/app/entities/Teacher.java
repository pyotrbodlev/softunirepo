package univeristy_system.app.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    private String email;
    private BigDecimal salaryPerHour;
    private Set<Course> courses;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName, phoneNumber);
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @OneToMany(mappedBy = "teacher",
            targetEntity = Course.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
