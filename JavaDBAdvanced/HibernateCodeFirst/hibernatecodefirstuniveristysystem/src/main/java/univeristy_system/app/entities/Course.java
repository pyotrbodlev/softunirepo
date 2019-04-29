package univeristy_system.app.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import univeristy_system.app.utils.Idable;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "courses")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Course extends Idable {
    //name, description, start date, end date, credits
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int credits;
    private Teacher teacher;
    private Set<Student> students;

    public Course() {
    }

    public Course(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    @Column(length = 30, unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column()
    @Type(type = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courses")
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
