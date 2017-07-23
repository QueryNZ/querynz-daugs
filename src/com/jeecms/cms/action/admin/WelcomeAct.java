package com.jeecms.cms.action.admin;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeAct {
	

//	这里应是请求权限
//	@RequiresPermissions("index")  
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request) {
		return "index";
	}

//	@RequiresPermissions("map")
	@RequestMapping("/map.do")
	public String map() {
		return "map";
	}

//	@RequiresPermissions("top")
	@RequestMapping("/top.do")
	public String top(HttpServletRequest request, ModelMap model) {

		return "top";
	}

//	@RequiresPermissions("main")
	@RequestMapping("/main.do")
	public String main() {
		return "main";

	}
	
//	@RequiresPermissions("left")
	@RequestMapping("/left.do")
	public String left(HttpServletRequest request, ModelMap model) {
		
		return "left";
	}
	
//	@RequiresPermissions("right")
	@RequestMapping("/right.do")
	public String right(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		return "right";
	}



	
}
