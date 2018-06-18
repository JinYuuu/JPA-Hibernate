package com.jpa.hibernate.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
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
