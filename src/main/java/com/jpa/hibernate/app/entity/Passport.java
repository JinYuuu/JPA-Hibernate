package com.jpa.hibernate.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
public class Passport {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String number;
	
	//to make the student the owning side of the relationship
	@OneToOne(fetch=FetchType.LAZY, mappedBy="passport")
	private Student student;
	

	//this constructor will used by jpa to create this specific bean
	//to ensure that other class which inherited course will be able to use the constructor
	protected Passport() {}
	
	public Passport(String name) {
		this.number = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	
	public String getNumber() {
		return number;
	}

	public void setNumber(String name) {
		this.number = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Passport [number=" + number + "]";
	}
	
	
	
}
