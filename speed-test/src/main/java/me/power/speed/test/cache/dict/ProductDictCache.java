/**
 * 
 */
package me.power.speed.test.cache.dict;

import java.util.Map;

/**
 * @author xuehui.miao
 *
 */
public class ProductDictCache extends AbstractSqlDictCache<Product> {//Map<String, String>
	
	public ProductDictCache() {
		super();
	}
	
	public void defineInitKeys() {
		this.getInitKeys().add("productid");
	}
	
	public void initCache() {
		this.initLoadSql = "select productid,productname from product where isdeleted=0";
		super.initCache();
	}
}
