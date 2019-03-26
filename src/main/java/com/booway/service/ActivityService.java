package com.booway.service;

import java.util.List;

import com.booway.pojo.TActivity;

public interface ActivityService {

	
	List<TActivity> queryNotEnd() throws Exception;
	
	TActivity save(TActivity activity) throws Exception;
	
	void remove(int id) throws Exception;
	
	TActivity queryInfoById(int id) throws Exception;
	
}
