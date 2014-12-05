/**
 * 
 */
package me.power.speed.test.cache.dict;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rx.Observable;

import com.github.davidmoten.rx.jdbc.Database;

/**
 * @author xuehui.miao
 *
 */
public abstract class AbstractSqlDictCache<T> implements DictCache<T> {
	private String url = "jdbc:mysql://10.10.0.126:3307/gametenddata";
	private String username = "gametenddata";
	private String password = "gamepasswd";
	private Database db = new Database(url, username, password);
	
	protected Set<String> initKeys = new HashSet<String>();
	protected String initLoadSql;
	
	protected Class<T> entityClass = null;
	
	private Map<String,T> cacheProvider = new HashMap<String, T>();
	
	public AbstractSqlDictCache() {
		this.setEntityClass();
	}
	
	@SuppressWarnings("unchecked")
	protected void setEntityClass() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/* (non-Javadoc)
	 * @see me.power.speed.test.cache.dict.DictCache#initCache(java.lang.String)
	 */
	public void initCache() {
		List<T> resultList = db.select(initLoadSql).autoMap(entityClass).toList().toBlocking().single();
		for(T t: resultList) {
			if(t instanceof Map) {
				String keyValue = null;
				for(String key: initKeys) {
					keyValue = keyValue + ((Map) t).get(key);
				}
				cacheProvider.put(keyValue, t);
			}
			else {
				String keyValue = "";
				for(String key: initKeys) {
					try {
						keyValue = keyValue + t.getClass().getDeclaredField(key).get(t);
						System.out.println(keyValue);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//System.out.println(keyValue);
				cacheProvider.put(keyValue, t);
			}
		}
	}

	/* (non-Javadoc)
	 * @see me.power.speed.test.cache.dict.DictCache#getCache(java.lang.String)
	 */
	public T getCache(String key) {
		// TODO Auto-generated method stub
		return cacheProvider.get(key);
	}

	/* (non-Javadoc)
	 * @see me.power.speed.test.cache.dict.DictCache#getCacheWhenEmptyAndInsert(java.lang.String)
	 */
	public T getCacheWhenEmptyAndInsert(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see me.power.speed.test.cache.dict.DictCache#putCache(java.lang.String, java.lang.Object)
	 */
	public boolean putCache(String key, Object value) {
		// TODO Auto-generated method stub
		return false;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setDb(Database db) {
		this.db = db;
	}

	public Set<String> getInitKeys() {
		return initKeys;
	}

}
