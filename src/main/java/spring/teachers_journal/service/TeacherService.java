package spring.teachers_journal.service;

import spring.teachers_journal.entity.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> getAll();

    public void save(Teacher teacher);

    public Teacher get(int id);

    public void delete(int id);

    public Teacher getByEmail(String email);
}
