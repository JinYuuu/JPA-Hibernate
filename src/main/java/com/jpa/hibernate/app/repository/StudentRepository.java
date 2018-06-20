package com.jpa.hibernate.app.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.hibernate.app.entity.Passport;
import com.jpa.hibernate.app.entity.Student;

@Repository
@Transactional
public class StudentRepository {
	@Autowired
	EntityManager em;
	
	/**
	 * a simple find method
	 * @param id : the id of the student
	 * @return Student : the found student
	 */
	public Student findById(Long id) {
		return em.find(Student.class, id);
	}
	
	/**
	 * to insert or update
	 * @param student :the student to save
	 * @return the saved student
	 */
	public Student save(Student student) { //-> insert or update
		if(student.getId() == null) {
			//insert
			em.persist(student);
		} else {
			//update
			em.merge(student);
		}
		return student;
	}
	
	/**
	 * deletes a student by id
	 * @param id : the id of the student
	 */
	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}
	
	
	public void saveStudentWithPassport() {
		//before we create a student, we should create the passport
		Passport passport = new Passport("Z123456");
		//create a hibernate sequence
		em.persist(passport);
		
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student); 
		
	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 2000L);
		//Persistence Context (student)
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		//Persistence Context (student, passport)
		
		//Database Operation 3 - update passport
		passport.setNumber("E123457");
		//Persistence Context (student, passport++)
		
		//Database Operation 4 - update student
		student.setName("Ranga - updated");
		//Persistence Context (student++, passport++)
	}
}
