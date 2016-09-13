package com.xidian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

/**
 * Achievement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "achievement", catalog = "studentmanage")
public class Achievement implements java.io.Serializable {

	// Fields

	private Long achievementId;
	private Course course;
	private Student student;
	private Teacher teacher;
	private Integer score;
	private Integer term;

	// Constructors

	/** default constructor */
	public Achievement() {
	}

	/** full constructor */
	public Achievement(Course course, Student student, Teacher teacher,
			Integer score, Integer term) {
		this.course = course;
		this.student = student;
		this.teacher = teacher;
		this.score = score;
		this.term = term;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "achievementID", unique = true, nullable = false)
	public Long getAchievementId() {
		return this.achievementId;
	}

	public void setAchievementId(Long achievementId) {
		this.achievementId = achievementId;
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
	@JoinColumn(name = "studentID")
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teacherID")
	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Column(name = "score")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(name = "term", nullable = false)
	public Integer getTerm() {
		return this.term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}
}