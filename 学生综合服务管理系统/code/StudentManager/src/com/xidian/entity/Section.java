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
 * Section entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "section", catalog = "studentmanage")
public class Section implements java.io.Serializable {

	// Fields

	private Long sectionId;
	private Manager manager;
	private String campus;
	private String phone;

	// Constructors

	/** default constructor */
	public Section() {
	}

	/** minimal constructor */
	public Section(String phone) {
		this.phone = phone;
	}

	/** full constructor */
	public Section(Manager manager, String campus, String phone,
			Set<Building> buildings) {
		this.manager = manager;
		this.campus = campus;
		this.phone = phone;
		//this.buildings = buildings;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sectionID", unique = true, nullable = false)
	public Long getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "managerID")
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Column(name = "campus", length = 20)
	public String getCampus() {
		return this.campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	@Column(name = "phone", nullable = false, length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}