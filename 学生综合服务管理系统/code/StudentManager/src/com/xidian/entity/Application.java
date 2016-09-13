package com.xidian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xidian.dao.ApplicationDAO;

/**
 * Application entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "application", catalog = "studentmanage")
public class Application implements java.io.Serializable {

	// Fields

	private Long applicationId;
	private Student student;
	private String reason;
	private Date beginDate;
	private Date endDate;
	private String state;

	// Constructors

	/** default constructor */
	public Application() {
	}

	/** minimal constructor */
	public Application(String reason, Date beginDate, Date endDate, String state) {
		this.reason = reason;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.state = state;
	}

	/** full constructor */
	public Application(Student student, String reason, Date beginDate, Date endDate,
			String state) {
		this.student = student;
		this.reason = reason;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "applicationID", unique = true, nullable = false)
	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentID")
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Column(name = "reason", nullable = false, length = 50)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "beginDate", nullable = false, length = 10)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "endDate", nullable = false, length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "state", nullable = false, length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
}