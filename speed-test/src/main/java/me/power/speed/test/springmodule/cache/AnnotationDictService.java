package me.power.speed.test.springmodule.cache;

import org.springmodules.cache.annotations.CacheFlush;
import org.springmodules.cache.annotations.Cacheable;

public class AnnotationDictService {
	
	@Cacheable(modelId="dictCaching")
	public String getDictValue(String key) {
		System.out.println("获取超级结果");
		return "key:" + key;
	}
	
	@CacheFlush(modelId="dictFlushing")
	public void setDictValue(String key, String value) {
		System.out.println("put " + key + " to " + value);
	}
}
