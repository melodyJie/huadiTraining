package com.xidian.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.data.Data;
import com.xidian.entity.College;
import com.xidian.entity.Fee;
import com.xidian.entity.Feetype;
import com.xidian.entity.Specialty;
import com.xidian.entity.Studentclass;
import com.xidian.service.CollegeService;
import com.xidian.service.FeeService;
import com.xidian.service.FeetypeService;
import com.xidian.service.SpecialtyService;
import com.xidian.service.StudentclassService;
import com.xidian.util.JsonDateValueProcessor;

public class FeetypeAction extends ActionSupport implements
		ModelDriven<Feetype> {
	private Feetype feetype = new Feetype();
	private FeetypeService feetypeService;
	private FeeService feeService;
	private CollegeService collegeService;
	private SpecialtyService specialtyService;
	private StudentclassService studentclassService;

	@Override
	public Feetype getModel() {
		return this.feetype;
	}

	public void setFeetypeService(FeetypeService feetypeService) {
		this.feetypeService = feetypeService;
	}

	public void setFeeService(FeeService feeService) {
		this.feeService = feeService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	public void setSpecialtyService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	public void setStudentclassService(StudentclassService studentclassService) {
		this.studentclassService = studentclassService;
	}

	/**
	 * 查询费用类型记录数
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String queryFeetypeNumber() throws IOException {
		Fee fee = feeService.findById(feetype.getFeetypeId());
		int count = feetypeService.queryFeetype(fee, 1, Integer.MAX_VALUE)
				.size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询费用类型
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryFeetype() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		Fee fee = feeService.findById(feetype.getFeetypeId());
		List<Data> list = feetypeService.queryFeetype(fee, page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 添加费用类型
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addFeetype() throws IOException {
		Fee fee = feeService.findById(feetype.getFeetypeId());
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] types = fee.getType().substring(1).split("\\+");
		Feetype feetype2 = new Feetype();
		feetype2.setFee(fee);
		List<String> propertyNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		propertyNames.add("fee");
		values.add(fee);
		for (int i = 0; i < types.length; i++) {
			if (types[i].equals("学院")) {
				Long collegeId = Long.parseLong(request
						.getParameter("collegeId"));
				College college = collegeService.findById(collegeId);
				feetype2.setCollege(college);
				propertyNames.add("college");
				values.add(college);
			} else if (types[i].equals("专业")) {
				Long specialtyId = Long.parseLong(request
						.getParameter("specialtyId"));
				Specialty specialty = specialtyService.findById(specialtyId);
				feetype2.setSpecialty(specialty);
				propertyNames.add("specialty");
				values.add(specialty);
			} else if (types[i].equals("班级")) {
				Long classId = Long.parseLong(request
						.getParameter("studentclassId"));
				Studentclass studentclass = studentclassService.findByProperty(
						"classId", classId).get(0);
				feetype2.setStudentclass(studentclass);
				propertyNames.add("studentclass");
				values.add(studentclass);
			} else {
				int grade = Integer.parseInt(request.getParameter("grade"));
				feetype2.setGrade(grade);
				propertyNames.add("grade");
				values.add(grade);
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		List<Feetype> list = feetypeService.findByProperties(propertyNames,
				values);
		if (null != list && list.size() > 0) {
			response.getWriter().println("费用类型已存在！！！");
		} else {
			feetype2.setValue(feetype.getValue());
			feetypeService.addFeetype(feetype2);
			response.getWriter().print("");
		}
		return NONE;
	}

	/**
	 * 查询学生应交费用
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryStudentFeetype() throws IOException {
		String value = ServletActionContext.getRequest().getParameter("value");
		List<Feetype> list = feetypeService.queryStudentFeetype(value);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		response.getWriter().println(jsonArray);
		return NONE;
	}

}
