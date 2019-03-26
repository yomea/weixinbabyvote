package com.booway.service;

import javax.servlet.http.HttpServletRequest;

import com.booway.pojo.TVoteRecord;

public interface VoteService {
	
	public void vote(TVoteRecord tVoteRecord, HttpServletRequest request) throws Exception;
	
}
