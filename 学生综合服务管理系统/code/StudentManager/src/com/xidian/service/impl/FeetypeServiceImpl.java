package com.xidian.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xidian.dao.FeeDAO;
import com.xidian.dao.FeetypeDAO;
import com.xidian.data.Data;
import com.xidian.entity.College;
import com.xidian.entity.Fee;
import com.xidian.entity.Feetype;
import com.xidian.entity.Pay;
import com.xidian.entity.Specialty;
import com.xidian.entity.Student;
import com.xidian.entity.Studentclass;
import com.xidian.service.FeetypeService;
import com.xidian.service.PayService;

public class FeetypeServiceImpl implements FeetypeService {
	private FeetypeDAO feetypeDAO;
	private FeeDAO feeDAO;
	private PayService payService;

	public void setFeetypeDAO(FeetypeDAO feetypeDAO) {
		this.feetypeDAO = feetypeDAO;
	}

	public void setFeeDAO(FeeDAO feeDAO) {
		this.feeDAO = feeDAO;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	@Override
	public List<Data> queryFeetype(Fee fee, int page, int offset) {
		List<Feetype> list = feetypeDAO
				.findByProperty("fee", fee, page, offset);
		String[] types = fee.getType().substring(1).split("\\+");
		List<Data> list2 = new ArrayList<Data>();
		for (Feetype feetype : list) {
			Data data = new Data();
			data.setId(this.getType(feetype, types[0]));
			if (types.length > 1) {
				data.setType(this.getType(feetype, types[1]));
			}
			data.setValue(feetype.getValue().toString());
			list2.add(data);
		}
		return list2;
	}

	@Override
	public List<Feetype> findByProperties(List<String> propertyNames,
			List<Object> values) {
		return feetypeDAO.findByProperties(propertyNames, values);
	}

	@Override
	public void addFeetype(Feetype feetype2) {
		feetypeDAO.save(feetype2);
	}

	@Override
	public List<Feetype> queryStudentFeetype(String value) {
		List<Pay> pays = new ArrayList<Pay>();
		List<Fee> list = feeDAO.findByProperty("state", "已发布", 1,
				Integer.MAX_VALUE);
		List<Feetype> list2 = new ArrayList<Feetype>();
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		if (value.equals("0")) {
			pays = payService.findPay(student);
		}
		List<String> propertyNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		for (Fee fee : list) {
			String[] types = fee.getType().substring(1).split("\\+");
			propertyNames.add("fee");
			values.add(fee);
			for (int i = 0; i < types.length; i++) {
				if (types[i].equals("学院")) {
					College college = student.getStudentclass().getSpecialty()
							.getCollege();
					propertyNames.add("college");
					values.add(college);
				} else if (types[i].equals("专业")) {
					Specialty specialty = student.getStudentclass()
							.getSpecialty();
					propertyNames.add("specialty");
					values.add(specialty);
				} else if (types[i].equals("班级")) {
					Studentclass studentclass = student.getStudentclass();
					propertyNames.add("studentclass");
					values.add(studentclass);
				} else {
					int year = Integer.parseInt(student.getYear());
					Calendar calendar = Calendar.getInstance();
					int month = calendar.get(Calendar.MONTH) + 1;
					int nowYear = calendar.get(Calendar.YEAR);
					int grade = month >= 9 ? (nowYear - year + 1)
							: (nowYear - year);
					propertyNames.add("grade");
					values.add(grade);
				}
			}
			List<Feetype> feetypes = feetypeDAO.findByProperties(propertyNames,
					values);
			if (null != feetypes && feetypes.size() > 0) {
				boolean flag = true;
				if (null != pays && pays.size() > 0) {
					for (Pay pay : pays) {
						if (pay.getFeetype().getFeetypeId() == feetypes.get(0)
								.getFeetypeId()) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					list2.add(feetypes.get(0));
				}
			}
			propertyNames.clear();
			values.clear();
		}
		return list2;
	}

	private String getType(Feetype feetype, String type) {
		String str = null;
		if (type.equals("学院")) {
			str = feetype.getCollege().getCollegeName();
		} else if (type.equals("专业")) {
			str = feetype.getSpecialty().getSpecialtyName();
		} else if (type.equals("班级")) {
			str = feetype.getStudentclass().getClassNumber();
		} else {
			str = feetype.getGrade().toString();
		}
		return str;
	}

	@Override
	public List<Feetype> findByProperty(String propertyName, Object value) {
		return feetypeDAO.findByProperty(propertyName, value, 1,
				Integer.MAX_VALUE);
	}

	@Override
	public void deleteFeetype(Fee fee) {
		feetypeDAO.deleteFeetype(fee);
	}
}
