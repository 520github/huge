/**
 * 
 */
package me.power.speed.common.jmx;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import clojure.main;

import me.power.speed.common.jmx.define.Hello;

/**
 * @author xuehui.miao
 *
 */
public class RegisterMBean {
	public static void registerMBean() throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, InterruptedException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		//MBeanServer mbs = MBeanServerFactory.createMBeanServer();
		ObjectName name = new ObjectName("me.power.speed.common.jmx.define:type=Hello");
		//ObjectName name = new ObjectName("Hello:type=myfirstBean");
		Hello mbean = new Hello();
		mbs.registerMBean(mbean, name);
	}
	
	public static void main(String[] args) {
		try {
			RegisterMBean.registerMBean();
			System.out.println("Waiting forever...");
			Thread.sleep(Long.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
