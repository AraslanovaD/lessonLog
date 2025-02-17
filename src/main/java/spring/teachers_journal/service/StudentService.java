package spring.teachers_journal.service;


import spring.teachers_journal.entity.Lesson;
import spring.teachers_journal.entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    public List<Student> getAll();

    public void save(Student employee);

    public Student get(int id);

    public void delete(int id);

    public List<Student> getByIdTeacher(int id);
}
