package com.xidian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Register entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "register", catalog = "studentmanage")
public class Register implements java.io.Serializable {

	// Fields

	private Long registerId;
	private Date registerDate;
	private String notice;

	// Constructors

	/** default constructor */
	public Register() {
	}

	/** full constructor */
	public Register(Date registerDate) {
		this.registerDate = registerDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "registerID", unique = true, nullable = false)
	public Long getRegisterId() {
		return this.registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "registerDate", nullable = false, length = 10)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	@Column(name = "notice", nullable = false, length = 20000)
	public String getNotice() {
		return this.notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

}