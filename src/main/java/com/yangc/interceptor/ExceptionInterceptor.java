package com.yangc.interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;

public class ExceptionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7298956055558550695L;

	private static Logger logger = Logger.getLogger(ExceptionInterceptor.class);

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		String actionName = ai.getInvocationContext().getName();
		try {
			String str = ai.invoke();
			Object obj = ai.getStack().getRoot().get(0);
			if (obj instanceof ExceptionHolder) {
				logger.error(actionName, ((ExceptionHolder) obj).getException());
			}
			return str;
		} catch (Exception e) {
			logger.error(actionName, e);
			return "exception";
		}
	}

}
