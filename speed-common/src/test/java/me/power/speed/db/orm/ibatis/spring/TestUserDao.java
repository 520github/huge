package me.power.speed.db.orm.ibatis.spring;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void testBatchInsertUserByIbatisSql() {
		try {
			List<User> userList = new ArrayList<User>();
			userList.add(user);
			userDao.batchInsertUserByIbatisSql(userList);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testBatchInsertUser() {
		try {
			userDao.batchInsertUser(this.getUserList());
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	protected List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		for(int i=0; i<1; i++) {
			User user = new User();
			user.setUserName("userName_" + i);
			user.setPassword("password_"+i);
			user.setEmail("email_"+i);
			user.setCreateTime(System.currentTimeMillis());
			user.setLastModifyTime(user.getCreateTime());
			userList.add(user);
		}
		return userList;
	}
}
