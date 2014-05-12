package com.yangc.system.action;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.yangc.action.PaginationAction;
import com.yangc.bean.DataGridBean;
import com.yangc.bean.ResultBean;
import com.yangc.system.bean.oracle.TSysDepartment;
import com.yangc.system.service.DeptService;
import com.yangc.utils.JsonUtils;

public class DeptAction implements PaginationAction {

	private static final Logger logger = Logger.getLogger(DeptAction.class);

	private DeptService deptService;

	private Long id;
	private String deptName;
	private Long serialNum;

	/**
	 * @功能: 查询所有部门(下拉列表)
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午2:13:04
	 * @return
	 * @throws IOException
	 */
	public String getDeptList() throws IOException {
		logger.info("getDeptList");
		List<TSysDepartment> deptList = this.deptService.getDeptList();
		JsonUtils.writeJsonToResponse(deptList);
		return null;
	}

	/**
	 * @功能: 查询所有部门(分页)
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午2:13:04
	 * @return
	 * @throws IOException
	 */
	public String getDeptList_page() throws IOException {
		logger.info("getDeptList_page");
		List<TSysDepartment> deptList = this.deptService.getDeptList_page();
		DataGridBean dataGridBean = new DataGridBean();
		dataGridBean.setDataGrid(deptList);
		JsonUtils.writeJsonToResponse(dataGridBean);
		return null;
	}

	/**
	 * @功能: 添加或修改部门
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午2:59:26
	 * @return
	 * @throws IOException
	 */
	public String addOrUpdateDept() throws IOException {
		logger.info("addOrUpdateDept - id=" + id + ", deptName=" + deptName + ", serialNum=" + serialNum);
		ResultBean resultBean = new ResultBean();
		resultBean.setSuccess(true);
		if (id == null) {
			resultBean.setMessage("添加成功");
		} else {
			resultBean.setMessage("修改成功");
		}
		this.deptService.addOrUpdateDept(id, deptName, serialNum);
		JsonUtils.writeJsonToResponse(resultBean);
		return null;
	}

	/**
	 * @功能: 删除部门
	 * @作者: yangc
	 * @创建日期: 2013年12月23日 下午3:02:44
	 * @return
	 * @throws IOException
	 */
	public String delDept() throws IOException {
		logger.info("delDept - id=" + id);
		ResultBean resultBean = new ResultBean();
		try {
			this.deptService.delDept(id);
			resultBean.setSuccess(true);
			resultBean.setMessage("删除成功");
		} catch (IllegalStateException e) {
			resultBean.setSuccess(false);
			resultBean.setMessage(e.getMessage());
		}
		JsonUtils.writeJsonToResponse(resultBean);
		return null;
	}

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}

}
