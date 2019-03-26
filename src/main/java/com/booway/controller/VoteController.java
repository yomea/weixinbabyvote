package com.booway.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booway.pojo.Authorize;
import com.booway.pojo.TVoteRecord;
import com.booway.service.VoteService;
import com.booway.utils.RestObj;

@RestController
@RequestMapping("/vote")
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	private Lock lock = new ReentrantLock();
	
	@RequestMapping(value="/{id}")
	public RestObj vote(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		/*Authorize authorize = (Authorize)session.getAttribute("authorize");
		String openId = authorize.getOpenid();*/
		String openId="dfdfghfgh";
		TVoteRecord tVoteRecord = new TVoteRecord();
		tVoteRecord.setVoteOpenId(openId);
		tVoteRecord.setEnterUserId(id);
		lock.lock();
		try {
			voteService.vote(tVoteRecord, request);
		} finally {
			lock.unlock();
		}
		return new RestObj(true, "投票成功！", "");
	}
	
}
