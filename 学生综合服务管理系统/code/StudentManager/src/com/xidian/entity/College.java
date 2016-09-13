package com.xidian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * College entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "college", catalog = "studentmanage")
public class College implements java.io.Serializable {

	// Fields

	private Long collegeId;
	private String collegeName;

	// Constructors

	/** default constructor */
	public College() {
	}

	/** minimal constructor */
	public College(String collegeName) {
		this.collegeName = collegeName;
	}

	/** full constructor */
//	public College(String collegeName, Set<Specialty> specialties) {
//		this.collegeName = collegeName;
//		this.specialties = specialties;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "collegeID", unique = true, nullable = false)
	public Long getCollegeId() {
		return this.collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	@Column(name = "collegeName", nullable = false, length = 30)
	public String getCollegeName() {
		return this.collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
}