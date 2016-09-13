package com.xidian.service;

import com.xidian.entity.Notice;

public interface NoticeService {

	/**
	 * 修改公告
	 */
	void updateNotice(Notice notice);

	/**
	 * 获取公告
	 * @param notice 
	 * @return
	 */
	String attainNotice(Notice notice);

}
