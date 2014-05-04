package com.yangc.bean;

public class ResultBean {

	private boolean success;
	private String message;
	private String msg;

	public ResultBean() {
	}

	public ResultBean(boolean success, String message) {
		this.success = success;
		this.message = message;
		this.msg = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		this.msg = message;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.message = msg;
		this.msg = msg;
	}

}
