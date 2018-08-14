package com.jpa.hibernate.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
//@Table(name="Course")
@NamedQueries(value = {
		@NamedQuery(name="query_get_all_courses", 
				query="Select c From Course c"),
		@NamedQuery(name="query_get_100_Step_courses", 
		query="Select c From Course c where name like '%100 Steps'")
})
@Cacheable
public class Course {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	//we are at the one side
	@OneToMany(mappedBy="course", fetch=FetchType.EAGER) 
	//Eager fetch: left outer join on the courses
	// SELECT ... FROM course LEFT OUTER JOIN reviews ON course.id = review.course.id
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy="courses") //student is the owning side of the relationship
	private List<Student> students = new ArrayList<>();
	
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

	public List<Review> getReviews() {
		return reviews;
	}

	//I dont want to let others set Review list
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(review);
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}

}
