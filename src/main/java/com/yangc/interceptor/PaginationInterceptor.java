package com.yangc.interceptor;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yangc.action.PaginationAction;
import com.yangc.common.Pagination;
import com.yangc.common.PaginationThreadUtils;

public class PaginationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7394634595783880988L;

	private static final Logger logger = Logger.getLogger(PaginationInterceptor.class);

	// 前端js对分页请求的名字
	private static final String PAGE_SIZE = "limit";
	private static final String PAGE_NOW = "page";

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		ActionContext ac = ai.getInvocationContext();
		Object action = ai.getAction();
		if (action instanceof PaginationAction) {
			Pagination pagination = PaginationThreadUtils.get();
			if (pagination == null) {
				pagination = new Pagination();
				PaginationThreadUtils.set(pagination);
			}
			Map<String, Object> params = ac.getParameters();
			// 设置要跳转到的页数
			if (params.get(PAGE_NOW) == null) {
				pagination.setPageNow(1);
			} else {
				String pageNow = ((String[]) params.get(PAGE_NOW))[0];
				if (StringUtils.isBlank(pageNow)) {
					pagination.setPageNow(1);
				} else {
					pagination.setPageNow(Integer.parseInt(pageNow));
				}
			}
			// 设置每页的行数
			if (params.get(PAGE_SIZE) != null) {
				String pageSize = ((String[]) params.get(PAGE_SIZE))[0];
				if (StringUtils.isNotBlank(pageSize)) {
					pagination.setPageSize(Integer.parseInt(pageSize));
				}
			}
			logger.info("PaginationInterceptor - pageNow=" + pagination.getPageNow() + ", pageSize=" + pagination.getPageSize());
		}
		String str = ai.invoke();
		PaginationThreadUtils.clear();
		return str;
	}

}
