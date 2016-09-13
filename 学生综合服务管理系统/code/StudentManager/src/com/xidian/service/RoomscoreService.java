package com.xidian.service;

import java.text.ParseException;
import java.util.List;

import com.xidian.data.RoomscoreData;
import com.xidian.entity.Room;
import com.xidian.entity.Roomscore;

public interface RoomscoreService {

	/**
	 * 查询宿舍评分
	 * 
	 * @param room
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Roomscore> queryScore(Room room, int page, int offset);

	/**
	 * 根据宿舍和日期查询宿舍评分
	 * 
	 * @param list
	 * @param scoreDate
	 * @param offset
	 * @param page
	 * @return
	 * @throws ParseException
	 */
	List<Roomscore> queryAllScore(List<Room> list, String scoreDate,
			int page, int offset) throws ParseException;

	/**
	 * 更新宿舍评分
	 * 
	 * @param roomscoreData
	 */
	void updateRoomscore(RoomscoreData roomscoreData);

	/**
	 * 查询没有评分的宿舍
	 * 
	 * @param list
	 * @param scoreDate
	 * @return
	 * @throws ParseException
	 */
	List<Room> queryNoScoreRoom(List<Room> list, String scoreDate)
			throws ParseException;

	/**
	 * 保存宿舍评分
	 * 
	 * @param room
	 * @param roomscoreData
	 * @throws ParseException
	 */
	void save(Room room, RoomscoreData roomscoreData) throws ParseException;

}
