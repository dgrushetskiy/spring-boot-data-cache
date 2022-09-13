package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
public class RepoCache {
    @Autowired
    private StudentRepo repo;

    @Cacheable("AllStudents")
    public List<Student> getAllStudents(){
        System.out.println("Calling Repo - getAllStudents");
        return (List<Student>) repo.findAll();
    }

    @Cacheable(value = "StudentInfo", key = "#id")
    public Optional<Student> getStudents(@PathVariable int id){
        System.out.println("Calling Repo - getStudents");
        return  repo.findById(id);
    }

    public Student addStudent(@RequestBody Student newStudent){
        return repo.save(newStudent);
    }

    public void removeStudent(@PathVariable int id){
        repo.deleteById(id);
    }

    public Student updateStudent(@RequestBody Student newStudent, @PathVariable int id){
        return repo.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setGrade(newStudent.getGrade());
                    return repo.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setStudent_id(Math.toIntExact(id));
                    return repo.save(newStudent);
                });
    }
}
