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
 * Roomscore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roomscore", catalog = "studentmanage")
public class Roomscore implements java.io.Serializable {

	// Fields

	private Long roomgradeId;
	private Manager manager;
	private Room room;
	private Integer score;
	private Date scoreDate;

	// Constructors

	/** default constructor */
	public Roomscore() {
	}

	/** minimal constructor */
	public Roomscore(Integer score, Date scoreDate) {
		this.score = score;
		this.scoreDate = scoreDate;
	}

	/** full constructor */
	public Roomscore(Manager manager, Room room, Integer score, Date scoreDate) {
		this.manager = manager;
		this.room = room;
		this.score = score;
		this.scoreDate = scoreDate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "roomgradeID", unique = true, nullable = false)
	public Long getRoomgradeId() {
		return this.roomgradeId;
	}

	public void setRoomgradeId(Long roomgradeId) {
		this.roomgradeId = roomgradeId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "managerID")
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roomID")
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column(name = "score", nullable = false)
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "scoreDate", nullable = false, length = 10)
	public Date getScoreDate() {
		return this.scoreDate;
	}

	public void setScoreDate(Date scoreDate) {
		this.scoreDate = scoreDate;
	}

}