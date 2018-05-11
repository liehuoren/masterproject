package com.test.service.area;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.master.service.area.AreaManager;
import com.master.util.PageData;
import com.test.base.BaseTest;

public class AreaServiceTest extends BaseTest {
	
	@Resource(name="areaService")
	private AreaManager areaService;
	
	@Test
	public void find() throws Exception{
		List<PageData> list = areaService.listAll(new PageData());
		logger.info("=============================================size:"+list.size());
	}
}
