package me.power.speed.test.springmodule.cache;

import org.junit.Before;
import org.junit.Test;

import me.power.speed.test.AbstractSpringTest;

//http://blog.csdn.net/pengchua/article/details/4401136
public class TestStudentServiceCache extends AbstractSpringTest {
	private StudentService ss;

	@Before
	public void before() {
		try {
			ss = (StudentService) this.getBean("studentService");
		} catch (Exception e) {
			this.fail(e);
		}
	}

	@Test
	public void testCache() {
		String name;
		System.out.println("第一次访问，没有缓存");
		name = ss.getName();
		System.out.println(name);
		name = ss.getName("Mr");
		System.out.println(name);

		// use change not changed value
		System.out.println("第二次访问，使用缓存");
		ss.changeNameAndNotTellCache("Michael");
		name = ss.getName();
		System.out.println(name);

		name = ss.getName("Mr");
		System.out.println(name);

		// update cache
		System.out.println("清除缓存后，再次访问 ");
		ss.setName("Michael");
		name = ss.getName();
		System.out.println(name);

		name = ss.getName("Mr");
		System.out.println(name);
	}

}
