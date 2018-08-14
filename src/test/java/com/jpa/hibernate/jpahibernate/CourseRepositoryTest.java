package com.jpa.hibernate.jpahibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.hibernate.app.JpaHibernateApplication;
import com.jpa.hibernate.app.entity.Course;
import com.jpa.hibernate.app.entity.Review;
import com.jpa.hibernate.app.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateApplication.class)
public class CourseRepositoryTest {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	@Autowired
	EntityManager em;
	
	/**
	 * tests the findById method
	 */
	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());
	}
	
	
	@Test
	@Transactional
	public void findById_firstLevelCacheDemo() {
		Course course = repository.findById(10001L);
		logger.info("First Course Retrieved {}", course);
		
		Course course1 = repository.findById(10001L);
		logger.info("First Course Retrieved again {}", course1);
		// course1 data is retrieved in first cache: one transaction
		
		//if not @Transactional , the query will be executed twice
		
		assertEquals("JPA in 50 steps", course.getName());
		assertEquals("JPA in 50 steps", course1.getName());
	}
	
	
	
	/**
	 * Tests the deleteById method
	 */
	@Test
	@DirtiesContext //Spring will automatically reset the data after this test
	public void deleteById_basic() {
		Course course = repository.findById(10002L);
		assertNull(repository.findById(10002L));
	}
	
	/**
	 * Tests the save method
	 */
	@Test
	@DirtiesContext
	public void save_basic() {
		//get a course
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps", repository.findById(10001L).getName());
		//update the details of it
		course.setName("JPA in 50 steps - Updated");
		repository.save(course);
		//check the value
		assertEquals("JPA in 50 steps - Updated", repository.findById(10001L).getName());
	}
	
	@Test
	@DirtiesContext
	public void playWithEntityManagers() {
		repository.playWithEntityManager();
	}
	
	@Test
	@DirtiesContext
	@Transactional //LazyInitializationException
	public void retrieveReviewsForCourse() {
		Course course = repository.findById(10001L);
		logger.info("{}",course.getReviews());
	}
	
	@Test
	@DirtiesContext
	@Transactional //LazyInitializationException
	public void retrieveCourseForReview() {
		Review review = em.find(Review.class,50001L);
		logger.info("{}",review.getCourse());
	}
}
