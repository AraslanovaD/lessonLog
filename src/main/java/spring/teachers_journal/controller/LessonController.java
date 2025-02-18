package spring.teachers_journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.teachers_journal.details.TeacherDetails;
import spring.teachers_journal.entity.Lesson;
import spring.teachers_journal.entity.Student;
import spring.teachers_journal.service.LessonService;
import spring.teachers_journal.service.StudentService;


import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @Autowired
    StudentService studentService;

    @GetMapping("")
    public String showAllLessons(@AuthenticationPrincipal TeacherDetails details, Model model, @RequestParam("time") String time){
        List<Lesson> lessons=null;

        if("before".equals(time)){
            lessons=lessonService.getByIdTeacherAndBeforeDate(details.getTeacher().getId(), LocalDate.now());
        } else if("after".equals(time)){
            lessons=lessonService.getByIdTeacherAndAfterDate(details.getTeacher().getId(), LocalDate.now());
        } else {
            lessons=lessonService.getByIdTeacherAndDate(details.getTeacher().getId(), LocalDate.now());
        }

        //lessons = lessonService.getByIdTeacher(details.getTeacher().getId());
        model.addAttribute("lessons", lessons);

        return "/lesson/index";
    }

    @GetMapping("/{id}")
    public String showLesson(@PathVariable("id") int id, Model model){
        Lesson lesson = lessonService.get(id);
        model.addAttribute("lesson",lesson);

        return "/lesson/show";
    }

    @GetMapping("/new")
    public String showNewLesson(@AuthenticationPrincipal TeacherDetails details, Model model){
        Lesson lesson = new Lesson();
        model.addAttribute("lesson", lesson);

        List<Student> students = studentService.getByIdTeacher(details.getTeacher().getId());
        model.addAttribute("students", students);

        return "/lesson/new";
    }

    @GetMapping("/{id}/edit")
    public String showEditLesson(@AuthenticationPrincipal TeacherDetails details,
                                 @PathVariable("id") int id, Model model){
        Lesson lesson = lessonService.get(id);
        model.addAttribute("lesson", lesson);

        List<Student> students = studentService.getByIdTeacher(details.getTeacher().getId());
        model.addAttribute("students", students);

        return "/lesson/edit";
    }

    @PostMapping("")
    public String addLesson(@AuthenticationPrincipal TeacherDetails details,
                            @ModelAttribute("lesson") Lesson lesson){

        lesson.setTeacher(details.getTeacher());

        lessonService.save(lesson);
        return "redirect:/lessons?time=now";
    }

    @PutMapping("/{id}")
    public String editLesson(@AuthenticationPrincipal TeacherDetails details,
                             @ModelAttribute("lesson") Lesson lesson, @PathVariable("id") int id){
        lesson.setTeacher(details.getTeacher());

        lessonService.save(lesson);

        return "redirect:/lessons";
    }

    @DeleteMapping("/{id}")
    public String deleteLesson(@PathVariable("id") int id){
        lessonService.delete(id);

        return "redirect:/lessons";
    }

}
