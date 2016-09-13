package com.xidian.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Feetype;
import com.xidian.entity.Pay;
import com.xidian.service.FeetypeService;
import com.xidian.service.PayService;
import com.xidian.util.JsonDateValueProcessor;

public class PayAction extends ActionSupport implements ModelDriven<Pay> {
	private Pay pay = new Pay();
	private PayService payService;
	private FeetypeService feetypeService;

	@Override
	public Pay getModel() {
		return this.pay;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	public void setFeetypeService(FeetypeService feetypeService) {
		this.feetypeService = feetypeService;
	}

	/**
	 * 查询缴费记录
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryPay() throws IOException {
		List<Pay> list = payService.queryPay();
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
	 * 添加缴费记录
	 * 
	 * @return
	 */
	public String addPay() {
		Feetype feetype = feetypeService.findByProperty("feetypeId",
				this.pay.getPayId()).get(0);
		payService.addPay(feetype);
		return NONE;
	}
}
