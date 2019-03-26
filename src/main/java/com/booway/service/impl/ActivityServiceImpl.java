package com.booway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.booway.Exception.RestClientException;
import com.booway.mapper.TActivityMapper;
import com.booway.pojo.TActivity;
import com.booway.pojo.TActivityExample;
import com.booway.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private TActivityMapper activityMapper;

	@Override
	public List<TActivity> queryNotEnd() throws Exception {
		TActivityExample example = new TActivityExample();
		//Criteria criteria = example.createCriteria();
		//Date currentDate = new Date();
		//获取在活动时间内，并且已经启动开始的。
		//criteria.andCrtTimeGreaterThan(currentDate);
		//criteria.andEndTimeLessThan(currentDate);
		//criteria.andActStateNotEqualTo(ActivityState.)
		//criteria.andActStateEqualTo(ActivityState.ING.getText());
		List<TActivity> activities = activityMapper.selectByExample(example);
		return activities;
	}

	@Override
	public TActivity save(TActivity activity) throws Exception {
		String title = activity.getTitle();
		if(!StringUtils.hasText(title)) {
			throw new RestClientException("请输入活动说明标题！");
		}
		String content = activity.getContent();
		if(!StringUtils.hasText(content)) {
			throw new RestClientException("请输入活动说明内容！");
		}
		/*Date startDate = activity.getCrtTime();
		Date currentDate = new Date();
		if(startDate == null) {
			throw new RestClientException("请填写活动开始时间");
		}
		if(startDate.compareTo(currentDate) == -1) {
			throw new RestClientException("活动开始时间不能小于当前时间");
		}
		Date endDate = activity.getEndTime();
		if(endDate == null) {
			throw new RestClientException("请填写活动结束时间");
		}
		if(endDate.compareTo(currentDate) == -1) {
			throw new RestClientException("活动结束时间不能小于当前时间");
		}
		if(startDate.compareTo(endDate) >=0) {
			throw new RestClientException("活动结束时间不能小于等于开始时间");
		}
		activity.setActState(ActivityState.WILL.getText());*/
		int row = activityMapper.insertSelective(activity);
		if(row <= 0) {
			throw new RestClientException("添加活动说明失败!");
		}
		return activity;
	}

	@Override
	public void remove(int id) throws Exception {
		int row = activityMapper.deleteByPrimaryKey(id);
		if(row <= 0) {
			throw new RestClientException("活动说明删除失败！");
		}
	}

	@Override
	public TActivity queryInfoById(int id) throws Exception {
		TActivity activity = activityMapper.selectByPrimaryKey(id);
		if(activity == null) {
			throw new RestClientException("该活动说明已被删除或不存在");
		}
		return activity;
	}

	
}
