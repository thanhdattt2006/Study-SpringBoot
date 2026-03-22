package com.dave.springboot;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // Tạo sẵn 2 thằng sinh viên mồi
    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Navin", 60),
            new Student(2, "Kiran", 65)
    ));

    // API lấy danh sách (Lệnh GET - đéo bị CSRF chặn)
    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // API thêm sinh viên (Lệnh POST - BỊ CSRF CHẶN HỌNG nếu không có Token)
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    // Lòi ra cái hàm lấy Bùa Token tao vừa dạy mày lúc nãy
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}