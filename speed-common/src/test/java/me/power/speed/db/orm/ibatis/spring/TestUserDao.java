package me.power.speed.db.orm.ibatis.spring;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.power.speed.AbstractTest;
import me.power.speed.db.orm.UserDao;
import me.power.speed.entity.User;

public class TestUserDao extends AbstractTest {
	@Autowired
	private UserDao userDao;
	private User user;
	
	@Before
	public void before() {
		user = new User();
		user.setUserName("userName");
		user.setPassword("password");
		user.setEmail("email");
		user.setCreateTime(System.currentTimeMillis());
		user.setLastModifyTime(user.getCreateTime());
	}
	
	@Test
	public void testInsertUser() {
		try {
			userDao.insertUser(user);
		} catch (Exception e) {
			this.fail(e);
		}
	}
}
