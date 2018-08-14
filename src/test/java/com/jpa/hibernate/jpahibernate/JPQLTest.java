package com.jpa.hibernate.jpahibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpa.hibernate.app.JpaHibernateApplication;
import com.jpa.hibernate.app.entity.Course;
import com.jpa.hibernate.app.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateApplication.class)
public class JPQLTest {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	EntityManager em;
	
	@Test
	public void jpql_basic() {
		List resultList = em.createNamedQuery("query_get_all_courses").getResultList();
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = 
				em.createNamedQuery("query_get_all_courses", Course.class);
		
		List<Course> resultList = query.getResultList();
		
		logger.info("Select c From Course c -> {}", resultList);
	}
	
	
	@Test
	public void jpql_where() {
		TypedQuery<Course> query = 
				em.createNamedQuery("query_get_100_Step_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c where name like '%100 Steps' -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = 
				em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("jpql_courses_without_students -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = 
				em.createQuery("Select c from Course c where size(c.students) >=2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("jpql_courses_with_atleast_2_students -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_order_by_students() {
		TypedQuery<Course> query = 
				em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("jpql_courses_with_atleast_2_students -> {}", resultList);
	}
	
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = 
				em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("jpql_students_with_passports_in_a_certain_pattern -> {}", resultList);
	}
	
	//like
	//BETWTEEN 100 and 1000
	//IS NULL
	//upper, lower, trim, length
	
	
	//JOIN => Select c,s from Course c JOIN c.students s //will not return back the courses without students
	//LEFT JOIN => Select c,s from Course c LEFT JOIN c.students s
	//CROSS JOIN => Select c,s from Course c, Student s 
		//3 and 4 =>3*4 = 12 Rows
		//takes every course and mix them with every students in there
	@Test
	public void join() {
		Query query = 
				em.createQuery("Select c,s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("join -> {}", resultList.size());//4
		for(Object[] result : resultList) {
			logger.info("course{} Student{}", result[0], result[1]);
			//result[0];//course
			//result[1];//student
		}
	}
	
	@Test
	public void left_join() {
		Query query = 
				em.createQuery("Select c,s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("join -> {}", resultList.size());//5
		for(Object[] result : resultList) {
			logger.info("course{} Student{}", result[0], result[1]);
			//result[0];//course
			//result[1];//student
		}
	}
	
	@Test
	public void cross_join() {
		Query query = 
				em.createQuery("Select c,s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("join -> {}", resultList.size()); //9
		for(Object[] result : resultList) {
			logger.info("course{} Student{}", result[0], result[1]);
			//result[0];//course
			//result[1];//student
		}
	}
}
