package com.jpa.hibernate.app.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.hibernate.app.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	@Autowired
	EntityManager em;
	
	/**
	 * a simple find method
	 * @param id : the id of the course
	 * @return Course : the found course
	 */
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	
	/**
	 * to insert or update
	 * @param course :the course to save
	 * @return the saved course
	 */
	public Course save(Course course) { //-> insert or update
		if(course.getId() == null) {
			//insert
			em.persist(course);
		} else {
			//update
			em.merge(course);
		}
		return course;
	}
	
	/**
	 * deletes a course by id
	 * @param id : the id of the course
	 */
	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}
	
	public void playWithEntityManager() {
		Course course = new Course("Web Services in 100 Steps.");
		em.persist(course); //let entity manager to treat this entity
		// this change will be persisted to the database
		course.setName("Web Services in 100 Steps - updated");
		
	}
}
