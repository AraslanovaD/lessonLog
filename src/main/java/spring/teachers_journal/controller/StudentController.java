package spring.teachers_journal.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import spring.teachers_journal.details.TeacherDetails;
import spring.teachers_journal.entity.Lesson;
import spring.teachers_journal.entity.Student;
import spring.teachers_journal.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("")
    public String showAllStudents(@AuthenticationPrincipal TeacherDetails details,
                                  Model model){
        List<Student> students = studentService.getByIdTeacher(details.getTeacher().getId());
        model.addAttribute("students", students);

        return "/student/index";
    }

    @GetMapping("/{id}")
    public String showStudent(@PathVariable("id") int id, Model model){
        Student student = studentService.get(id);
        model.addAttribute("student",student);

        return "/student/show";
    }

    @GetMapping("/new")
    public String showNewStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);

        return "/student/new";
    }

    @GetMapping("/{id}/edit")
    public String showEditStudent(@PathVariable("id") int id, Model model){
        Student student = studentService.get(id);
        model.addAttribute("student", student);

        return "/student/edit";
    }

    @GetMapping("/{id}/lessons")
    public String showAllLessons(@PathVariable("id") int id, Model model, @RequestParam String time){
        Student student = studentService.get(id);
        List<Lesson> lessons=student.getLessons();

        if("before".equals(time)){
            lessons=lessons.stream().filter(l->l.getLessonDate().isBefore(LocalDate.now())).collect(Collectors.toList());
        } else if("after".equals(time)){
            lessons=lessons.stream().filter(l->l.getLessonDate().isAfter(LocalDate.now())).collect(Collectors.toList());
        } else {
            lessons=lessons.stream().filter(l->l.getLessonDate().isEqual(LocalDate.now())).collect(Collectors.toList());
        }

        model.addAttribute("student", student);
        model.addAttribute("lessons", lessons);

        return "/student/lessons";
    }

    @PostMapping("")
    public String addStudent(@AuthenticationPrincipal TeacherDetails details,
                             @Valid @ModelAttribute("student") Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/student/new";
        }

        student.setTeacher(details.getTeacher());
        studentService.save(student);

        return "redirect:/students";
    }

    @PutMapping("/{id}")
    public String editStudent(@AuthenticationPrincipal TeacherDetails details,
                              @Valid @ModelAttribute("student") Student student,BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "/student/edit";
        }

        student.setTeacher(details.getTeacher());
        studentService.save(student);

        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") int id){
        studentService.delete(id);

        return "redirect:/students";
    }

}
