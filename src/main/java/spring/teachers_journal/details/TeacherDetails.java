package spring.teachers_journal.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.teachers_journal.entity.Student;
import spring.teachers_journal.entity.Teacher;

import java.util.Collection;
import java.util.List;

public class TeacherDetails implements UserDetails {
    private Teacher teacher;

    public TeacherDetails(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.teacher.getPassword();
    }

    @Override
    public String getUsername() {
        return this.teacher.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
