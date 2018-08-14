package com.jpa.hibernate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.hibernate.app.entity.Course;
import com.jpa.hibernate.app.entity.Review;

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
		Course course1 = new Course("Web Services in 100 Steps.");

		//let entity manager to treat this entity
		//persist : this change will be persisted to the database
		em.persist(course1); 
		//flush : the change what we've done until then, will be sent to database
		Course course2 = new Course("Angular Js in 100 Steps.");
		em.persist(course2);
		em.flush();
		
		//detach : nolonger persisted
		em.clear(); 
		//em.detach(course2);
		
		course1.setName("Web Services in 100 Steps - updated");
		course2.setName("Angular Js in 100 Steps - Updated");
		
		em.refresh(course1);
		//all the changes that have done to course1 will be lost
		
		em.flush();
	}

	public void addHardcodedReviewsForCourse() {
		// get the course 10003	
		Course course = findById(10003L);
		
		// add 2 reviews to it
		Review review1 = new Review("5", "the course is good");
		Review review2 = new Review("5", "the course is superb");
				
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		// save it to the database
		em.persist(review1);
		em.persist(review2);
		
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		// get the course 10003	
		Course course = findById(courseId);
		
		// add reviews to it
		for (Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
		
	}
}
