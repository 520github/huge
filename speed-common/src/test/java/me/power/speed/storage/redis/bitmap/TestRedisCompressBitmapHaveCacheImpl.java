package me.power.speed.storage.redis.bitmap;

import java.util.ArrayList;
import java.util.List;

import me.power.speed.ConsumerTime;
import me.power.speed.storage.redis.bitmap.impl.RedisCompressBitmapHaveCacheImpl;

import org.junit.Before;
import org.junit.Test;

public class TestRedisCompressBitmapHaveCacheImpl extends
		AbstractRedisBitmapTest {
	
	private String key = "bitmap:compress:cache:20141230:2";
	@Before
	public void before() {
		this.redisBitmap = new RedisCompressBitmapHaveCacheImpl();
	}
	
	@Test
	public void testSetBitmapOffset() {
		int offsets[] = this.getIncreamIntArrays(10001, 1, 100);
		this.testSetBitmapOffset(key, offsets);
	}
	
	@Test
	public void testGetBitmapCount() {
		this.testGetBitmapCount(key);
	}
	
	@Test
	public void testCycleCategorysSetBitmapOffset() {
		int productIds[] = this.getProductIds();
		int gameServers[] = this.getGameServers();
		int partners[] = this.getPartners();
		
		List<String> keys = this.getKeys(productIds, gameServers, partners);
		
		ConsumerTime ct = new ConsumerTime();
		for(String key: keys) {
			int offsets[] = this.getIncreamIntArrays(10000, 1, 100);//10001
			this.testSetBitmapOffset(key, offsets);
		}
		ct.endConsumeTime();
	}
	
	protected List<String> getKeys(int productIds[], int gameServers[], int partners[]) {
		List<String>  keys = new ArrayList<String>();
		for(int productId: productIds) {
			keys.add(String.valueOf(productId));
		}
		for(int productId: productIds) {
			for(int gameServer: gameServers) {
				keys.add(productId + ":" + gameServer);
			}
		}
		for(int productId: productIds) {
			for(int partner: partners) {
				keys.add(productId + ":" + partner);
			}
		}
		
		return keys;
	}
	
	protected int[] getProductIds() {
		return this.getIncreamIntArrays(20, 12000, 1);
	}
	
	protected int[] getGameServers() {
		return this.getIncreamIntArrays(30, 99000, 1);
	}
	
	protected int[] getPartners() {
		return this.getIncreamIntArrays(30, 96000, 1);
	}
}
