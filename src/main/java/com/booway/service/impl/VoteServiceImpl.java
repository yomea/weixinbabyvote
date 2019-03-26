package com.booway.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booway.Exception.RestClientException;
import com.booway.mapper.TEnterUserMapper;
import com.booway.mapper.TVoteRecordMapper;
import com.booway.pojo.TEnterUser;
import com.booway.pojo.TEnterUserExample;
import com.booway.pojo.TVoteRecord;
import com.booway.pojo.TVoteRecordExample;
import com.booway.pojo.TVoteRecordExample.Criteria;
import com.booway.service.VoteService;
import com.booway.utils.ExamineState;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private TVoteRecordMapper tVoteRecordMapper;
	
	@Autowired
	private TEnterUserMapper userMapper;
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public void vote(TVoteRecord tVoteRecord, HttpServletRequest request) throws Exception {
		tVoteRecord.setVoteTime(new Date());
		int userId = tVoteRecord.getEnterUserId();
		String openId = tVoteRecord.getVoteOpenId();
		TVoteRecordExample example = new TVoteRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andEnterUserIdEqualTo(userId);
		criteria.andVoteOpenIdEqualTo(openId);
		List<TVoteRecord> tvrs = tVoteRecordMapper.selectByExample(example);
		TEnterUser user = userMapper.selectByPrimaryKey(userId);
		if(user == null) {
			throw new RestClientException("该用户已取消报名或未报名！");
		}
		
		String exaString = user.getExamineState();
		
		if(!ExamineState.EXAMINE_PASS.getText().equals(exaString)) {
			throw new RestClientException("只能给审核通过的用户投票！");
		}
		
		TEnterUserExample userExample = new TEnterUserExample();
		com.booway.pojo.TEnterUserExample.Criteria userCriteria = userExample.createCriteria();
		userCriteria.andIdEqualTo(userId);
		if(tvrs == null || tvrs.isEmpty()) {
			tVoteRecord.setVoteNum(1);
			
			int row = tVoteRecordMapper.insertSelective(tVoteRecord);
			user.setVoteNum(user.getVoteNum() + 1);
			int userRow = userMapper.updateByExampleSelective(user, userExample);
			if(row < 1 || userRow < 1) {
				throw new RestClientException("投票失败！");
			}
		} else {
			TVoteRecord oldTvr = tvrs.get(0);
			oldTvr.setVoteNum(oldTvr.getVoteNum() + 1);
			int row = tVoteRecordMapper.updateByExample(oldTvr, example);
			user.setVoteNum(user.getVoteNum() + 1);
			int userRow = userMapper.updateByExampleSelective(user, userExample);
			if(row < 1 || userRow < 1) {
				throw new RestClientException("投票失败！");
			}
		}
		
	}

}
