package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.RoomDAO;
import com.xidian.entity.Building;
import com.xidian.entity.Room;
import com.xidian.entity.Student;
import com.xidian.service.RoomService;

public class RoomServiceImpl implements RoomService {
	private RoomDAO roomDAO;

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	@Override
	public List<Room> queryRooms(Student student) {
		if (null != student.getRoom()) {
			return null;
		}
		List<Room> list = roomDAO.findByProperty("type", student
				.getStudentclass().getSpecialty().getSpecialtyId().toString());
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getTotal() - list.get(i).getAvailable() == 0) {
					list.remove(i);
				}
			}
			return list;
		}
		return null;
	}

	@Override
	public Room findByRoom(Room room) {
		Room r = roomDAO.findByExample(room).get(0);
		r.setAvailable(r.getAvailable() + 1);
		roomDAO.attachDirty(r);
		return r;
	}

	@Override
	public List<Room> findByBuilding(Building building) {
		return roomDAO.findByProperty("building", building);
	}

	@Override
	public List<Room> findAll() {
		return roomDAO.findAll();
	}

	@Override
	public List<Room> queryNoScoreRoom(List<Room> list2, Building building) {
		List<Room> list = roomDAO.findByDivide(list2, building, 1,
				Integer.MAX_VALUE);
		return list;
	}

	@Override
	public Room findById(Long roomId) {
		return roomDAO.findByProperty("roomId", roomId).get(0);
	}

	@Override
	public void updateRoom(Room room) {
		roomDAO.attachDirty(room);
	}
}
