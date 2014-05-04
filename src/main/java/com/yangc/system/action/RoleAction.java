package com.yangc.system.action;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.yangc.action.PaginationAction;
import com.yangc.bean.ResultBean;
import com.yangc.system.bean.oracle.TSysRole;
import com.yangc.system.service.RoleService;
import com.yangc.utils.JsonUtils;

public class RoleAction implements PaginationAction {

	private static Logger logger = Logger.getLogger(RoleAction.class);

	private RoleService roleService;

	private Long id;
	private String roleName;

	public String getRoleList() throws IOException {
		logger.info("getRoleList");
		List<TSysRole> roleList = this.roleService.getRoleList();
		JsonUtils.writeJsonToResponse(roleList);
		return null;
	}

	/**
	 * @功能: 查询所有角色(分页)
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午7:16:59
	 * @return
	 * @throws IOException
	 */
	public String getRoleList_page() throws IOException {
		logger.info("getRoleList_page");
		List<TSysRole> roleList = this.roleService.getRoleList_page();
		JsonUtils.writeJsonToResponse(roleList);
		return null;
	}

	public String addOrUpdateRole() throws IOException {
		logger.info("addOrUpdateRole - id=" + id + ", roleName=" + roleName);
		ResultBean resultBean = new ResultBean();
		resultBean.setSuccess(true);
		if (id == null) {
			resultBean.setMessage("添加成功");
		} else {
			resultBean.setMessage("修改成功");
		}
		this.roleService.addOrUpdateRole(id, roleName);
		JsonUtils.writeJsonToResponse(resultBean);
		return null;
	}

	public String delRole() throws IOException {
		logger.info("delRole - id=" + id);
		this.roleService.delRole(id);
		JsonUtils.writeJsonToResponse(new ResultBean(true, "删除成功"));
		return null;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
