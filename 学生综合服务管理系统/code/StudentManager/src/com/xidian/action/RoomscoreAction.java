package com.xidian.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.data.RoomscoreData;
import com.xidian.entity.Building;
import com.xidian.entity.Room;
import com.xidian.entity.Roomscore;
import com.xidian.entity.Student;
import com.xidian.service.BuildingService;
import com.xidian.service.RoomService;
import com.xidian.service.RoomscoreService;
import com.xidian.service.StudentService;
import com.xidian.util.JsonDateValueProcessor;

public class RoomscoreAction extends ActionSupport implements
		ModelDriven<RoomscoreData> {
	private RoomscoreData roomscoreData = new RoomscoreData();
	private RoomscoreService roomscoreService;
	private StudentService studentService;
	private BuildingService buildingService;
	private RoomService roomService;

	@Override
	public RoomscoreData getModel() {
		return this.roomscoreData;
	}

	public void setRoomscoreService(RoomscoreService roomscoreService) {
		this.roomscoreService = roomscoreService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	/**
	 * 查询某个宿舍评分记录
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryScoreNumber() throws IOException {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Student s = studentService.queryStudent(student).get(0);
		List<Roomscore> list = new ArrayList<Roomscore>();
		if (null != s.getRoom()) {
			list = roomscoreService.queryScore(s.getRoom(), 1,
					Integer.MAX_VALUE);
		}
		int count = list.size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询某个宿舍评分
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryScore() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Student s = studentService.queryStudent(student).get(0);
		List<Roomscore> list = new ArrayList<Roomscore>();
		if (null != s.getRoom()) {
			list = roomscoreService.queryScore(s.getRoom(), page, 10);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 查询全部宿舍评分记录数
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public String queryAllScoreNumber() throws ParseException, IOException {
		List<Room> list = new ArrayList<Room>();
		if (!roomscoreData.getRoomNumber().equals("")) {
			Building building = buildingService.findByProperty("buildingId",
					Long.parseLong(roomscoreData.getRoomNumber())).get(0);
			list = roomService.findByBuilding(building);
		} else {
			list = roomService.findAll();
		}
		int count = roomscoreService.queryAllScore(list,
				roomscoreData.getScoreDate(), 1, Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询全部宿舍评分
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public String queryAllScore() throws IOException, ParseException {
		List<Room> list = new ArrayList<Room>();
		if (!roomscoreData.getRoomNumber().equals("")) {
			Building building = buildingService.findByProperty("buildingId",
					Long.parseLong(roomscoreData.getRoomNumber())).get(0);
			list = roomService.findByBuilding(building);
		} else {
			list = roomService.findAll();
		}
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Roomscore> list2 = roomscoreService.queryAllScore(list,
				roomscoreData.getScoreDate(), page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(list2, jsonConfig);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 更新宿舍评分
	 * 
	 * @return
	 */
	public String updateRoomscore() {
		roomscoreService.updateRoomscore(roomscoreData);
		return NONE;
	}

	/**
	 * 查询没有评分的宿舍
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public String queryNoScoreRoom() throws ParseException, IOException {
		List<Room> list = new ArrayList<Room>();
		Building building = null;
		if (!roomscoreData.getRoomNumber().equals("")) {
			building = buildingService.findByProperty("buildingId",
					Long.parseLong(roomscoreData.getRoomNumber())).get(0);
			list = roomService.findByBuilding(building);
		} else {
			list = roomService.findAll();
		}
		List<Room> list2 = roomscoreService.queryNoScoreRoom(list,
				roomscoreData.getScoreDate());
		List<Room> list3 = roomService.queryNoScoreRoom(list2,
				building);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list3);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 保存宿舍评分
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String savaRoomscore() throws ParseException {
		Room room = roomService.findById(roomscoreData.getRoomscoreId());
		roomscoreService.save(room, roomscoreData);
		return NONE;
	}
}
