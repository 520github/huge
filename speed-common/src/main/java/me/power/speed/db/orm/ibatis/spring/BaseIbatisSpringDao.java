package me.power.speed.db.orm.ibatis.spring;

import me.power.speed.common.spring.SpringContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

public abstract class BaseIbatisSpringDao extends SqlMapClientDaoSupport {
	@Autowired
	protected SqlMapClient sqlMapClient;
	
	public BaseIbatisSpringDao() {
		this.setSqlMapClient(SpringContext.getSqlMapClient());
	}
	
	public void insertObject(String id, Object object) {
		try {
			this.sqlMapClient.insert(id, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
