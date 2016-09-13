package com.xidian.service.impl;

import java.util.Date;
import java.util.List;

import com.xidian.dao.FeeDAO;
import com.xidian.entity.Fee;
import com.xidian.service.FeeService;

public class FeeServiceImpl implements FeeService {
	private FeeDAO feeDAO;

	public void setFeeDAO(FeeDAO feeDAO) {
		this.feeDAO = feeDAO;
	}

	@Override
	public List<Fee> queryFee(Fee fee, int page, int offset) {
		List<Fee> list = feeDAO.findByProperty("state", fee.getState(), page,
				offset);
		return list;
	}

	@Override
	public void addFee(Fee fee) {
		fee.setPublishDate(new Date());
		fee.setState("未发布");
		feeDAO.save(fee);
	}

	
	@Override
	public Fee findById(Long feeId) {
		return feeDAO.findByProperty("feeId", feeId, 1, Integer.MAX_VALUE).get(
				0);
	}

	@Override
	public String validateFee(Fee fee) {
		List<Fee> list = feeDAO.findByProperty("feeName", fee.getFeeName(), 1,
				Integer.MAX_VALUE);
		if (null != list && list.size() > 0) {
			return "费用已存在！！！";
		}
		return "";
	}

	@Override
	public void deleteFee(Fee fee) {
		feeDAO.delete(feeDAO.findByProperty("feeId", fee.getFeeId(), 1,
				Integer.MAX_VALUE).get(0));
	}

	@Override
	public void updateFee(Fee fee) {
		Fee f = feeDAO.findByProperty("feeId", fee.getFeeId(), 1,
				Integer.MAX_VALUE).get(0);
		f.setState("已发布");
		feeDAO.attachDirty(f);
	}

}
