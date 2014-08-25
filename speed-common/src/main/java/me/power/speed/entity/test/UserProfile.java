package me.power.speed.entity.test;

public class UserProfile {
	public String userID = null;
	public Integer level;
	public Integer sex;
	public Integer age;
	public Integer agelevel;
	public Integer firstbuylevel;

	public Integer getFirstbuylevel() {
		return firstbuylevel;
	}

	public void setFirstbuylevel(Integer firstbuylevel) {
		this.firstbuylevel = firstbuylevel;
	}

	public Integer getAgelevel() {
		return agelevel;
	}

	public void setAgelevel(Integer agelevel) {
		this.agelevel = agelevel;
	}

	public String getUserID() {
		return userID;
	}

	public Integer getLevel() {
		return level;
	}

	public Integer getSex() {
		return sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserProfile [userID=" + userID + ", level=" + level + ", sex=" + sex + ", age=" + age + ", agelevel=" + agelevel + ", firstbuylevel="
				+ firstbuylevel + "]";
	}
}
