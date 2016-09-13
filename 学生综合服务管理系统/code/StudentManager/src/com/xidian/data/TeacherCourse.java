package com.xidian.data;

import java.util.List;

import com.xidian.entity.Course;
import com.xidian.entity.Teacher;

public class TeacherCourse {
	private Teacher teacher;
	private List<Course> courses;
	private String available;

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
