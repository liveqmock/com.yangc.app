package com.yangc.system.action;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.yangc.bean.ResultBean;
import com.yangc.system.bean.oracle.MenuTree;
import com.yangc.system.bean.oracle.TSysMenu;
import com.yangc.system.service.MenuService;
import com.yangc.utils.JsonUtils;
import com.yangc.utils.LoginUserUtils;

public class MenuAction {

	private static Logger logger = Logger.getLogger(MenuAction.class);

	private MenuService menuService;

	private Long id;
	private String menuName;
	private String menuUrl;
	private Long parentMenuId = 0L;
	private Long serialNum;
	private Long isshow;
	private String description;

	/**
	 * @功能: 显示顶层tab
	 * @作者: yangc
	 * @创建日期: 2012-9-10 上午12:00:10
	 * @return
	 * @throws IOException
	 */
	public String showTopFrame() throws IOException {
		Long userId = LoginUserUtils.getLoginUser().getId();
		logger.info("showTopFrame - parentMenuId=" + parentMenuId + ", userId=" + userId);
		List<TSysMenu> menus = this.menuService.getTopFrame(parentMenuId, userId);
		JsonUtils.writeJsonToResponse(menus);
		return null;
	}

	/**
	 * @功能: 显示主页左侧和主页内容
	 * @作者: yangc
	 * @创建日期: 2012-9-10 上午12:00:10
	 * @return
	 * @throws IOException
	 */
	public String showMainFrame() throws IOException {
		Long userId = LoginUserUtils.getLoginUser().getId();
		logger.info("showMainFrame - parentMenuId=" + parentMenuId + ", userId=" + userId);
		List<TSysMenu> menus = this.menuService.getMainFrame(parentMenuId, userId);
		JsonUtils.writeJsonToResponse(menus);
		return null;
	}

	/**
	 * @功能: 根据parentMenuId获取菜单树
	 * @作者: yangc
	 * @创建日期: 2014年1月2日 下午4:04:09
	 * @return
	 * @throws IOException
	 */
	public String getMenuTreeList() throws IOException {
		logger.info("getMenuTreeList - parentMenuId=" + parentMenuId);
		List<MenuTree> menuTreeList = this.menuService.getMenuListByParentMenuId(parentMenuId);
		JsonUtils.writeJsonToResponse(menuTreeList);
		return null;
	}

	/**
	 * @功能: 添加菜单
	 * @作者: yangc
	 * @创建日期: 2014年1月2日 下午2:06:05
	 * @return
	 * @throws IOException
	 */
	public String addOrUpdateMenu() throws IOException {
		logger.info("addOrUpdateMenu - id=" + id + ", menuName=" + menuName + ", menuUrl=" + menuUrl + ", parentMenuId=" + parentMenuId + ", serialNum=" + serialNum + ", isshow=" + isshow
				+ ", description=" + description);
		ResultBean resultBean = new ResultBean();
		resultBean.setSuccess(true);
		if (id == null) {
			resultBean.setMessage("添加成功，请授权进行查看");
		} else {
			resultBean.setMessage("修改成功，请刷新页面进行查看");
		}
		this.menuService.addOrUpdateMenu(id, menuName, menuUrl, parentMenuId, serialNum, isshow, description);
		JsonUtils.writeJsonToResponse(resultBean);
		return null;
	}

	/**
	 * @功能: 修改所属父节点
	 * @作者: yangc
	 * @创建日期: 2014年1月2日 下午2:09:41
	 * @return
	 * @throws IOException
	 */
	public String updParentMenuId() throws IOException {
		logger.info("updParentMenuId - id=" + id + ", parentMenuId=" + parentMenuId);
		this.menuService.updParentMenuId(id, parentMenuId);
		JsonUtils.writeJsonToResponse(new ResultBean(true, "修改成功"));
		return null;
	}

	/**
	 * @功能: 删除菜单
	 * @作者: yangc
	 * @创建日期: 2014年1月2日 下午2:06:17
	 * @return
	 * @throws IOException
	 */
	public String delMenu() throws IOException {
		logger.info("delMenu - id=" + id);
		ResultBean resultBean = new ResultBean();
		try {
			this.menuService.delMenu(id);
			resultBean.setSuccess(true);
			resultBean.setMessage("删除成功");
		} catch (IllegalStateException e) {
			resultBean.setSuccess(false);
			resultBean.setMessage(e.getMessage());
		}
		JsonUtils.writeJsonToResponse(resultBean);
		return null;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}

	public void setIsshow(Long isshow) {
		this.isshow = isshow;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
