package spring.teachers_journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.teachers_journal.entity.Lesson;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    //@Query("SELECT * FROM Lesson s WHERE s.id_teacher = ?1")
    public List<Lesson> findByTeacherId(int id_teacher);

    //@Query("SELECT * FROM Lesson s WHERE s.id_teacher = ?1 and s.lessonDate<?2")
    public List<Lesson> findByTeacherIdAndLessonDateBefore(int id_teacher, LocalDate date);

    //@Query("SELECT * FROM Lesson s WHERE s.id_teacher = ?1 and s.lessonDate>?2")
    public List<Lesson> findByTeacherIdAndLessonDateAfter(int id_teacher, LocalDate date);

    //@Query("SELECT * FROM Lesson s WHERE s.id_teacher = ?1 and s.lessonDate=?2")
    public List<Lesson> findByTeacherIdAndLessonDate(int id_teacher, LocalDate date);
}
