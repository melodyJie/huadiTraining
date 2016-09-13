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

/**
 * Specialtycourse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "specialtycourse", catalog = "studentmanage")
public class Specialtycourse implements java.io.Serializable {

	// Fields

	private Long specialtyCourseId;
	private Specialty specialty;
	private Teach teach;
	private Integer term;

	// Constructors

	/** default constructor */
	public Specialtycourse() {
	}

	/** minimal constructor */
	public Specialtycourse(Integer term) {
		this.term = term;
	}

	/** full constructor */
	public Specialtycourse(Specialty specialty, Teach teach, Integer term) {
		this.specialty = specialty;
		this.teach = teach;
		this.term = term;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "specialtyCourseID", unique = true, nullable = false)
	public Long getSpecialtyCourseId() {
		return this.specialtyCourseId;
	}

	public void setSpecialtyCourseId(Long specialtyCourseId) {
		this.specialtyCourseId = specialtyCourseId;
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
	@JoinColumn(name = "teachID")
	public Teach getTeach() {
		return this.teach;
	}

	public void setTeach(Teach teach) {
		this.teach = teach;
	}

	@Column(name = "term", nullable = false)
	public Integer getTerm() {
		return this.term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

}