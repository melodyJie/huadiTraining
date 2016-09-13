package com.xidian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Choosing entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "choosing", catalog = "studentmanage")
public class Choosing implements java.io.Serializable {

	// Fields

	private Long choosingId;
	private Teach teach;
	private Student student;

	// Constructors

	/** default constructor */
	public Choosing() {
	}

	/** full constructor */
	public Choosing(Teach teach, Student student) {
		this.teach = teach;
		this.student = student;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "choosingID", unique = true, nullable = false)
	public Long getChoosingId() {
		return this.choosingId;
	}

	public void setChoosingId(Long choosingId) {
		this.choosingId = choosingId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "teachID")
	public Teach getTeach() {
		return this.teach;
	}

	public void setTeach(Teach teach) {
		this.teach = teach;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentID")
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}