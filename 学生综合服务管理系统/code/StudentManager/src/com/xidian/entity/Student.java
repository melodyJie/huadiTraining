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
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student", catalog = "studentmanage")
public class Student implements java.io.Serializable {

	// Fields

	private Long studentId;
	private Studentclass studentclass;
	private Room room;
	private String name;
	private String identityId;
	private String studentNumber;
	private String sex;
	private String year;
	private String password;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String name, String identityId, String studentNumber,
			String sex, String year, String password) {
		this.name = name;
		this.identityId = identityId;
		this.studentNumber = studentNumber;
		this.sex = sex;
		this.year = year;
		this.password = password;
	}

	/** full constructor */
//	public Student(Studentclass studentclass, Room room, String name,
//			String identityId, String studentNumber, String sex, String year,
//			String password, Set<Move> moves, Set<Health> healths,
//			Set<Rewards> rewardses, Set<Habitancy> habitancies,
//			Set<Punishment> punishments, Set<Application> applications,
//			Set<Pay> paies, Set<Receive> receives, Set<Choosing> choosings,
//			Set<Achievement> achievements) {
//		this.studentclass = studentclass;
//		this.room = room;
//		this.name = name;
//		this.identityId = identityId;
//		this.studentNumber = studentNumber;
//		this.sex = sex;
//		this.year = year;
//		this.password = password;
//		this.moves = moves;
//		this.healths = healths;
//		this.rewardses = rewardses;
//		this.habitancies = habitancies;
//		this.punishments = punishments;
//		this.applications = applications;
//		this.paies = paies;
//		this.receives = receives;
//		this.choosings = choosings;
//		this.achievements = achievements;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "studentID", unique = true, nullable = false)
	public Long getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "classID")
	public Studentclass getStudentclass() {
		return this.studentclass;
	}

	public void setStudentclass(Studentclass studentclass) {
		this.studentclass = studentclass;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roomID")
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "identityID", nullable = false, length = 20)
	public String getIdentityId() {
		return this.identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	@Column(name = "studentNumber", nullable = false, length = 20)
	public String getStudentNumber() {
		return this.studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	@Column(name = "sex", nullable = false, length = 5)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "year", nullable = false, length = 5)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "password", nullable = false, length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}