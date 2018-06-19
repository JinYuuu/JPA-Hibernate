package com.jpa.hibernate.jpahibernate;

import java.util.List;

import javax.persistence.EntityManager;
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
}
