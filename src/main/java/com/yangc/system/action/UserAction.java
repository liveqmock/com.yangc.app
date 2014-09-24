package com.yangc.system.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.yangc.bean.ResultBean;
import com.yangc.system.bean.oracle.TSysUser;
import com.yangc.system.service.UserService;
import com.yangc.utils.Constants;
import com.yangc.utils.JsonUtils;
import com.yangc.utils.LoginUserUtils;

public class UserAction {

	private static final Logger logger = Logger.getLogger(UserAction.class);

	private UserService userService;

	private String username;
	private String password;
	private String newPassword;

	/**
	 * @功能: 校验登录
	 * @作者: yangc
	 * @创建日期: 2012-9-10 上午12:04:21
	 * @return
	 */
	public String login() throws IOException {
		logger.info("login - username=" + username + ", password=" + password);
		ResultBean resultBean = new ResultBean();
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			resultBean.setSuccess(false);
			resultBean.setMessage("用户名或密码不能为空");
		} else {
			List<TSysUser> users = this.userService.getUserListByUsernameAndPassword(username, password);
			if (null == users || users.isEmpty()) {
				resultBean.setSuccess(false);
				resultBean.setMessage("用户不存在");
			} else if (users.size() > 1) {
				resultBean.setSuccess(false);
				resultBean.setMessage("用户重复");
			} else {
				TSysUser user = users.get(0);
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute(Constants.CURRENT_USER, user);
				resultBean.setSuccess(true);
				resultBean.setMessage(Constants.INDEX_PAGE);
			}
		}
		JsonUtils.writeJsonToResponse(resultBean);
		return null;
	}

	/**
	 * @功能: 退出系统
	 * @作者: yangc
	 * @创建日期: 2012-9-10 上午12:04:33
	 * @return
	 */
	public String logout() {
		logger.info("logout");
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute(Constants.CURRENT_USER);
		session.invalidate();
		return "loginPage";
	}

	/**
	 * @功能: 修改密码
	 * @作者: yangc
	 * @创建日期: 2013年12月21日 下午4:24:12
	 * @return
	 * @throws IOException
	 */
	public String changePassword() throws IOException {
		TSysUser user = LoginUserUtils.getLoginUser();
		logger.info("changePassword - userId=" + user.getId() + ", password=" + password + ", newPassword=" + newPassword);
		ResultBean resultBean = new ResultBean();
		if (StringUtils.isBlank(password) || StringUtils.isBlank(newPassword)) {
			resultBean.setSuccess(false);
			resultBean.setMessage("原密码或新密码不能为空");
		} else {
			if (!user.getPassword().equals(password)) {
				resultBean.setSuccess(false);
				resultBean.setMessage("原密码输入错误");
			} else {
				this.userService.updPassword(user.getId(), newPassword);
				resultBean.setSuccess(true);
				resultBean.setMessage("修改成功");
			}
		}
		JsonUtils.writeJsonToResponse(resultBean);
		return null;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
