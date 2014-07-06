/**
 * 
 */
package me.power.speed.db.mongo.test;

/**
 * @author xuehui.miao
 *
 */
public interface IMongoDao<T> {
	
	public void saveObject(T object);
	
	public T getById(long id);
}
