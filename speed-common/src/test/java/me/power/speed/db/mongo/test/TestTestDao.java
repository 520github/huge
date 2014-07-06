/**
 * 
 */
package me.power.speed.db.mongo.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.power.speed.AbstractTest;

/**
 * @author xuehui.miao
 *
 */
public class TestTestDao extends AbstractTest {
	@Autowired
	private TestDao testDao;
	private me.power.speed.entity.test.Test test;
	
	@Before
	public void before() {
		test = new me.power.speed.entity.test.Test();
		test.setName("test name");
		test.setAge(102);
	}
	
	@Test
	public void testSaveObject() {
		testDao.saveObject(test);
	}
	
	@Test
	public void testGetById() {
		long id = 0;
		test = testDao.getById(id);
		Assert.assertNotNull(test);
		
	}
}
