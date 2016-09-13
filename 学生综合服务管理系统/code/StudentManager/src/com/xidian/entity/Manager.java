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
 * Manager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "manager", catalog = "studentmanage")
public class Manager implements java.io.Serializable {

	// Fields

	private Long managerId;
	private String username;
	private String managerName;
	private String password;

	// Constructors

	/** default constructor */
	public Manager() {
	}

	/** minimal constructor */
	public Manager(String username, String managerName, String password) {
		this.username = username;
		this.managerName = managerName;
		this.password = password;
	}

	/** full constructor */
	public Manager(String username, String managerName, String password,
			Set<Roomscore> roomscores, Set<Notice> notices,
			Set<Section> sections) {
		this.username = username;
		this.managerName = managerName;
		this.password = password;
//		this.roomscores = roomscores;
//		this.notices = notices;
//		this.sections = sections;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "managerID", unique = true, nullable = false)
	public Long getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	
	@Column(name = "username", nullable = false, length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "managerName", nullable = false, length = 30)
	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Column(name = "password", nullable = false, length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}