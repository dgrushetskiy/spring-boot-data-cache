package com.example.demo.controller;

import com.example.demo.dao.RepoCache;
import com.example.demo.dao.StudentRepo;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private RepoCache repoC;


    @GetMapping("/students")
    private List<Student> getAllStudents(){
        return repoC.getAllStudents();
    }


    @GetMapping("/student/{id}")
    private Optional<Student> getStudents(@PathVariable int id){

        return  repoC.getStudents(id);
    }

    @PostMapping("/students")
    private Student addStudent(@RequestBody Student newStudent){
        return repoC.addStudent(newStudent);
    }

    @DeleteMapping("/students/{id}")
    private void removeStudent(@PathVariable int id){
        repoC.removeStudent(id);
    }


    @PutMapping("/students/{id}")
    private Student updateStudent(@RequestBody Student newStudent, @PathVariable int id){

        return repoC.updateStudent(newStudent, id);

    }
}
