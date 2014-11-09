/**
 * 
 */
package me.power.speed.common.cache.dynamic;

/**
 * @author xuehui.miao
 *
 */
public class CacheData {
	private Object data;
	private long time;
	private int count;
	
	public CacheData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void addCount() {
		this.count++;
	}
	
}
