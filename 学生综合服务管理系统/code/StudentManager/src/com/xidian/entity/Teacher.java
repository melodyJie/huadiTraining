package com.xidian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.PseudoColumnUsage;
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
 * Teacher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher", catalog = "studentmanage")
public class Teacher implements java.io.Serializable {

	// Fields

	private Long teacherId;
	private String teacherName;
	private String teacherNumber;
	private String password;
	private String identityId;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(String teacherName, String teacherNumber, String password,
			String identityId) {
		this.teacherName = teacherName;
		this.teacherNumber = teacherNumber;
		this.password = password;
		this.identityId = identityId;
	}

	/** full constructor */
//	public Teacher(String teacherName, String teacherNumber, String psaaword,
//			String identityId, Set<Receive> receives, Set<Teach> teachs,
//			Set<Achievement> achievements) {
//		this.teacherName = teacherName;
//		this.teacherNumber = teacherNumber;
//		this.password = password;
//		this.identityId = identityId;
//		this.receives = receives;
//		this.teachs = teachs;
//		this.achievements = achievements;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "teacherID", unique = true, nullable = false)
	public Long getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name = "teacherName", nullable = false, length = 30)
	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Column(name = "teacherNumber", nullable = false, length = 20)
	public String getTeacherNumber() {
		return this.teacherNumber;
	}

	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	@Column(name = "password", nullable = false, length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "identityID", nullable = false, length = 20)
	public String getIdentityId() {
		return this.identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}
}