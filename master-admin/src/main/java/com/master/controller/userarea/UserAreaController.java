package com.master.controller.userarea;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.master.controller.base.BaseController;
import com.master.entity.Page;
import com.master.service.userarea.UserAreaManager;
import com.master.util.AppUtil;
import com.master.util.Jurisdiction;
import com.master.util.ObjectExcelView;
import com.master.util.PageData;

/** 
 * 说明：地区维护
 * 创建人：FLC
 * 创建时间：2017-01-09
 */
@Controller
@RequestMapping(value="/userarea")
public class UserAreaController extends BaseController {
	
	String menuUrl = "userarea/list.do"; //菜单地址(权限用)
	@Resource(name="userareaService")
	private UserAreaManager userareaService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增UserArea");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("id", null);	//主键
		String PINGYIN = pd.getString("pingyin");
		if(null!=PINGYIN||!"".equals(PINGYIN)){
			pd.put("pingyin", PINGYIN.toUpperCase());
		}
		userareaService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除UserArea");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		userareaService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**手动处理百度接口获取的区域信息与系统中的区域信息关系映射
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/autoRelational")
	public void autoRelational(HttpServletRequest request, PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"手动处理百度接口获取的区域信息与系统中的区域信息关系映射");
		HttpSession session = request.getSession();
		String autoRelational = (String) session.getAttribute("autoRelational");
		PageData pd = new PageData();
		pd = this.getPageData();
		if(null==autoRelational){
			pd = userareaService.autoRelational(session);
			out.write("success");
		}else{
			out.write("executing");
		}
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改UserArea");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String PINGYIN = pd.getString("pingyin");
		if(null!=PINGYIN||!"".equals(PINGYIN)){
			pd.put("pingyin", PINGYIN.toUpperCase());
		}
		userareaService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表UserArea");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		String level = pd.getString("level");
		String parentid = pd.getString("parentid");
		
		//如果父级编号为空，则使用关键字+区域级别来检索数据
		if(null==parentid||"".equals(parentid.trim())){
			if(null != keywords && !"".equals(keywords)){
				pd.put("keywords", keywords.trim().toUpperCase());
			}
			if(null==level){
				pd.put("level", "1");//区域级别为1时表时为省级单位
			}
		}else{//父级编号存在则检索下辖的所有区域（下辖第一级，下级的下级不查)
			pd.put("parentid", parentid);
		}
		page.setShowCount(40);
		page.setPd(pd);
		List<PageData>	varList = userareaService.list(page);	//列出UserArea列表
		mv.setViewName("userarea/userarea_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("userarea/userarea_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = userareaService.findById(pd);	//根据ID读取
		mv.setViewName("userarea/userarea_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除UserArea");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			userareaService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出UserArea到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("拼音首字母简写");	//1
		titles.add("区域名称");	//2
		titles.add("区域简称");	//3
		titles.add("经度");	//4
		titles.add("纬度");	//5
		titles.add("区域级别");	//6
		titles.add("主键");	//7
		titles.add("父级编号");	//8
		dataMap.put("titles", titles);
		List<PageData> varOList = userareaService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("PINGYIN"));	    //1
			vpd.put("var2", varOList.get(i).getString("AREANAME"));	    //2
			vpd.put("var3", varOList.get(i).getString("SHORTNAME"));	    //3
			vpd.put("var4", varOList.get(i).get("LNG").toString());	//4
			vpd.put("var5", varOList.get(i).get("LAT").toString());	//5
			vpd.put("var6", varOList.get(i).get("LEVEL").toString());	//6
			vpd.put("var7", varOList.get(i).get("ID").toString());	//7
			vpd.put("var8", varOList.get(i).getString("PARENTID"));	    //8
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
