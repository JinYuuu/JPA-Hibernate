package com.jpa.hibernate.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.hibernate.app.entity.Course;
import com.jpa.hibernate.app.entity.FullTimeEmployee;
import com.jpa.hibernate.app.entity.PartTimeEmployee;
import com.jpa.hibernate.app.entity.Review;
import com.jpa.hibernate.app.entity.Student;
import com.jpa.hibernate.app.repository.CourseRepository;
import com.jpa.hibernate.app.repository.EmployeeRepository;
import com.jpa.hibernate.app.repository.StudentRepository;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Course course = repository.findById(10001L);
		//logger.info("Course 10001 -> {}", course);
		//repository.save(new Course("Microservices in 100 steps"));
		//repository.deleteById(10001L);
		
		//repository.playWithEntityManager();
		
		//studentRepository.saveStudentWithPassport();
		
		/*List<Review> reviews = new ArrayList<>();
		reviews.add(new Review("5", "the course is good"));
		reviews.add(new Review("5", "the course is superb"));
		*/
		//courseRepository.addHardcodedRevie0wsForCourse();
		//courseRepository.addReviewsForCourse(10003L, reviews);
		
		//studentRepository.insertHardcodedStydentAndCourse();
		//studentRepository.insertStudentAndCourse(new Student("Jack"), 
		//		new Course("Microservices in 100 Steps"));
		//studentRepository.addNewCourseToStudent(new Course("Microservices in 100 Steps"), 20002L);
		
		/*
		employeeRepository.insert(
				new FullTimeEmployee("Jack", new BigDecimal("10000")));
		employeeRepository.insert(
				new PartTimeEmployee("Jill", new BigDecimal("50")));
		
		logger.info("retrieveAllPartTimeEmployees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		logger.info("retrieveAllFullTimeEmployees -> {}", employeeRepository.retrieveAllFullTimeEmployees());
		*/
		
		System.out.println("started. ^^");
	}
}
