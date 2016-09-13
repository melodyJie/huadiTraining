package com.xidian.data;


public class RoomscoreData {
	private Long roomscoreId;
	private String roomNumber;
	private Integer score;
	private String scoreDate;
	
	public void setRoomscoreId(Long roomscoreId) {
		this.roomscoreId = roomscoreId;
	}
	
	public Long getRoomscoreId() {
		return roomscoreId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getScoreDate() {
		return scoreDate;
	}

	public void setScoreDate(String scoreDate) {
		this.scoreDate = scoreDate;
	}

}
