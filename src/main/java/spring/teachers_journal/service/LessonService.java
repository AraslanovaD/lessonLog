package spring.teachers_journal.service;

import spring.teachers_journal.entity.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {
    public List<Lesson> getAll();

    public void save(Lesson lesson);

    public Lesson get(int id);

    public void delete(int id);

    public List<Lesson> getByIdTeacher(int id);

    public List<Lesson> getByIdTeacherAndBeforeDate(int id, LocalDate date);

    public List<Lesson> getByIdTeacherAndDate(int id, LocalDate date);

    public List<Lesson> getByIdTeacherAndAfterDate(int id, LocalDate date);
}
