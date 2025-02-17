package spring.teachers_journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.teachers_journal.entity.Lesson;
import spring.teachers_journal.entity.Student;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    //@Query("SELECT * FROM Student s WHERE s.id_teacher = ?1")
    public List<Student> findByTeacherId(int id_teacher);
}
