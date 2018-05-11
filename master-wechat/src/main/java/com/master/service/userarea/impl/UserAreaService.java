package com.master.service.userarea.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.master.dao.DaoSupport;
import com.master.entity.Page;
import com.master.service.userarea.UserAreaManager;
import com.master.util.Logger;
import com.master.util.PageData;

/** 
 * 说明： 地区维护
 * 创建人：FLC
 * 创建时间：2017-01-09
 * @version
 */
@Service("userareaService")
public class UserAreaService implements UserAreaManager{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("UserAreaMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("UserAreaMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("UserAreaMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("UserAreaMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("UserAreaMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("UserAreaMapper.findById", pd);
	}
	/**
	 * 根据父级ID查询
	 */
	@Override
	public List<PageData> findByParentidlist(Page page) throws Exception {
		return (List<PageData>)dao.findForList("UserAreaMapper.findByParentidlist", page);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("UserAreaMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/**
	 * 手动处理百度接口获取的区域信息与系统中的区域信息关系映射
	 * @return
	 * @throws Exception 
	 */
	public PageData autoRelational(HttpSession session) throws Exception{
		/*PageData pd = new PageData();
		session.setAttribute("autoRelational", "executing");
		logger.info("手动处理百度接口获取的区域信息与系统中的区域信息关系映射");
		logger.info("获取所有三级区域信息中……");
		List<Area> listThird = (List<Area>) dao.findForList("UserAreaMapper.getThirdAreaList", pd);
		logger.info("获取所有三级区域信息条数："+(null==listThird?0:listThird.size()));
		
		List<PageData> relationalList = new ArrayList<PageData>();
		PageData rePd = null;
		
		logger.info("构建参数调百度接口中...");
		
		String url = "http://api.map.baidu.com/geocoder/v2/";
		for (Area area : listThird) {
			
			//调用百度接口将坐标转换为区域
			String resource = HttpRequest.sendGet(url, "location="+area.getLat()+","+area.getLng()+"&output=json&pois=3&ak=5WEwS3qWvcNGbeGu7ty77cdx");
			
			String regex = "\"\\d{6}\"";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(resource);
			if(matcher.find()){
				
				//调用接口返回值匹配到地区编号信息
				String relation_id = matcher.group().replace("\"", "");
				if(area.getId().intValue()!=Integer.valueOf(relation_id).intValue()){
					logger.info("成功>>调用接口获取到的["+area.getAreaname()+"],坐标[lat:"+area.getLat()+",lng:"+area.getLng()+"],转换的区域编号为:"+relation_id+"与系统中的区域编号["+area.getId()+"]不一致，需要做匹配");
					rePd = new PageData();
					rePd.put("relational_id", relation_id);
					rePd.put("area_id", String.valueOf(area.getId()));
					relationalList.add(rePd);
				}
			}else{
				logger.info("失败>>调用接口获取到的["+area.getAreaname()+"],坐标[lat:"+area.getLat()+",lng:"+area.getLng()+"],未采集到地区信息，接口返回结果为："+(StringUtils.isEmpty(resource)?"null":resource));
			}
			
			
		}
		
		PageData seachSource = null;
		int updateCount = 0;
		int saveCount = 0;
		for (PageData pageData : relationalList) {
			seachSource = (PageData) dao.findForObject("UserAreaMapper.getRelationalById", pageData);
			if(null!=seachSource){
				dao.update("UserAreaMapper.editRelational", pageData);
				updateCount++;
			}else{
				dao.save("UserAreaMapper.saveRelational", pageData);
				saveCount++;
			}
		}
		logger.info("共更新了"+updateCount+"条关系映射");
		logger.info("共新增了"+saveCount+"条关系映射");
		pd.put("updateCount", updateCount);
		pd.put("saveCount", saveCount);
		session.removeAttribute("autoRelational");
		return pd;*/
		return null;
	}

}

