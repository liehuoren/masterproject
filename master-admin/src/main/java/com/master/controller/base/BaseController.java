package com.master.controller.base;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.master.entity.Page;
import com.master.enums.ExEnum;
import com.master.util.AppUtil;
import com.master.util.Logger;
import com.master.util.PageData;
import com.master.util.UuidUtil;

/**
 */
public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;
	
	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
	/**得到分页列表的信息
	 * @return
	 */
	public Page getPage(){
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	
	/**
	 * ajax返回
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public Map<String, Object> getResultMap(String code,String msg,Object data){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("code",code);
		map.put("msg",msg);
		map.put("data",data);
		return map;
	}
	/**
	 * ajax返回
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public Object getResultMap(ExEnum ex,Object data){
		PageData pd = this.getPageData();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("code",ex.getCode());
		map.put("msg",ex.getDes());
		map.put("data",data);
		return AppUtil.returnObject(pd, map);
	}
	
}
