package com.booway.utils;

import java.util.List;

public class PageObj<T> {
	
	private int currentPageNum;
	
	private long total;
	
	private int pageSize;
	
	private List<T> data;
	
	public PageObj(int currentPageNum, long total, int pageSize, List<T> data) {
		super();
		this.currentPageNum = currentPageNum;
		this.total = total;
		this.pageSize = pageSize;
		this.data = data;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
