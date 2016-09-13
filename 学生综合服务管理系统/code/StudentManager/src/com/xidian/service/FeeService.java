package com.xidian.service;

import java.util.List;

import com.xidian.entity.Fee;

public interface FeeService {
	
	/**
	 * 查询费用
	 * @param fee
	 * @param offset 
	 * @param page 
	 * @return
	 */
	List<Fee> queryFee(Fee fee, int page, int offset);

	/**
	 * 添加费用
	 * @param fee
	 */
	void addFee(Fee fee);
	
	/**
	 * 根据id查询费用
	 * @param feeId
	 * @return
	 */
	Fee findById(Long feeId);

	/**
	 * 验证费用是否存在
	 * @param fee
	 * @return
	 */
	String validateFee(Fee fee);

	/**
	 * 删除费用
	 * @param fee
	 */
	void deleteFee(Fee fee);

	/**
	 * 更新费用
	 * @param fee
	 */
	void updateFee(Fee fee);
}
