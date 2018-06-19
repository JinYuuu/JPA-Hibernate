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
import org.springframework.transaction.annotation.Transactional;

import com.jpa.hibernate.app.JpaHibernateApplication;
import com.jpa.hibernate.app.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=JpaHibernateApplication.class)
public class NativeQueriesTest {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	EntityManager em;
	
	@Test
	public void native_queries_basic() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE where id = ? -> {}", resultList);
	}
	
	@Test
	public void native_queries_with_named_parameter() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("SELECT * FROM COURSE where id :? -> {}", resultList);
	}
	
	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query = em.createNativeQuery("Update COURSE set last_updated_date=sysdate()");
		int nbOfRowsUpdated = query.executeUpdate();
		logger.info("nbOfRowsUpdated -> {}", nbOfRowsUpdated);
	}
	
}
