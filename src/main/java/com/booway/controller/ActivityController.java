package com.booway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.booway.pojo.TActivity;
import com.booway.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value="/query")
	public String query(Model model) throws Exception {
		List<TActivity> tas = activityService.queryNotEnd();
		model.addAttribute("tas", tas);
		return "activity";
	}
	
	@RequestMapping(value="/info/{id}")
	public String queryInfoById(@PathVariable Integer id, Model model) throws Exception {
		TActivity activity = activityService.queryInfoById(id);
		model.addAttribute("activity", activity);
		return "activityDetail";
	}
	
}
