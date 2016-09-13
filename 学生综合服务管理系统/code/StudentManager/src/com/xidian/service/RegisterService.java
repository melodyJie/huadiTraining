package com.xidian.service;

import com.xidian.entity.Register;

public interface RegisterService {

	/**
	 * 获取新生须知
	 * @return
	 */
	String attainNotice();

	/**
	 * 修改新生须知
	 * @param register
	 */
	void updateNotice(Register register);

}
