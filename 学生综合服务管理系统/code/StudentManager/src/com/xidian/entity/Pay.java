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

/**
 * Pay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pay", catalog = "studentmanage")
public class Pay implements java.io.Serializable {

	// Fields

	private Long payId;
	private Student student;
	private Feetype feetype;
	private Integer value;
	private Date payDate;

	// Constructors

	/** default constructor */
	public Pay() {
	}

	/** minimal constructor */
	public Pay(Long payId) {
		this.payId = payId;
	}

	/** full constructor */
	public Pay(Long payId, Student student, Feetype feetype, Integer value,
			Date payDate) {
		this.payId = payId;
		this.student = student;
		this.feetype = feetype;
		this.value = value;
		this.payDate = payDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "payID", unique = true, nullable = false)
	public Long getPayId() {
		return this.payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentID")
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "feetypeID")
	public Feetype getFeetype() {
		return this.feetype;
	}

	public void setFeetype(Feetype feetype) {
		this.feetype = feetype;
	}

	@Column(name = "value", nullable = false)
	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "payDate", nullable = false, length = 10)
	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

}