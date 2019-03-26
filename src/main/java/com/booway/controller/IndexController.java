package com.booway.controller;

import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.booway.pojo.TEnterUser;
import com.booway.service.IndexService;
import com.booway.utils.PageObj;
import com.booway.utils.RestObj;
import com.booway.vo.EnterUserVo;

@Controller
@RequestMapping("/baby")
public class IndexController {

	@Autowired
	private IndexService indexService;
	
	private Lock lock = new ReentrantLock();
	
	@RequestMapping("/authorize")
	public String authorize(@RequestParam String code, HttpServletRequest request) throws Exception {
		indexService.authorize(code, request);
		return "redirect:/baby/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model) throws Exception {
		PageObj<TEnterUser> users = indexService.queryEnterUsers(null,
				1, 10);
		model.addAttribute("babyList", users);
		return "index";
	}

	/**
	 * 查询宝贝列表
	 * 
	 * @param params
	 * @return RestObj
	 * @exception 抛出，交给全局异常处理器处理
	 */
	@RequestMapping(value = "/query", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, method = RequestMethod.GET)
	@ResponseBody
	public RestObj queryEnterUsers(@RequestParam(value="username", defaultValue="") String username, @RequestParam(value="pageNum", defaultValue="1") Integer pageNum, @RequestParam(value="pageSize", defaultValue="10") Integer pageSize) throws Exception {
		
		String uname = URLDecoder.decode(username, "utf-8");
		PageObj<TEnterUser> users = indexService.queryEnterUsers(uname,
				pageNum, pageSize);
		return new RestObj(true, "查询成功！", users);

	}

	/**
	 * 查询当前宝贝排名信息
	 * 
	 * @return
	 */
	@RequestMapping(value="/sortInfo/{id}", method=RequestMethod.GET)
	public String queryCurrentBabyInfo(@PathVariable("id") Integer id, Model model) throws Exception {
		EnterUserVo enterUserVo = indexService.queryBabySortInfoById(id);
		model.addAttribute("EnterUserVo", enterUserVo);
		return "rankDetail";
	}
	
	@RequestMapping(value="/sortInfo",  method=RequestMethod.GET)
	public String querySortInfo(@RequestParam(value="pageNum", defaultValue="1") Integer pageNum, @RequestParam(value="pageSize", defaultValue="10") Integer pageSize, Model model) throws Exception {
		PageObj<TEnterUser> users = indexService.querySortInfo(pageNum, pageSize);
		model.addAttribute("users", users);
		return "rank";
	}
	
	@RequestMapping(value="/pic", method=RequestMethod.POST)
	@ResponseBody
	public RestObj uploadPic(@RequestParam MultipartFile file) throws Exception {
		String picPath = indexService.uploadPic(file);
		return new RestObj(true, "上传成功！", picPath);
	}
	
	@RequestMapping(value="/enter", produces={MediaType.APPLICATION_JSON_UTF8_VALUE}, method=RequestMethod.POST)
	@ResponseBody
	public RestObj enterName(@RequestBody Map<String, Object> params, HttpServletRequest request) throws Exception {
		
		//此处单独开一个方法进行校验数据，使得同步锁细粒度化
		TEnterUser tEnterUser = indexService.validationEnterUser(params, request);
		TEnterUser user =null;
		//同步，由于事物与同步锁会产生矛盾，此处将同步提到controller层
		lock.lock();
		try {
			user = indexService.enterName(tEnterUser);
		} catch(Exception e) {
			
		} finally {
			lock.unlock();
		}
		return new RestObj(true, "报名成功！", user);
	}
	
	@RequestMapping(value="/lookEnterResult/{id}")
	public String lookEnterResult(@PathVariable Integer id, Model model) throws Exception {
		
		TEnterUser user = indexService.lookEnterResult(id);
		model.addAttribute("user", user);
		return "signDetail";
	}

	
}
