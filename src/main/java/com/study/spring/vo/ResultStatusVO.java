package com.study.spring.vo;

public class ResultStatusVO {
	private boolean success;
	private String msg;

	public ResultStatusVO() {
	}

	public ResultStatusVO(boolean success) {
		this.success = success;
	}

	public ResultStatusVO(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
