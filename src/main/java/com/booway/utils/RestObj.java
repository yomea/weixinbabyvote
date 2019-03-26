package com.booway.utils;

/**
 * 返回工具类
 * @author wuzhenhong
 *
 */
public class RestObj {
	
	private boolean successful = true;
	
	private String msg;
	
	private String code;
	
	private Object data;
	
	public RestObj() {
		super();
	}
	
	public RestObj(boolean successful, String msg, Object data) {
		super();
		this.successful = successful;
		this.msg = msg;
		this.data = data;
	}
	
	public RestObj(boolean successful, String msg, String code, Object data) {
		this(successful,msg,data);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
