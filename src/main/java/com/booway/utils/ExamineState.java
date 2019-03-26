package com.booway.utils;

public enum ExamineState {
	
	EXAMINE_PASS("审核通过"), EXAMINE_FAIL("审核不通过"), EXAMINE_ING("待审核");
	
	private String text;
	
	private ExamineState(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
