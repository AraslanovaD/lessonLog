package spring.teachers_journal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.teachers_journal.entity.Teacher;
import spring.teachers_journal.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public void save(Teacher student) {
        teacherRepository.save(student);
    }

    @Override
    public Teacher get(int id) {
        Teacher teacher = null;
        Optional<Teacher> optional = teacherRepository.findById(id);

        if (optional.isPresent()) {
            teacher = optional.get();
        }
        return teacher;
    }

    @Override
    public void delete(int id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher getByEmail(String email) {
        Teacher teacher = teacherRepository.findByEmail(email);

        return teacher;
    }


}
