package me.power.speed.test.storage.redis;

import java.util.ArrayList;
import java.util.List;

import me.power.speed.test.ConsumerTime.ConsumerTimeHandle;

import org.junit.Test;

public class TestRedisHyperLogLog extends AbstractRedisTest {
	private String key = "hll-1";
	
	@Test
	public void testSetValueToHyperLogLog() {
		RedisUtil.setValuesToPf(key, "300004", "300098", "300123");
	}
	
	@Test
	public void testSetSingleCycleValueToHyperLogLog() {
		int start = 0;
		int count = 10000;
		String key = "hll-7";
		for(int i=start; i< start+count; i++) {
			RedisUtil.setValuesToPf(key, String.valueOf(i));
		}
	}
	
	@Test
	public void testSetMulitCycleValueToHyperLogLog() {
		String key = "hll-4";
		int i = 0;
		int j =0;
		for(i=1; i<= 100; i++) {
			for(j=1;j<=100;j++) {
				int result = i*j;
				RedisUtil.setValuesToPf(key, String.valueOf(result));
			}
		}
	}
	
	@Test
	public void testSetCycleToHyperLogLog() {
		String key3 = "hll-3";
		int i = 0;
		int j =0;
		for(i=1; i< 100; i++) {
			List<String> dataList = new ArrayList<String>();
			for(j=1;j<100;j++) {
				int result = i*j;
				dataList.add(String.valueOf(result));
			}
			RedisUtil.setValuesToPf(key3, dataList.toArray(new String[]{}));
		}
	}
	
	@Test
	public void testGetCountFromHyperLogLog() {
		String key = "hll-7";
		long result = RedisUtil.getCountFromPf(key);
		this.print(result);
	}
	
	@Test
	public void testCycleSetUUID2HyperLogLog() {
		key = "hll:20141226:06";
		final List<String> dataList = this.getUUIDList(100000);
		
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				for(String data: dataList) {
					redisTest.setValuesToPf(key, data);
				}
				redisTest.getAndPrintPfCount(key);
			}
		});
	}
	
	@Test
	public void testCycleSetArrayUUID2HyperLogLog() {
		key = "hll:20141229:11";
		final String datas[] = this.getUUIDArray(50000);
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				redisTest.setValuesToPf(key, datas);
				redisTest.getAndPrintPfCount(key);
			}
		});
	}
	
	
}
