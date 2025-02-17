package spring.teachers_journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.teachers_journal.details.TeacherDetails;
import spring.teachers_journal.entity.Teacher;
import spring.teachers_journal.repository.TeacherRepository;

@Service
public class TeacherDetailsService implements UserDetailsService {
    @Autowired
    private TeacherRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = repository.findByEmail(username);

        if(teacher==null){
            throw new UsernameNotFoundException("User not found");
        }

        return new TeacherDetails(teacher);
    }
}
