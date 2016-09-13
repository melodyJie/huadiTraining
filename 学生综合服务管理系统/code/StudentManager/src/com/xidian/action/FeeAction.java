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
import com.xidian.entity.Fee;
import com.xidian.service.FeeService;
import com.xidian.service.FeetypeService;
import com.xidian.service.PayService;
import com.xidian.util.JsonDateValueProcessor;

public class FeeAction extends ActionSupport implements ModelDriven<Fee> {
	private Fee fee = new Fee();
	private FeeService feeService;
	private PayService payService;
	private FeetypeService feetypeService;

	public void setFeeService(FeeService feeService) {
		this.feeService = feeService;
	}

	public void setPayService(PayService payService) {
		this.payService = payService;
	}
	
	public void setFeetypeService(FeetypeService feetypeService) {
		this.feetypeService = feetypeService;
	}

	@Override
	public Fee getModel() {
		return this.fee;
	}

	/**
	 * 查询费用记录数
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String queryFeeNumber() throws IOException {
		int count = feeService.queryFee(this.fee, 1, Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询费用
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryFee() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Fee> list = feeService.queryFee(this.fee, page, 10);
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
	 * 添加费用
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addFee() throws IOException {
		feeService.addFee(this.fee);
		return NONE;
	}

	/**
	 * 验证费用是否存在
	 * 
	 * @return
	 * @throws IOException
	 */
	public String validateFee() throws IOException {
		String message = feeService.validateFee(this.fee);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
		return NONE;
	}

	/**
	 * 删除费用
	 * 
	 * @return
	 */
	public String deleteFee() {
		feetypeService.deleteFeetype(this.fee);
		feeService.deleteFee(this.fee);
		return NONE;
	}

	/**
	 * 更新费用
	 * 
	 * @return
	 */
	public String updateFee() {
		feeService.updateFee(this.fee);
		return NONE;
	}

}
