package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.RegisterDAO;
import com.xidian.entity.Register;
import com.xidian.service.RegisterService;

public class RegisterServiceImpl implements RegisterService {
	private RegisterDAO registerDAO;
	
	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}

	@Override
	public String attainNotice() {
		List list = registerDAO.findAll();
		String notice = null;
		if (null != list && list.size() > 0){
			Register register = (Register) list.get(0);
			notice = register.getNotice();
		}
		return notice;
	}

	@Override
	public void updateNotice(Register register) {
		Register register2 = (Register) registerDAO.findAll().get(0);
		register2.setNotice(register.getNotice());
		registerDAO.attachDirty(register2);
	}
}
