package me.power.speed.entity;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	private String userName ;
	private String password ;
	private String email;
	private Date createTime;
	private Timestamp lastModifyTime;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public long getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(long createTime) {
//		this.createTime = createTime;
//	}
//	public long getLastModifyTime() {
//		return lastModifyTime;
//	}
//	public void setLastModifyTime(long lastModifyTime) {
//		this.lastModifyTime = lastModifyTime;
//	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Timestamp getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
}
