package com.xidian.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.data.Data;
import com.xidian.entity.Building;
import com.xidian.entity.Room;
import com.xidian.entity.Student;
import com.xidian.service.BuildingService;
import com.xidian.service.RoomService;
import com.xidian.service.StudentService;

public class RoomAction extends ActionSupport implements ModelDriven<Room> {
	private Room room = new Room();
	private RoomService roomService;
	private StudentService studentService;
	private BuildingService buildingService;

	@Override
	public Room getModel() {
		return this.room;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	/**
	 * 查询新生可选宿舍
	 * 
	 * @return
	 * @throws IOException
	 */
	public String query() throws IOException {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Student s = studentService.findByProperty("studentId",
				student.getStudentId()).get(0);
		List<Room> list = roomService.queryRooms(s);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().print(jsonArray);
		return NONE;
	}

	/**
	 * 新生选择宿舍
	 * 
	 * @return
	 * @throws IOException
	 */
	public String chooseRoom() throws IOException {
		Room r = roomService.findById(this.room.getRoomId());
		r.setAvailable(r.getAvailable() + 1);
		roomService.updateRoom(r);
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Student s = studentService.findByProperty("studentId",
				student.getStudentId()).get(0);
		s.setRoom(r);
		studentService.update(s);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("选择成功！！！");
		return NONE;
	}

	/**
	 * 根据楼栋查询宿舍
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryRooms() throws IOException {
		Building building = buildingService.findByProperty("buildingId",
				Long.parseLong(room.getRoomNumber())).get(0);
		List<Room> list = roomService.findByBuilding(building);
		List<Data> list2 = new ArrayList<Data>();
		for (Room room : list) {
			Data data = new Data();
			data.setId(room.getRoomId().toString());
			data.setValue(room.getRoomNumber());
			list2.add(data);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list2);
		response.getWriter().print(jsonArray);
		return NONE;
	}

}
