/**
 * 
 */
package me.power.speed.db.mongo.test.impl;

import org.springframework.stereotype.Repository;

import me.power.speed.db.mongo.AbstractMongoDao;
import me.power.speed.db.mongo.test.TestDao;
import me.power.speed.entity.test.Test;

/**
 * @author xuehui.miao
 *
 */
@Repository
public class TestDaoImpl extends AbstractMongoDao<Test> implements TestDao {

	/* (non-Javadoc)
	 * @see me.power.speed.db.mongo.test.IMongoDao#saveObject(java.lang.Object)
	 */
	public void saveObject(Test object) {
		super.saveObject(object);
	}

	/* (non-Javadoc)
	 * @see me.power.speed.db.mongo.test.IMongoDao#getById(long)
	 */
	public Test getById(long id) {
		return super.getById(id);
	}

}
