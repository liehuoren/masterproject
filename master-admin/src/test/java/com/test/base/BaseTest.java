package com.test.base;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath*:/spring/*.xml",
	"classpath*:/mybatis/mybatis-config.xml"
	})
public class BaseTest {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	//前置代码
	@Before
	public void init(){
		logger.debug("执行了前置代码 。");
	}
	
	//后置代码 
	@After
	public void after(){
		logger.debug("执行了后置代码。");
	}
	
}
