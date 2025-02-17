package spring.teachers_journal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.teachers_journal.repository.LessonRepository;
import spring.teachers_journal.entity.Lesson;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public void save(Lesson student) {
        lessonRepository.save(student);
    }

    @Override
    public Lesson get(int id) {
        Lesson student = null;
        Optional<Lesson> optional = lessonRepository.findById(id);

        if (optional.isPresent()) {
            student = optional.get();
        }
        return student;
    }

    @Override
    public void delete(int id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<Lesson> getByIdTeacher(int id){
        return lessonRepository.findByTeacherId(id);
    }
    @Override

    public List<Lesson> getByIdTeacherAndBeforeDate(int id, LocalDate date){
        return lessonRepository.findByTeacherIdAndLessonDateBefore(id,date);
    }
    @Override
    public List<Lesson> getByIdTeacherAndDate(int id, LocalDate date){
        return lessonRepository.findByTeacherIdAndLessonDate(id,date);
    }

    @Override
    public List<Lesson> getByIdTeacherAndAfterDate(int id, LocalDate date){
        return lessonRepository.findByTeacherIdAndLessonDateAfter(id,date);
    }
}
