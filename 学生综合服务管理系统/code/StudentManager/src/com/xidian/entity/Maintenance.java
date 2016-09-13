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
 * Maintenance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "maintenance", catalog = "studentmanage")
public class Maintenance implements java.io.Serializable {

	// Fields

	private Long maintenanceId;
	private Room room;
	private String handle;
	private String note;
	private String state;
	private Date maintenanceDate;

	// Constructors

	/** default constructor */
	public Maintenance() {
	}

	/** minimal constructor */
	public Maintenance(String handle) {
		this.handle = handle;
	}

	/** full constructor */
	public Maintenance(Room room, String handle, String note, String state,
			Date maintenanceDate) {
		this.room = room;
		this.handle = handle;
		this.note = note;
		this.state = state;
		this.maintenanceDate = maintenanceDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "maintenanceID", unique = true, nullable = false)
	public Long getMaintenanceId() {
		return this.maintenanceId;
	}

	public void setMaintenanceId(Long maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roomID")
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "handle", nullable = false, length = 20)
	public String getHandle() {
		return this.handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	@Column(name = "note", length = 50)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Column(name = "state", nullable = false, length = 20)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "maintenanceDate", nullable = false, length = 10)
	public Date getMaintenanceDate() {
		return this.maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

}