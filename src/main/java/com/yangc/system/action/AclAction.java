package com.yangc.system.action;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.yangc.bean.ResultBean;
import com.yangc.system.bean.oracle.AuthTree;
import com.yangc.system.service.AclService;
import com.yangc.utils.JsonUtils;

public class AclAction {

	private static Logger logger = Logger.getLogger(AclAction.class);

	private AclService aclService;
	private Long roleId;
	private Long parentMenuId = 0L;
	private Long menuId;
	private int permission;
	private int allow;

	/**
	 * @功能: 某个角色所拥有的权限
	 * @作者: yangc
	 * @创建日期: 2013年12月24日 下午10:54:50
	 * @return
	 * @throws IOException
	 */
	public String getAuthTreeList() throws IOException {
		logger.info("getAuthTreeList - roleId=" + roleId + ", parentMenuId=" + parentMenuId);
		List<AuthTree> authTreeList = this.aclService.getAclListByRoleIdAndParentMenuId(roleId, parentMenuId);
		JsonUtils.writeJsonToResponse(authTreeList);
		return null;
	}

	/**
	 * @功能: 添加或修改权限
	 * @作者: yangc
	 * @创建日期: 2013年12月24日 下午11:14:16
	 * @return
	 * @throws IOException
	 */
	public String addOrUpdateAcl() throws IOException {
		logger.info("addOrUpdateAcl - roleId=" + roleId + ", menuId=" + menuId + ", permission=" + permission + ", allow=" + allow);
		this.aclService.addOrUpdateAcl(roleId, menuId, permission, allow);
		JsonUtils.writeJsonToResponse(new ResultBean(true, ""));
		return null;
	}

	public void setAclService(AclService aclService) {
		this.aclService = aclService;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public void setAllow(int allow) {
		this.allow = allow;
	}

}
