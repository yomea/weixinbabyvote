package com.booway.utils;

public enum ActivityState {
	
	CLOSE("关闭"), ING("进行中"), END("结束"), WILL("未启动");
	
	private String text;
	
	private ActivityState(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}
