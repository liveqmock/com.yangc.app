package com.yangc.utils;

import com.yangc.system.service.AclService;

public class AclTld {

	private static AclService aclService;

	public static Integer getPermission(Long menuId, Integer permission) {
		Long userId = LoginUserUtils.getLoginUser().getId();
		return aclService.getOperateStatus(userId, menuId, permission);
	}

	public void setAclService(AclService aclService) {
		AclTld.aclService = aclService;
	}

}
