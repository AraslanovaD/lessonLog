package spring.teachers_journal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.teachers_journal.entity.Lesson;
import spring.teachers_journal.repository.StudentRepository;
import spring.teachers_journal.entity.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student get(int id) {
        Student student = null;
        Optional<Student> optional = studentRepository.findById(id);

        if (optional.isPresent()) {
            student = optional.get();
        }
        return student;
    }

    @Override
    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getByIdTeacher(int id){
        return studentRepository.findByTeacherId(id);
    }
}
