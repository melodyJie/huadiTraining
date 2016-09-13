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
 * Studentclass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "studentclass", catalog = "studentmanage")
public class Studentclass implements java.io.Serializable {

	// Fields

	private Long classId;
	private Specialty specialty;
	private String classNumber;

	// Constructors

	/** default constructor */
	public Studentclass() {
	}

	/** minimal constructor */
	public Studentclass(String classNumber) {
		this.classNumber = classNumber;
	}

	/** full constructor */
//	public Studentclass(Specialty specialty, String classNumber,
//			Set<Student> students) {
//		this.specialty = specialty;
//		this.classNumber = classNumber;
//		this.students = students;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "classID", unique = true, nullable = false)
	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "specialtyID")
	public Specialty getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	@Column(name = "classNumber", nullable = false, length = 20)
	public String getClassNumber() {
		return this.classNumber;
	}

	public void setClassNumber(String classNumber) {
		this.classNumber = classNumber;
	}
}