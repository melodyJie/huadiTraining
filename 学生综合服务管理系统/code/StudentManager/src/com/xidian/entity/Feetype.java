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
 * Feetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "feetype", catalog = "studentmanage")
public class Feetype implements java.io.Serializable {

	// Fields

	private Long feetypeId;
	private Specialty specialty;
	private Fee fee;
	private College college;
	private Studentclass studentclass;
	private Integer grade;
	private Integer value;

	// Constructors

	/** default constructor */
	public Feetype() {
	}

	/** minimal constructor */
	public Feetype(Integer value) {
		this.value = value;
	}

	/** full constructor */
	public Feetype(Specialty specialty, Fee fee, College college,
			Studentclass studentclass, Integer grade, Integer value) {
		this.specialty = specialty;
		this.fee = fee;
		this.college = college;
		this.studentclass = studentclass;
		this.grade = grade;
		this.value = value;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feetypeID", unique = true, nullable = false)
	public Long getFeetypeId() {
		return this.feetypeId;
	}

	public void setFeetypeId(Long feetypeId) {
		this.feetypeId = feetypeId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "specialtyID")
	public Specialty getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "feeID")
	public Fee getFee() {
		return this.fee;
	}

	public void setFee(Fee fee) {
		this.fee = fee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "collegeID")
	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "classID")
	public Studentclass getStudentclass() {
		return this.studentclass;
	}

	public void setStudentclass(Studentclass studentclass) {
		this.studentclass = studentclass;
	}

	@Column(name = "grade")
	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(name = "value", nullable = false)
	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}