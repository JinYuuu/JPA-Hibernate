package com.jpa.hibernate.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name="Course")
@NamedQueries(value = {
		@NamedQuery(name="query_get_all_courses", 
				query="Select c From Course c"),
		@NamedQuery(name="query_get_100_Step_courses", 
		query="Select c From Course c where name like '%100 Steps'")
})
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	//this constructor will used by jpa to create this specific bean
	//to ensure that other class which inherited course will be able to use the constructor
	protected Course() {}
	
	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}
	
	
	
}
