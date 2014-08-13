package me.power.speed.common.outofmemory.list;

import java.util.UUID;

public class BigDto {
	private String name = null;
	private int age;
	private long createtime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	
	public static BigDto getBigDto() {
		BigDto bdto = new BigDto();
		bdto.setName(UUID.randomUUID().toString());
		bdto.setAge((int)(Math.random()*100));
		bdto.setCreatetime(System.currentTimeMillis());
		
		return bdto;
	}
}
