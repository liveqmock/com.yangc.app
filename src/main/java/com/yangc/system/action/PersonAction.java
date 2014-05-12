package com.yangc.system.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yangc.action.PaginationAction;
import com.yangc.bean.DataGridBean;
import com.yangc.bean.ResultBean;
import com.yangc.system.bean.oracle.TSysPerson;
import com.yangc.system.bean.oracle.TSysUsersroles;
import com.yangc.system.service.PersonService;
import com.yangc.system.service.UsersrolesService;
import com.yangc.utils.JsonUtils;

public class PersonAction implements PaginationAction {

	private static final Logger logger = Logger.getLogger(PersonAction.class);

	private PersonService personService;
	private UsersrolesService usersrolesService;

	private Long id;
	private String name;
	private Long sex;
	private String phone;
	private Long deptId;
	private Long userId;
	private String username;
	private String password;
	private String roleIds;

	/**
	 * @功能: 查询所有用户(自动完成)
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午5:30:25
	 * @return
	 * @throws IOException
	 */
	public String getPersonList() throws IOException {
		logger.info("getPersonList");
		List<TSysPerson> personList = this.personService.getPersonList();
		JsonUtils.writeJsonToResponse(personList);
		return null;
	}

	/**
	 * @功能: 查询所有用户(分页)
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午5:30:25
	 * @return
	 * @throws IOException
	 */
	public String getPersonListByPersonNameAndDeptId_page() throws IOException {
		if (StringUtils.isNotBlank(name)) {
			name = URLDecoder.decode(name, "UTF-8");
		}
		logger.info("getPersonListByPersonNameAndDeptId_page - name=" + name + ", deptId=" + deptId);
		List<TSysPerson> personList = this.personService.getPersonListByPersonNameAndDeptId_page(name, deptId);
		DataGridBean dataGridBean = new DataGridBean();
		dataGridBean.setDataGrid(personList);
		JsonUtils.writeJsonToResponse(dataGridBean);
		return null;
	}

	/**
	 * @功能: 根据userId获取roleIds
	 * @作者: yangc
	 * @创建日期: 2013年12月24日 上午10:49:47
	 * @return
	 * @throws IOException
	 */
	public String getRoleIdsByUserId() throws IOException {
		logger.info("getRoleIdsByUserId - userId=" + userId);
		TSysPerson person = new TSysPerson();
		List<TSysUsersroles> usersrolesList = this.usersrolesService.getUsersrolesListByUserId(userId);
		if (usersrolesList == null || usersrolesList.isEmpty()) {
			person.setRoleIds("");
		} else {
			StringBuilder sb = new StringBuilder();
			for (TSysUsersroles usersroles : usersrolesList) {
				sb.append(usersroles.getRoleId()).append(",");
			}
			person.setRoleIds(sb.substring(0, sb.length() - 1));
		}
		JsonUtils.writeJsonToResponse(person);
		return null;
	}

	/**
	 * @功能: 添加或修改用户
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午5:49:16
	 * @return
	 * @throws IOException
	 */
	public String addOrUpdatePerson() throws IOException {
		logger.info("addOrUpdatePerson - id=" + id + ", name=" + name + ", sex=" + sex + ", phone=" + phone + ", deptId=" + deptId + ", userId=" + userId + ", username=" + username + ", password="
				+ password + ", roleIds=" + roleIds);
		ResultBean resultBean = new ResultBean();
		try {
			resultBean.setSuccess(true);
			if (id == null) {
				resultBean.setMessage("添加成功");
			} else {
				resultBean.setMessage("修改成功");
			}
			this.personService.addOrUpdatePerson(id, name, sex, phone, deptId, userId, username, password, roleIds);
		} catch (IllegalStateException e) {
			resultBean.setSuccess(false);
			resultBean.setMessage(e.getMessage());
		}
		JsonUtils.writeJsonToResponse(resultBean);
		return null;
	}

	/**
	 * @功能: 删除用户
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午7:00:20
	 * @return
	 * @throws IOException
	 */
	public String delPerson() throws IOException {
		logger.info("delPerson - id=" + id);
		this.personService.delPerson(id);
		JsonUtils.writeJsonToResponse(new ResultBean(true, "删除成功"));
		return null;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setUsersrolesService(UsersrolesService usersrolesService) {
		this.usersrolesService = usersrolesService;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

}
