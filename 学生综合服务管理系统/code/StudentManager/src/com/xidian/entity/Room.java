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
 * Room entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "room", catalog = "studentmanage")
public class Room implements java.io.Serializable {

	// Fields

	private Long roomId;
	private Building building;
	private String roomNumber;
	private String type;
	private Integer price;
	private String sex;
	private Integer available;
	private Integer total;

	// Constructors

	/** default constructor */
	public Room() {
	}

	/** minimal constructor */
	public Room(String roomNumber, String type, Integer price, String sex,
			Integer available, Integer total) {
		this.roomNumber = roomNumber;
		this.type = type;
		this.price = price;
		this.sex = sex;
		this.available = available;
		this.total = total;
	}

	/** full constructor */
//	public Room(Building building, String roomNumber, String type,
//			Integer price, String sex, Integer available, Integer total,
//			Set<Habitancy> habitancies, Set<Roomscore> roomscores,
//			Set<Move> movesForRooRoomId, Set<Student> students,
//			Set<Move> movesForRoomId, Set<Maintenance> maintenances) {
//		this.building = building;
//		this.roomNumber = roomNumber;
//		this.type = type;
//		this.price = price;
//		this.sex = sex;
//		this.available = available;
//		this.total = total;
//		this.habitancies = habitancies;
//		this.roomscores = roomscores;
//		this.movesForRooRoomId = movesForRooRoomId;
//		this.students = students;
//		this.movesForRoomId = movesForRoomId;
//		this.maintenances = maintenances;
//	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "roomID", unique = true, nullable = false)
	public Long getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buildingID")
	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	@Column(name = "roomNumber", nullable = false, length = 10)
	public String getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Column(name = "type", nullable = false, length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "sex", nullable = false, length = 5)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "available", nullable = false)
	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}	
	
	@Column(name = "total", nullable = false)
	public Integer getTotal() {
		return this.total;
	}
	
	public void setTotal(Integer total) {
		this.total = total;
	}
}