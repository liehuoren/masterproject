package com.master.controller.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.master.controller.base.BaseController;
import com.master.entity.Page;
import com.master.enums.ExEnum;
import com.master.service.userarea.UserAreaManager;
import com.master.util.PageData;

@Controller
@RequestMapping("test")
public class TestController extends BaseController {
	
	@Resource(name="userareaService")
	private UserAreaManager userareaService;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2879258974982808447L;
	
	@ResponseBody
	@RequestMapping("map")
	public Object map(){
		PageData pd = getPageData();
		return getResultMap(ExEnum.EX_SUCCESS, pd);
	}
	
	@RequestMapping("list")
	public ModelAndView list(){
		Page page = new Page();
		page.setPd(getPageData());
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("list", userareaService.findByParentidlist(page));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("test");
		
		return mv;
	}
	
}
