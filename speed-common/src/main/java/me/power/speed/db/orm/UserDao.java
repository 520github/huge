package me.power.speed.db.orm;

import java.util.List;

import me.power.speed.entity.User;

public interface UserDao {
	
	public void insertUser(User user);
	
	public void batchInsertUser(List<User> userList);
}
