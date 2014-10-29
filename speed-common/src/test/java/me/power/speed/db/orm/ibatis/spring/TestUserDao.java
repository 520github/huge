package me.power.speed.db.orm.ibatis.spring;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.power.speed.AbstractTest;
import me.power.speed.ConsumerTime;
import me.power.speed.common.util.DateUtil;
import me.power.speed.db.orm.UserDao;
import me.power.speed.entity.User;

public class TestUserDao extends AbstractTest {
	@Autowired
	private UserDao userDao;
	private User user;
	
	@Before
	public void before() {
		user = new User();
		String index = "2";
		user.setUserName("userName" + index);
		user.setPassword("password" + index);
		user.setEmail("email" + index);
		user.setCreateTime(this.getCurrentDate());
		user.setLastModifyTime(this.getCurrentTimestamp());
	}
	
	@Test
	public void testOneInsertUser() {
		try {
			user.setCreateTime(DateUtil.parseString2UTCDate("2014-10-28T03:34:40Z"));
			userDao.insertUser(user);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testInsertUser() {
		try {
			ConsumerTime ct = new ConsumerTime();
			for(int i = 0; i <1; i ++) {
				userDao.insertUser(this.getUser(i));
			}
			ct.endConsumeTime();
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testBatchInsertUserByIbatisSql() {
		try {
			ConsumerTime ct = new ConsumerTime();
			for(int i=0; i<100;i++) {
				userDao.batchInsertUserByIbatisSql(this.getUserList(i, 1000));
			}
			ct.endConsumeTime();
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testRepeatBatchInsertUserByIbatisSql() {
		try {
			List<User> userList = new ArrayList<User>();
			userList.add(this.getUser(1));
			userList.add(this.getUser(2));
			userList.add(this.getUser(3));
			userList.add(this.getUser(1));
			userList.add(this.getUser(4));
			userList.add(this.getUser(5));
			userDao.batchInsertUserByIbatisSql(userList);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testBatchInsertUser() {
		try {
			ConsumerTime ct = new ConsumerTime();
			for(int i=0; i<5;i++) {
				userDao.batchInsertUser(this.getUserList(i, 1000));
			}
			ct.endConsumeTime();
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testRepeatBatchInsertUser() {
		try {
			List<User> userList = new ArrayList<User>();
			userList.add(this.getUser(1));
			userList.add(this.getUser(2));
			userList.add(this.getUser(3));
			//userList.add(this.getUser(1));
			userList.add(this.getUser(4));
			userList.add(this.getUser(5));
			userDao.batchInsertUser(userList);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	protected List<User> getUserList(int index, int limit) {
		List<User> userList = new ArrayList<User>();
		for(int i=0; i<limit; i++) {
			userList.add(this.getUser(index*limit+i));
		}
		return userList;
	}
	
	protected User getUser(int i) {
		User user = new User();
		user.setUserName("userName_" + i);
		user.setPassword("password_"+i);
		user.setEmail("email_"+i);
		user.setCreateTime(this.getCurrentDate());
		try {
			user.setCreateTime(DateUtil.parseString2UTCDate("2014-10-28T03:34:40Z"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setLastModifyTime(this.getCurrentTimestamp());
		return user;
	}
}
