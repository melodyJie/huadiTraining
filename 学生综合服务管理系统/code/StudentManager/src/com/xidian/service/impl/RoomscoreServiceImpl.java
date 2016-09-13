package com.xidian.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xidian.dao.RoomscoreDAO;
import com.xidian.data.RoomscoreData;
import com.xidian.entity.Room;
import com.xidian.entity.Roomscore;
import com.xidian.service.RoomscoreService;

public class RoomscoreServiceImpl implements RoomscoreService {
	private RoomscoreDAO roomscoreDAO;

	public void setRoomscoreDAO(RoomscoreDAO roomscoreDAO) {
		this.roomscoreDAO = roomscoreDAO;
	}

	@Override
	public List<Roomscore> queryScore(Room room, int page, int offset) {
		List<Roomscore> list = roomscoreDAO.findByProperty("room", room, page,
				offset);
		return list;
	}

	@Override
	public List<Roomscore> queryAllScore(List<Room> list, String scoreDate,
			int page, int offset) throws ParseException {
		Date date = null;
		if (!scoreDate.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(scoreDate);
		}
		List<Roomscore> list2 = roomscoreDAO.findByDivide(list, date, page, offset);
		return list2;
	}

	@Override
	public void updateRoomscore(RoomscoreData roomscoreData) {
		Roomscore roomscore = roomscoreDAO.findByProperty("roomgradeId",
				roomscoreData.getRoomscoreId(), 1, Integer.MAX_VALUE).get(0);
		roomscore.setScore(roomscoreData.getScore());
		roomscoreDAO.attachDirty(roomscore);
	}

	@Override
	public List<Room> queryNoScoreRoom(List<Room> list, String scoreDate)
			throws ParseException {
		Date date = null;
		if (!scoreDate.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(scoreDate);
		}
		List<Roomscore> list2 = roomscoreDAO.findByDivide(list, date, 1,
				Integer.MAX_VALUE);
		List<Room> list3 = new ArrayList<Room>();
		if (null != list2 && list2.size() > 0) {
			for (Roomscore roomscore : list2) {
				list3.add(roomscore.getRoom());
			}
		}
		return list3;
	}

	@Override
	public void save(Room room, RoomscoreData roomscoreData)
			throws ParseException {
		Date date = null;
		if (!roomscoreData.getScoreDate().equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(roomscoreData.getScoreDate());
		}
		Roomscore roomscore = new Roomscore();
		roomscore.setRoom(room);
		roomscore.setScore(roomscoreData.getScore());
		roomscore.setScoreDate(date);
		roomscoreDAO.save(roomscore);
	}
}
