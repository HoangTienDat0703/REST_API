package com.example.practive1.controller;

import com.example.practive1.entity.Student;
import com.example.practive1.request.CreateStudentRequest;
import com.example.practive1.request.InQueryRequest;
import com.example.practive1.request.UpdateStudentRequest;
import com.example.practive1.response.StudentResponse;
import com.example.practive1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v2/api/student/")
public class StudentController {

    @Autowired
    StudentService studentService;
//    @Value("${app.name: AYE}")
//    private String app;

    @GetMapping("getAll")
    public List<StudentResponse> getAllStudents(){
        List<Student> studentList = studentService.getAllStudents();

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(studentA -> {
            studentResponseList.add(new StudentResponse(studentA));
        });
        return studentResponseList;
    }

    @PostMapping("create")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest){
        Student student = studentService.creatStudent(createStudentRequest);
        return new StudentResponse(student);
    }

    @PutMapping("update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest){
        Student student = studentService.updateStudent(updateStudentRequest);
        return new StudentResponse(student);
    }

    @DeleteMapping("delete")
    public String deleteStudent(@RequestParam long id){
        return studentService.deleteStudent(id);
    }

    @DeleteMapping("deleteId/{id}")
    public String deleteStudentById(@PathVariable("id") long idd){
        return studentService.deleteStudent(idd);
    }

    @GetMapping("getFirstName/{firstName}")
    public List<StudentResponse> getByFirstName(@PathVariable String firstName){
        List<Student> studentList = studentService.getByFirstName(firstName);

        List<StudentResponse> studentResponseList = new ArrayList<>();

        studentList.stream().forEach(studentAA -> {
            studentResponseList.add(new StudentResponse(studentAA));
        });

        return studentResponseList;
    }

    @GetMapping("getFirstName/{firstName}/{lastName}")
    public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName,
                                                           @PathVariable String lastName){
        return new StudentResponse(studentService.getByFirstNameAndLastName(firstName,lastName));
    }

    @GetMapping("getByFirstBNameIn")
    public List<StudentResponse> getFirstNameIn(
            @RequestBody InQueryRequest inQueryRequest){
        List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(studentIn -> {
            studentResponseList.add(new StudentResponse(studentIn));
        });
        return studentResponseList;
    }

    @GetMapping("getAllPagination")
    public List<StudentResponse> getAllPagination(@RequestParam int pageNo,
                                  @RequestParam int pageSize){
        List<Student> studentList = studentService.getAllStudentsWithPagination(pageNo,pageSize);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(studentP ->{
            studentResponseList.add(new StudentResponse(studentP));
        });
        return studentResponseList;
    }

    @PostMapping("createA")
    public void add(){

    }
}
