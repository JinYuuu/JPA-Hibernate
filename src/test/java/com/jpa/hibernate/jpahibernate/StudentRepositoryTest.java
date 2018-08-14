package com.jpa.hibernate.jpahibernate;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.hibernate.app.JpaHibernateApplication;
import com.jpa.hibernate.app.entity.Passport;
import com.jpa.hibernate.app.entity.Student;
import com.jpa.hibernate.app.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateApplication.class)
public class StudentRepositoryTest {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StudentRepository repository;
	
	@Autowired
	EntityManager em;

	@Test
	public void someTest() {
		repository.someOperationToUnderstandPersistenceContext();
	}
	
	@Test
	@Transactional //the session for all the test
	public void retrieveStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());		
	}
	
	@Test
	@Transactional //the session for all the test
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());		
	}
	
	@Test
	@Transactional //the session for all the test
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class,  20001L);	
		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());		
	}
	
}
