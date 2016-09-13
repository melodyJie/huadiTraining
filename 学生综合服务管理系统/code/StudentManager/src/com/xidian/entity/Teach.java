package com.xidian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Teach entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teach", catalog = "studentmanage")
public class Teach implements java.io.Serializable {

	// Fields

	private Long teachId;
	private Course course;
	private Teacher teacher;
	private Integer state;

	/** default constructor */
	public Teach() {
	}

	/** full constructor */
//	public Teach(Course course, Teacher teacher, Set<Choosing> choosings) {
//		this.course = course;
//		this.teacher = teacher;
//		this.choosings = choosings;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "teachID", unique = true, nullable = false)
	public Long getTeachId() {
		return this.teachId;
	}

	public void setTeachId(Long teachId) {
		this.teachId = teachId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "courseID")
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacherID")
	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	@Column(name = "state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}