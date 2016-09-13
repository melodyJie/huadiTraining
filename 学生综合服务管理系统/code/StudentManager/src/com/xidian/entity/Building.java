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
 * Building entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "building", catalog = "studentmanage")
public class Building implements java.io.Serializable {

	// Fields

	private Long buildingId;
	private Section section;
	private String buildingName;

	// Constructors

	/** default constructor */
	public Building() {
	}

	/** minimal constructor */
	public Building(String buildingName) {
		this.buildingName = buildingName;
	}

	/** full constructor */
	public Building(Section section, String buildingName) {
		this.section = section;
		this.buildingName = buildingName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "buildingID", unique = true, nullable = false)
	public Long getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sectionID")
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@Column(name = "buildingName", nullable = false, length = 20)
	public String getBuildingName() {
		return this.buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

}