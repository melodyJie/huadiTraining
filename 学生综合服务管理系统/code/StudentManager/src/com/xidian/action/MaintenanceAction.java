package com.xidian.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Building;
import com.xidian.entity.Maintenance;
import com.xidian.entity.Room;
import com.xidian.entity.Student;
import com.xidian.service.BuildingService;
import com.xidian.service.MaintenanceService;
import com.xidian.service.RoomService;
import com.xidian.service.StudentService;
import com.xidian.util.JsonDateValueProcessor;

public class MaintenanceAction extends ActionSupport implements
		ModelDriven<Maintenance> {
	private Maintenance maintenance = new Maintenance();
	private MaintenanceService maintenanceService;
	private BuildingService buildingService;
	private RoomService roomService;
	private StudentService studentService;

	@Override
	public Maintenance getModel() {
		return this.maintenance;
	}

	public void setMaintenanceService(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * 添加维护申请
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addMaintenance() throws IOException {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Student s = studentService.findByProperty("studentId",
				student.getStudentId()).get(0);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (null != s.getRoom()) {
			maintenance.setRoom(s.getRoom());
			maintenanceService.addMaintenance(maintenance);
			response.getWriter().println("提交成功！！！");
		} else {
			response.getWriter().println("宿舍不存在！！！");
		}
		return NONE;
	}

	/**
	 * 查看维修进度记录数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryMaintenanceNumber() throws IOException {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Student s = studentService.findByProperty("studentId",
				student.getStudentId()).get(0);
		int count = maintenanceService.queryMaintenance(s, 1, Integer.MAX_VALUE)
				.size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查看维修进度
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryMaintenance() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Student s = studentService.findByProperty("studentId",
				student.getStudentId()).get(0);
		List<Maintenance> list = maintenanceService.queryMaintenance(s, page,
				10);
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
	 * 取消维护
	 * 
	 * @return
	 * @throws IOException
	 */
	public String cancelMaintenance() throws IOException {
		maintenanceService.cancelMaintenance(maintenance);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("取消成功！！！");
		return NONE;
	}

	/**
	 * 所有维修记录数
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String queryAllMaintenanceNumber() throws IOException {
		List<Room> list = new ArrayList<Room>();
		if (!maintenance.getNote().equals("")) {
			Room room = roomService.findById(Long.parseLong(maintenance
					.getNote()));
			list.add(room);
		} else if (!maintenance.getHandle().equals("")) {
			Building building = buildingService.findByProperty("buildingId",
					Long.parseLong(maintenance.getHandle())).get(0);
			list = roomService.findByBuilding(building);
		} else {
			list = roomService.findAll();
		}
		String[] states = maintenance.getState().split(" ");
		int count = maintenanceService.queryAllMaintenance(list, states, 1,
				Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 所有维修记录
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllMaintenance() throws IOException {
		List<Room> list = new ArrayList<Room>();
		if (!maintenance.getNote().equals("")) {
			Room room = roomService.findById(Long.parseLong(maintenance
					.getNote()));
			list.add(room);
		} else if (!maintenance.getHandle().equals("")) {
			Building building = buildingService.findByProperty("buildingId",
					Long.parseLong(maintenance.getHandle())).get(0);
			list = roomService.findByBuilding(building);
		} else {
			list = roomService.findAll();
		}
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		String[] states = maintenance.getState().split(" ");
		List<Maintenance> list2 = maintenanceService.queryAllMaintenance(list,
				states, page, 10);
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
	 * 更新维修信息
	 * 
	 * @return
	 */
	public String updateMaintenance() {
		maintenanceService.updateMaintenance(this.maintenance);
		return NONE;
	}

}
