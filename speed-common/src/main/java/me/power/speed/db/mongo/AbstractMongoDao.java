/**
 * 
 */
package me.power.speed.db.mongo;

import java.lang.reflect.ParameterizedType;

import me.power.speed.db.mongo.test.IMongoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * 抽象处理类
 * @author xuehui.miao
 *
 */
public abstract class AbstractMongoDao<T> implements IMongoDao<T> {
	@Autowired
	private MongoTemplate mongoTemplate; 
	
	protected Class<T> entityClass = null;
	
	public AbstractMongoDao() {
		this.setEntityClass();
	}
	
	@SuppressWarnings("unchecked")
	protected void setEntityClass() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void saveObject(T t) {
		mongoTemplate.save(t);
	}
	
	public T getById(long id) {
		return this.getMongoTemplate().findOne(this.getPkQuery(id), entityClass);
	}
	
	protected Query getPkQuery(long id) {
		Query pkQuery = new Query();
		pkQuery.addCriteria(Criteria.where("id").is(id));
		return pkQuery;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
}
