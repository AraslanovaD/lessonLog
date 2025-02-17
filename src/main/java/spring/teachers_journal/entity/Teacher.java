package spring.teachers_journal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @NotBlank(message = "Почта не должна быть пустой")
    @Email(message = "Некорректная почта")
    @Column(name="email")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустой")
    @Size(min = 8, message = "Пароль должен быть от 8 символов длиной")
    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "teacher",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "teacher",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    public Teacher() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", students=" + students +
                ", lessons=" + lessons +
                '}';
    }
}
