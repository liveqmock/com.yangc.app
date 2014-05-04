package com.yangc.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class JsonUtils {

	private JsonUtils() {
	}

	public static void writeJsonToResponse(Object obj) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(com.yangc.utils.json.JsonUtils.toJson(obj));
		pw.flush();
		pw.close();
	}

}
