package com.master.service.userarea;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.master.entity.Page;
import com.master.util.PageData;

/** 
 * 说明： 地区维护接口
 * 创建人：FLC
 * 创建时间：2017-01-09
 * @version
 */
public interface UserAreaManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**根据父级ID查询
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> findByParentidlist(Page page)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;

	/**
	 * 手动处理百度接口获取的区域信息与系统中的区域信息关系映射
	 * @return
	 */
	public PageData autoRelational(HttpSession session) throws Exception;
	
}

