package com.xidian.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Building;
import com.xidian.service.BuildingService;

public class BuildingAction extends ActionSupport implements ModelDriven<Building> {
	private Building building = new Building();
	private BuildingService buildingService;
	
	@Override
	public Building getModel() {
		return this.building;
	}
	
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}
	
	/**
	 * 查询所有楼栋
	 * @return
	 * @throws IOException
	 */
	public String queryBuilding() throws IOException{
		List<Building> list = buildingService.queryBuilding();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

}
