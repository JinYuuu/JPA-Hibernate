package com.jpa.hibernate.app.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpa.hibernate.app.JpaHibernateApplication;
import com.jpa.hibernate.app.entity.Course;
import com.jpa.hibernate.app.repository.CourseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateApplication.class)
public class CourseRepositoryTest {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository repository;
	
	/**
	 * tests the findById method
	 */
	@Test
	public void findById_basic() {
		Course course = repository.findById(10001L);
		assertEquals("JPA in 50 steps", course.getName());
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
}
