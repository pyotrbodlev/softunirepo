package univeristy_system.app.core;

import univeristy_system.app.entities.Course;
import univeristy_system.app.entities.Student;
import univeristy_system.app.entities.Teacher;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Set;

public class Engine implements Runnable {
    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {
        Student pesho = new Student("Pesho", "Peshov", "+987987987");
        Student gosho = new Student("Gosho", "Goshov", "+987987987");

        Teacher teacher = new Teacher("Daskal", "Ivanov", "+8789141");

        Course course = new Course("Java Db", new Date());
        Course course1 = new Course("Java Web", new Date());

        this.entityManager.getTransaction().begin();

        this.entityManager.persist(pesho);
        this.entityManager.persist(teacher);

        this.entityManager.persist(course);
        this.entityManager.persist(course1);

        pesho.setCourses(Set.of(course1, course));

        course.setStudents(Set.of(pesho, gosho));
        course1.setStudents(Set.of(pesho, gosho));

        course.setTeacher(teacher);
        course1.setTeacher(teacher);

        this.entityManager.getTransaction().commit();

        this.entityManager.find(Student.class, 1).getCourses().forEach(c -> System.out.println(c.getName()));

        this.entityManager.find(Course.class, 3).getStudents().forEach(s -> System.out.println(s.getFirstName() + " " + s.getLastName()));

    }
}
