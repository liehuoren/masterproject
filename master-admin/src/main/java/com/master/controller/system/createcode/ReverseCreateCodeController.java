package com.master.controller.system.createcode;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.master.controller.base.BaseController;
import com.master.util.AppUtil;
import com.master.util.DbFH;
import com.master.util.Jurisdiction;
import com.master.util.PageData;

/** 
 * 类名称： 反向生成
 * 创建人：FLC
 * 修改时间：2016年4月15日
 * @version
 */
@Controller
@RequestMapping(value="/recreateCode")
public class ReverseCreateCodeController extends BaseController {
	@Value("${url}")
    private String url;
    @Value("${username}")
    private String userName;
    @Value("${password}")
    private String password;
	
	String menuUrl = "recreateCode/list.do"; //菜单地址(权限用)
	
	/**列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception{
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){} 	//校验权限
		System.out.println(url+","+userName+","+password);
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/createcode/recreatecode_list");
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	 /**列出所有表
	 * @param
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/listAllTable")
	@ResponseBody
	public Object listAllTable(){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		PageData pd = new PageData();		
		pd = this.getPageData();
		Map<String,Object> map = new HashMap<String,Object>();
		List<PageData> pdList = new ArrayList<PageData>();
		List<String> tblist = new ArrayList<String>();
		try {
			
			//获取数据库连接信息
			getJdbcInfoByProties(pd);
			
			Object[] arrOb = DbFH.getTables(pd);
			tblist = (List<String>)arrOb[1];
			pd.put("msg", "ok");
		} catch (ClassNotFoundException e) {
			pd.put("msg", "no");
			e.printStackTrace();
		} catch (SQLException e) {
			pd.put("msg", "no");
			e.printStackTrace();
		}
		pdList.add(pd);
		map.put("tblist", tblist);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	/**
	 * 将配置文件中的jdbc连接信息组装成到pd对象
	 * @param pd
	 */
	private void getJdbcInfoByProties(PageData pd) {
		if(null==pd)pd=getPageData();
		//获取连接信息
		pd.put("dbtype", "mysql");//数据库类型
		pd.put("username", userName);//用户名
		pd.put("password", password);
		
		//通过连接字符串将数据库url、端口号、数据库名称获取到
		String remove_head = url.replaceAll("jdbc:mysql:\\/\\/", "");//	去掉"jdbc:mysql://"
		String address = remove_head.substring(0, remove_head.indexOf(":"));//获取连接地址
		String remove_address = remove_head.replaceAll(address, "");//去掉连接地址
		String port = remove_address.substring(1, remove_address.indexOf("/"));//获取端口号。连接字符串":port/"中的port,但":"不被包含，所以从1开始,以"/"结尾
		String dbName = remove_address.substring(remove_address.indexOf("/")+1,remove_address.indexOf("?"));//获取数据库名称
		pd.put("dbAddress", address);//数据库连接地址
		pd.put("dbport", port);//端口
		pd.put("databaseName", dbName);//数据库名
		
	}

	/**去代码生成器页面(进入弹窗)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goProductCode")
	public ModelAndView goProductCode() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String fieldType = "";
		StringBuffer sb = new StringBuffer("");
		
		//获取数据库连接信息
		getJdbcInfoByProties(pd);
		
		List<Map<String,String>> columnList = DbFH.getFieldParameterLsit(DbFH.getFHCon(pd),pd.getString("table")); //读取字段信息
		for(int i=0;i<columnList.size();i++){
			Map<String,String> fmap = columnList.get(i);
			sb.append(fmap.get("fieldNanme").toString().toUpperCase());					//字段名称
			sb.append(",fh,");
			fieldType = fmap.get("fieldType").toString().toLowerCase();					//字段类型
			if(fieldType.contains("int")){
				sb.append("Integer");
			}else if(fieldType.contains("NUMBER")){
				if(Integer.parseInt(fmap.get("fieldSccle")) > 0){
					sb.append("Double");
				}else{
					sb.append("Integer");
				}
			}else if(fieldType.contains("double") || fieldType.contains("numeric")){
				sb.append("Double");
			}else if(fieldType.contains("date")){
				sb.append("Date");
			}else{
				sb.append("String");
			}
			sb.append(",fh,");
			sb.append(fmap.get("fieldNanme").toString().toUpperCase());														//备注
			sb.append(",fh,");
			sb.append("是");																//是否前台录入
			sb.append(",fh,");
			sb.append("无");																//默认值
			sb.append(",fh,");
			sb.append(fmap.get("fieldLength").toString());								//长度
			sb.append(",fh,");
			sb.append(fmap.get("fieldSccle").toString());								//小数点右边的位数
			sb.append("___salt4");
		}
		pd.put("FIELDLIST", sb.toString());
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.setViewName("system/createcode/productCode");
		return mv;
	}
	
}