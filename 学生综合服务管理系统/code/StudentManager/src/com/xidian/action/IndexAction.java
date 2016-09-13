package com.xidian.action;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

	/**
	 * 转到主页
	 */
	public String execute() {
		return "index";
	}
}
