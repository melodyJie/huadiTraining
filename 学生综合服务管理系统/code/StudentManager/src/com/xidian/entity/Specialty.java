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
 * Specialty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "specialty", catalog = "studentmanage")
public class Specialty implements java.io.Serializable {

	// Fields

	private Long specialtyId;
	private College college;
	private String specialtyName;

	// Constructors

	/** default constructor */
	public Specialty() {
	}

	/** minimal constructor */
	public Specialty(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	/** full constructor */
//	public Specialty(College college, String specialtyName,
//			Set<Studentclass> studentclasses, Set<Feetype> feetypes) {
//		this.college = college;
//		this.specialtyName = specialtyName;
//		this.studentclasses = studentclasses;
//		this.feetypes = feetypes;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "specialtyID", unique = true, nullable = false)
	public Long getSpecialtyId() {
		return this.specialtyId;
	}

	public void setSpecialtyId(Long specialtyId) {
		this.specialtyId = specialtyId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "collegeID")
	public College getCollege() {
		return this.college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	@Column(name = "specialtyName", nullable = false, length = 30)
	public String getSpecialtyName() {
		return this.specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}
}