package com.xidian.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Fee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fee", catalog = "studentmanage")
public class Fee implements java.io.Serializable {

	// Fields

	private Long feeId;
	private String type;
	private String feeName;
	private java.util.Date publishDate;
	private String state;

	// Constructors

	/** default constructor */
	public Fee() {
	}

	/** full constructor */
//	public Fee(String type, String feeName, Date publishDate, Set<Feetype> feetypes) {
//		this.type = type;
//		this.feeName = feeName;
//		this.publishDate = publishDate;
//		this.feetypes = feetypes;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feeID", unique = true, nullable = false)
	public Long getFeeId() {
		return this.feeId;
	}

	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}

	@Column(name = "type", nullable = false, length = 30)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "feeName", nullable = false, length = 30)
	public String getFeeName() {
		return this.feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publishDate", nullable = false, length = 10)
	public java.util.Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(java.util.Date publishDate) {
		this.publishDate = publishDate;
	}
	
	@Column(name = "state", nullable = false, length = 10)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}