package spring.teachers_journal.validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import spring.teachers_journal.entity.Teacher;
import spring.teachers_journal.service.TeacherDetailsService;

@Component
public class TeacherValidator implements Validator {
    @Autowired
    private TeacherDetailsService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return Teacher.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Teacher teacher = (Teacher) o;

        try {
            service.loadUserByUsername(teacher.getEmail());
        } catch (UsernameNotFoundException ignored) {
            return; // все ок, пользователь не найден
        }

        errors.rejectValue("email", "", "Человек с такой почтой уже существует");
    }
}
