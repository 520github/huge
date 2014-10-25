/**
 * 
 */
package me.power.speed.common.template.velocity;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.AbstractBaseTest;

/**
 * @author xuehui.miao
 *
 */
public class TestVelocityTemplate extends AbstractBaseTest {
	
	private String template;
	private Map<String,Object> model = new HashMap<String, Object>();
	
	@Before
	public void before() {
		this.template = "template/template.vm";
		model.put("name", "this is my name for time");
	}
	
	@Test
	public void testVelocityTemplate() {
		String result = VelocityTemplate.getTemplateData(template, model);
		this.print(result);
		
		result = VelocityTemplate.getTemplateDataBySpring(template, model);
		this.print(result);
	}
}
