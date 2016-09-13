package com.xidian.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Register;
import com.xidian.service.RegisterService;

public class RegisterAction extends ActionSupport implements ModelDriven<Register> {
	private Register register = new Register();
	private RegisterService registerService;
	
	@Override
	public Register getModel() {
		return this.register;
	}
	
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	/**
	 * 获取新生须知
	 * @return
	 * @throws IOException 
	 */
	public String attainNotice() throws IOException{
		String notice = registerService.attainNotice();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(notice);
		return NONE;
	}
	
	/**
	 * 添加或修改新生须知
	 * @return
	 * @throws IOException 
	 */
	public String addNotice() throws IOException{
		registerService.updateNotice(register);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("修改成功！！！");
		return NONE;
	}

}
