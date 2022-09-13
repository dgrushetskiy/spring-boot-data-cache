package com.example.demo;

import com.example.demo.dao.StudentRepo;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class DemoApplication implements ApplicationRunner {

	@Autowired
	StudentRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		repo.save(new Student("Ana", "One"));
		repo.save(new Student("Bob", "Two"));
		repo.save(new Student("Charlie", "One"));
		repo.save(new Student("David", "Three"));
	}
}
