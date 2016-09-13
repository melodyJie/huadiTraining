package com.xidian.service;

import java.util.List;

import com.xidian.entity.Building;
import com.xidian.entity.Room;
import com.xidian.entity.Student;

public interface RoomService {

	/**
	 * 查询新生可选宿舍
	 * @param student 
	 * @return
	 */
	List<Room> queryRooms(Student student);

	/**
	 * 查询宿舍
	 * @param room
	 * @return
	 */
	Room findByRoom(Room room);

	/**
	 * 根据楼栋查询宿舍
	 * @param building
	 * @return
	 */
	List<Room> findByBuilding(Building building);

	/**
	 * 查询所有宿舍
	 * @return
	 */
	List<Room> findAll();

	/**
	 * 查询没有评分的宿舍
	 * @param list2
	 * @return
	 */
	List<Room> queryNoScoreRoom(List<Room> list2, Building building);

	/**
	 * 根据id查询宿舍
	 * @param roomId
	 * @return
	 */
	Room findById(Long roomId);

	/**
	 * 更新宿舍信息
	 * @param room
	 */
	void updateRoom(Room room);
	
}
