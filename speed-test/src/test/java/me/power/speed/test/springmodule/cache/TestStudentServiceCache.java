package me.power.speed.test.springmodule.cache;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.test.AbstractSpringTest;

//http://blog.csdn.net/pengchua/article/details/4401136
public class TestStudentServiceCache extends AbstractSpringTest {
	private IStudentService ss;

	@Before
	public void before() {
		try {
			ss = (IStudentService)this.getBean("studentService11");
		} catch (Exception e) {
			this.fail(e);
		}
	}

	@Test
	public void testCache() {
		String name;
		System.out.println("111");
		name = ss.getName();
		System.out.println(name);
		name = ss.getName("Mr");
		System.out.println(name);

		// use change not changed value
		System.out.println("222");
		ss.changeNameAndNotTellCache("Michael");
		name = ss.getName();
		System.out.println(name);

		name = ss.getName("Mr");
		System.out.println(name);

		// update cache
		System.out.println("3333");
		ss.setName("Michael");
		name = ss.getName();
		System.out.println(name);

		name = ss.getName("Mr");
		System.out.println(name);
	}

}
