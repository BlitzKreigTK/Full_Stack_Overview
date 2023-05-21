package com.learning.springbootframework.bean;

public class Course {
	private int id;
	private String name;
	private String course;

	public Course(int id, String name, String course) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCourse() {
		return course;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", course=" + course + "]";
	}

}
