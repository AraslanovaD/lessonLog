package spring.teachers_journal.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.teachers_journal.entity.Teacher;
import spring.teachers_journal.service.TeacherService;
import spring.teachers_journal.validator.TeacherValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TeacherService service;

    @Autowired
    private TeacherValidator validator;

    @GetMapping("/login")
    public String showLogin(){
        return "/auth/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        Teacher teacher = new Teacher();
        model.addAttribute("teacher",teacher);

        return "/auth/register";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult){
        validator.validate(teacher,bindingResult);

        if(bindingResult.hasErrors()){
            return "/auth/register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(encodedPassword);

        service.save(teacher);

        return "/auth/register_success";
    }
}
