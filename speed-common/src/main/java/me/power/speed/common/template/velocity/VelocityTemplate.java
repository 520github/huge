/**
 * 
 */
package me.power.speed.common.template.velocity;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author xuehui.miao
 *
 */
public class VelocityTemplate {
	private static Properties properties=new Properties();
	static {
		setDefaultProperties();
	}
	
	private static VelocityEngine velocityEngine = new VelocityEngine(properties);
	
	public static String getTemplateData(String template, Map<String, Object> context) {
		return getTemplateData(template, getVelocityContextFromMap(context));
	}
	
	public static String getTemplateData(String template, VelocityContext context) {
		StringWriter sw = new StringWriter();
		velocityEngine.mergeTemplate(template, "utf-8", context, sw);
		return sw.toString();
	}
	
	public static String getTemplateDataBySpring(String template, Map<String, Object> model) {
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, model);
	}
	
	private static VelocityContext getVelocityContextFromMap(Map<String, Object> context) {
		VelocityContext vc = new VelocityContext();
		for(String key : context.keySet()) {
			Object value = context.get(key);
			vc.put(key, value);
		}
		
		return vc;
	}
	
	private static void setDefaultProperties() {
		properties.setProperty("resource.loader", "class");
		properties.setProperty("resource.loader", "class");
		properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		//properties.setProperty("input.encoding", "UTF-8");
        //properties.setProperty("output.encoding", "UTF-8");
		 properties.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
	     properties.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
	     properties.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
	}
	
}
