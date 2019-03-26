package com.booway.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.booway.Exception.RestClientException;
import com.booway.mapper.TEnterUserMapper;
import com.booway.pojo.Authorize;
import com.booway.pojo.TEnterUser;
import com.booway.pojo.TEnterUserExample;
import com.booway.pojo.TEnterUserExample.Criteria;
import com.booway.service.IndexService;
import com.booway.utils.BeanCopyUtil;
import com.booway.utils.ExamineState;
import com.booway.utils.GsonUtils;
import com.booway.utils.HttpClientUtil;
import com.booway.utils.PageObj;
import com.booway.utils.ParseUtil;
import com.booway.utils.WeixinUtil;
import com.booway.vo.EnterUserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class IndexServiceImpl implements IndexService {
	
	@Autowired
	private TEnterUserMapper userMapper;
	
	@Value("${allowFiles}")
	private String allowFiles;
	
	@Value("${picSavePath}")
	private String picSavePath;
	
	@Value("${appid}")
	private String appid;
	
	@Value("${secret}")
	private String secret;
	
	@Value("${grant_type_authorization_code_url}")
	private String authorUrl;
	
	@Transactional(rollbackFor={Exception.class})
	@Override
	public PageObj<TEnterUser> queryEnterUsers(String username, int pageNum, int pageSize) throws Exception{
		
		PageHelper.startPage(pageNum, pageSize);
		TEnterUserExample example = new TEnterUserExample();
		Criteria criteria = example.createCriteria();
		if(StringUtils.hasText(username)) {
			username = WeixinUtil.dealSpec(username);
			criteria.andUserNameLike("%" + username + "%");
		}
		//筛选审核通过的用户
		criteria.andExamineStateEqualTo(ExamineState.EXAMINE_PASS.getText());
		List<TEnterUser> list = userMapper.selectByExample(example);
		PageInfo<TEnterUser> pageInfo = new PageInfo<TEnterUser>(list);
		long total = pageInfo.getTotal();
		
		return new PageObj<TEnterUser>(pageNum, total, pageSize, pageInfo.getList());
	}

	@Override
	public EnterUserVo queryBabySortInfoById(Integer id) throws Exception {
		TEnterUser baby = userMapper.selectByPrimaryKey(id);
		int sortNum = userMapper.getSortNum(baby.getVoteNum());
		EnterUserVo userVo = BeanCopyUtil.copy(EnterUserVo.class, baby);
		userVo.setSortNum(sortNum);
		return userVo;
	}

	@Override
	public PageObj<TEnterUser> querySortInfo(int pageNum, int pageSize)
			throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		TEnterUserExample example = new TEnterUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andExamineStateEqualTo(ExamineState.EXAMINE_PASS.getText());
		List<TEnterUser> list = userMapper.selectSortByExample(example);
		PageInfo<TEnterUser> pageInfo = new PageInfo<TEnterUser>(list);
		long total = pageInfo.getTotal();
		return new PageObj<TEnterUser>(pageNum, total, pageSize, pageInfo.getList());
	}

	@Transactional(rollbackFor={Exception.class})
	@Override
	public TEnterUser enterName(TEnterUser tEnterUser) throws Exception {
		
		int maxCode = userMapper.getMaxNum();
		tEnterUser.setOrderCode(maxCode + 1);
		int row = userMapper.insertSelective(tEnterUser);
		if(row <= 0) {
			throw new RestClientException("报名失败！");
		}
		return tEnterUser;
	}
	
	public TEnterUser validationEnterUser(Map<String, Object> params, HttpServletRequest request) throws Exception {
		TEnterUser tEnterUser = new TEnterUser();
		HttpSession session = request.getSession(true);
		Authorize authorize = (Authorize)session.getAttribute("authorize");
		//String openId = authorize.getOpenid();
		String openId="helloworldsjjfhasdhsadgj";
		//baby名字
		String username = ParseUtil.parseStr(params.get("babyName"));
		if(!StringUtils.hasText(openId)) {
			throw new RestClientException("未获取到用户唯一标识！");
		}
		tEnterUser.setOpenId(openId);
		if(!StringUtils.hasText(username)) {
			throw new RestClientException("宝贝名字不能为空！");
		}
		tEnterUser.setUserName(username);
		String linkman = ParseUtil.parseStr(params.get("linkmanName"));
		if(!StringUtils.hasText(linkman)) {
			throw new RestClientException("联系人名字不能为空！");
		}
		tEnterUser.setLinkman(linkman);
		String phoneNum = ParseUtil.parseStr(params.get("linkmanPhonNum"));
		if(!StringUtils.hasText(phoneNum) || !WeixinUtil.validationPhoneNum(phoneNum)) {
			throw new RestClientException("请输入正确的手机或座机号码！");
		}
		tEnterUser.setPhoneNum(phoneNum);
		
		String picUrls = ParseUtil.parseStr(params.get("base64"));
		
		if(!StringUtils.hasText(picUrls)) {
			throw new RestClientException("请上传图片！");
		}
		String base64s[] = picUrls.split("@@");
		if(base64s.length > 6) {
			throw new RestClientException("最多只能上传6张！");
		}
		List<String> urls = parseAndSavePic(base64s,request);
		String picUrl = join(urls, ",");
		tEnterUser.setPicUrl(picUrl);
		tEnterUser.setCrtTime(new Date());
		tEnterUser.setUpdateTime(new Date());
		//审核中
		tEnterUser.setExamineState(ExamineState.EXAMINE_ING.getText());
		tEnterUser.setVoteNum(0);
		return tEnterUser;
	}
	
	private String join(List<String> list, String delim) {
		StringBuilder sb = new StringBuilder();
		for(String str : list) {
			sb.append(str).append(",");
		}
		if(sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	
	private List<String> parseAndSavePic(String base64s[], HttpServletRequest request) {
		List<String> urls = new ArrayList<String>();
		if(base64s == null || base64s.length == 0) {
			return urls;
		}
		String webRootPath = request.getServletContext().getRealPath("/");
		System.out.println(webRootPath);
		for(String base : base64s) {
			String base64 = base.replace("data:image/png;base64,", "");
			byte[] buff = Base64Utils.decodeFromString(base64);
			String finalPath = WeixinUtil.generateFilePath(webRootPath + picSavePath) + UUID.randomUUID().toString() + ".png";
			File savefile = new File(finalPath);
			if(!savefile.getParentFile().exists()) {
				if(!savefile.getParentFile().mkdirs()) {
					throw new RestClientException("对不起，创建上传的路径失败！");
				}
			}
			try {
				FileUtils.writeByteArrayToFile(savefile, buff);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RestClientException("保存图片文件失败");
			}
			String needSavePath = finalPath.replace(webRootPath, "");
			urls.add(needSavePath);
			
		}
		return urls;
	}

	@Override
	public String uploadPic(MultipartFile file) throws Exception {
		if(file == null || file.isEmpty()) {
			throw new RestClientException("请上传图片");
		}
		String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "utf-8");
		String[] afsArr = allowFiles.split(",");
		if(!WeixinUtil.isAllowPath(fileName, afsArr)) {
			throw new RestClientException("请上传符合格式（jpg,jpeg,png）的图片");
		}
		String finalPath = WeixinUtil.generateFilePath(picSavePath) + fileName;
		File savefile = new File(finalPath);
		if(!savefile.exists()) {
			if(!savefile.mkdirs()) {
				throw new RestClientException("对不起，创建上传的路径失败！");
			}
		}
		file.transferTo(savefile);
		return finalPath;
	}

	@Override
	public void authorize(String code, HttpServletRequest request) throws Exception {
		String url = MessageFormat.format(authorUrl, appid, secret, code);
		String respStr = HttpClientUtil.doGet(url);
		Map<String,Object> map = GsonUtils.toMap(respStr, String.class, Object.class);
		if(map.containsKey("errcode")) {
			throw new RestClientException("授权失败！");
		}
		Authorize authorize = GsonUtils.toObject(respStr, Authorize.class);
		HttpSession session = request.getSession(true);
		//设置过期时间
		session.setMaxInactiveInterval(authorize.getExpires_in());
		session.setAttribute("authorize", authorize);
	}

	@Override
	public TEnterUser lookEnterResult(Integer id) throws Exception {
		TEnterUser user = userMapper.selectByPrimaryKey(id);
		if(user == null) {
			throw new RestClientException("该用户不存在或者已取消报名！");
		}
		return user;
	}

}
