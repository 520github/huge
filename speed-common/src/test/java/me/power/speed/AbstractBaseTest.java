package me.power.speed;

import org.junit.Assert;

import me.power.speed.common.json.JacksonJsonUtil;

public class AbstractBaseTest {
	protected void print(Object obj) {
		try {
			if(obj == null) {
				return;
			}
			String json = JacksonJsonUtil.convertObject2Json(obj);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(obj.toString());
		}
	}
	
	protected void fail(Exception e) {
		e.printStackTrace();
		Assert.fail(e.getMessage());
	}
}
