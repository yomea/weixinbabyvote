package com.booway.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.booway.pojo.TEnterUser;
import com.booway.utils.PageObj;
import com.booway.vo.EnterUserVo;

public interface IndexService {
	
	public PageObj<TEnterUser> queryEnterUsers(String username, int pageNum, int pageSize) throws Exception;

	public EnterUserVo queryBabySortInfoById(Integer id) throws Exception;

	public PageObj<TEnterUser> querySortInfo(int pageNum, int pageSize) throws Exception;

	public TEnterUser enterName(TEnterUser tEnterUser) throws Exception;

	public String uploadPic(MultipartFile file) throws Exception;
	
	public TEnterUser validationEnterUser(Map<String, Object> params, HttpServletRequest request) throws Exception;

	public void authorize(String code, HttpServletRequest request) throws Exception;

	public TEnterUser lookEnterResult(Integer id) throws Exception;
	
}
