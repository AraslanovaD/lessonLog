package spring.teachers_journal.entity;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="id_teacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name="id_student")
    private Student student;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "lesson_date")
    private LocalDate lessonDate;

    @Column(name = "lesson_start")
    private LocalTime lessonStart;

    @Column(name = "lesson_end")
    private LocalTime lessonEnd;

    private String plan;

    private String homework;

    public Lesson() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }

    public LocalTime getLessonStart() {
        return lessonStart;
    }

    public void setLessonStart(LocalTime lessonStart) {
        this.lessonStart = lessonStart;
    }

    public LocalTime getLessonEnd() {
        return lessonEnd;
    }

    public void setLessonEnd(LocalTime lessonEnd) {
        this.lessonEnd = lessonEnd;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonDate=" + lessonDate +
                ", lessonStart=" + lessonStart +
                ", lessonEnd=" + lessonEnd +
                '}';
    }
}
