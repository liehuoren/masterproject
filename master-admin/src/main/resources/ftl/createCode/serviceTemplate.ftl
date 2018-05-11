package com.master.service.${packageName}.${objectNameLower}.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.master.dao.DaoSupport;
import com.master.entity.Page;
import com.master.util.PageData;
import com.master.service.${packageName}.${objectNameLower}.${objectName}Manager;

/** 
 * 说明： ${TITLE}
 * 创建人：FLC
 * 创建时间：${nowDate?string("yyyy-MM-dd")}
 * @version
 */
@Service("${objectNameLower}Service")
public class ${objectName}Service implements ${objectName}Manager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("${objectName}Mapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("${objectName}Mapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	@Transactional
	public void edit(PageData pd)throws Exception{
		//先锁行,再修改内容,本方法结束后会将锁解开(在锁行中的时候，另一条锁行查询将会等待。)。
		PageData lockPd = findByIdForLock(pd);
		dao.update("${objectName}Mapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("${objectName}Mapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("${objectName}Mapper.listAll", pd);
	}
		
	/**通过ID获取数据并锁行
	 * @param pd
	 * @throws Exception
	 */
	public PageData findByIdForLock(PageData pd)throws Exception{
		return (PageData)dao.findForObject("${objectName}Mapper.findByIdForLock", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("${objectName}Mapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("${objectName}Mapper.deleteAll", ArrayDATA_IDS);
	}
	
}
