package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.NoticeDAO;
import com.xidian.entity.Notice;
import com.xidian.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	private NoticeDAO noticeDAO;

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	@Override
	public void updateNotice(Notice notice) {
		List<Notice> list = noticeDAO
				.findByProperty("title", notice.getTitle());
		if (null != list && list.size() > 0) {
			Notice notice2 = list.get(0);
			notice2.setContent(notice.getContent());
			noticeDAO.attachDirty(notice2);
		} else {
			noticeDAO.save(notice);
		}
	}

	@Override
	public String attainNotice(Notice notice) {
		List<Notice> list = noticeDAO
				.findByProperty("title", notice.getTitle());
		String notice2 = "";
		if (null != list && list.size() > 0) {
			notice2 = list.get(0).getContent();
		}
		return notice2;
	}
}
