package me.power.speed.storage.redis.bitmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.power.speed.ConsumerTime;
import me.power.speed.base.hashcode.HashAlgorithms;
import me.power.speed.storage.redis.RedisUtil;
import me.power.speed.storage.redis.bitmap.impl.RedisCompressBitmapHaveCacheImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRedisCompressBitmapHaveCacheImpl extends
		AbstractRedisBitmapTest {
	
	private String key = "bitmap:compress:cache:20150105:11";
	@Before
	public void before() {
		this.redisBitmap = new RedisCompressBitmapHaveCacheImpl();
	}
	
	@Test
	public void testSetBitmapOffset() {
		//int offsets[] = this.getIncreamIntArrays(10001, 1, 100);
		//int offsets[] = this.getUUIDHashCodeArrays(100001);
		int offsets[] = this.getRandomIntArrays(1000000, 100000000);
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
			int offsets[] = this.getIncreamIntArrays(101, 1, 100);//10001
			this.testSetBitmapOffset(key, offsets);
		}
		ct.endConsumeTime();
	}
	
	@Test
	public void testMurMurHashCode() {
		int cycleNum = 10000;
		Set<Integer> set = new HashSet<Integer>();
		int maxValue = 1040187422;
		
		String tt = this.getUUID();
		System.out.println("tt:" + tt);
		
		for(int i=0;i<cycleNum;i++) {
			String uuid = this.getUUID();
			//int result = this.getMurMurHashCodeByRedis(uuid);
			//int result = this.hashCodeObject(uuid);
			int result = HashAlgorithms.JSHash(uuid);
			if(result > maxValue) {
				this.print("maxValue:" + result);
			}
			//System.out.println(result);
			set.add(Math.abs(result));
		}
		System.out.println(set.size());
		Assert.assertEquals(cycleNum, set.size());
	}
	
	@Test
	public void testGetInfo() {
		String info = RedisUtil.info();
		this.print(info);
		long usedMemory = RedisUtil.getRedisCurrentUsedMemory();
		this.print(String.valueOf(usedMemory));
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
		return this.getIncreamIntArrays(500, 12000, 1);
	}
	
	protected int[] getGameServers() {
		return this.getIncreamIntArrays(60, 99000, 1);
	}
	
	protected int[] getPartners() {
		return this.getIncreamIntArrays(60, 96000, 1);
	}
}
