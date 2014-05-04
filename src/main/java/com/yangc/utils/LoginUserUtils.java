package com.yangc.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.yangc.system.bean.oracle.TSysUser;

public class LoginUserUtils {

	/**
	 * @功能: 获取登录用户
	 * @作者: yangc
	 * @创建日期: 2013年12月16日 下午12:04:42
	 * @return
	 */
	public static TSysUser getLoginUser() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		TSysUser user = (TSysUser) session.getAttribute(ParamUtils.LOGIN_USER);
		return user;
	}

}
