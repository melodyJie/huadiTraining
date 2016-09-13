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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "course", catalog = "studentmanage")
public class Course implements java.io.Serializable {

	// Fields

	private Long courseId;
	private String courseName;
	private String courseType;
	private Integer credit;

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(String courseName, String courseType, Integer credit) {
		this.courseName = courseName;
		this.courseType = courseType;
		this.credit = credit;
	}

	/** full constructor */
//	public Course(String courseName, String courseType, Integer credit,
//			Set<Teach> teachs, Set<Achievement> achievements) {
//		this.courseName = courseName;
//		this.courseType = courseType;
//		this.credit = credit;
//		this.teachs = teachs;
//		this.achievements = achievements;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "courseID", unique = true, nullable = false)
	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Column(name = "courseName", nullable = false, length = 30)
	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "courseType", nullable = false, length = 10)
	public String getCourseType() {
		return this.courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	@Column(name = "credit", nullable = false)
	public Integer getCredit() {
		return this.credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		return true;
	}
}