/**
 * 
 */
package me.power.speed.common.cache.aop;

import java.util.List;

/**
 * @author xuehui.miao
 * 
 */
public class CacheAction {
	/**
	 * value:缓存中的键，${map.name}会动态替换为传入参数map里面的key为name的值。
	 * comdition：缓存执行条件：!map.containsKey
	 * ('execute')表示map中不包含execute这个key的时候才进行缓存操作。 这里面的map是传入的参数名称。
	 * 执行到该方法会自动去缓存里面查找该key，有就直接返回，没有就执行该方法，如果返回值不为空则同时存入缓存并返回结果。
	 */
	@GetDataFromCache(value = "Resource_selectByMap_${map.name}", condition = "!map.containsKey('execute')")
	public List<Object> selectByMap(Object map) {
		// return super.selectByMap(map);
		return null;
	}

	/*
	 * 同样value为缓存中的key，${t.name}会动态替换为update方法传入参数Resource的name字段
	 * comdition：字段作用同上，不演示了
	 */
	@UpdateToCache(value = "Resource_selectByMap_${t.name}")
	public int update(Object t) {
		// return super.update(t);
		return 0;
	}

}
