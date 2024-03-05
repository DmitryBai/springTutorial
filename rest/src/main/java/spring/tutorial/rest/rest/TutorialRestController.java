package spring.tutorial.rest.rest;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.tutorial.rest.entity.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TutorialRestController {
    private List<Student> students;

    @PostConstruct
    public void loadStudents() {
        students = new ArrayList<>();

        students.add(new Student("John", "Wick"));
        students.add(new Student("John", "Connor"));
        students.add(new Student("Sara", "Connor"));
    }

    @GetMapping("/students/")
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id is not found " + studentId);
        }

        return students.get(studentId);
    }

}
