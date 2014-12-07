/**
 * 
 */
package me.power.speed.test;

import org.junit.Assert;

/**
 * @author keke
 * 
 */
public abstract class AbstractTest {
	protected void print(Object obj) {
		try {
			if (obj == null) {
				return;
			}

			if (obj.getClass().isArray()) {
				this.printArrayValue(obj);
				return;
			}

			if (obj instanceof String) {
				System.out.println(obj.toString());
				return;
			}
			
			System.out.println(obj.toString());

//			String json = JacksonJsonUtil.convertObject2Json(obj);
//			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(obj.toString());
		}
	}

	protected void printArrayValue(Object obj) {
		if (obj instanceof String[]) {
			String values[] = (String[]) obj;
			for (String value : values) {
				this.print(value);
			}
		}
	}

	protected void fail(Exception e) {
		e.printStackTrace();
		Assert.fail(e.getMessage());
	}
	
	protected String Int2Binary(int value) {
		return Integer.toBinaryString(value);
	}
	
}
