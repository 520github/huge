package me.power.speed.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SpringContext implements ApplicationContextAware {
	private static ApplicationContext context = null;
	
	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		context = ac;
	}
	
	protected static ApplicationContext getApplicationContext() {
		return context;
	}
	
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
	
	public static SqlMapClient getSqlMapClient() {
		return (SqlMapClient)getBean("sqlMapClient");
	}

}
