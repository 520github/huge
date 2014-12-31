package me.power.speed.test.storage.redis;

import java.util.ArrayList;
import java.util.List;

import me.power.speed.test.ConsumerTime.ConsumerTimeHandle;

import org.junit.Test;

public class TestRedisHyperLogLog extends AbstractRedisTest {
	private static int diffCount =0;
	private static int moreMax =0;
	private static int lessMax =0;
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
//		String keyBase = "hll:20141231:";
//		final int uuidNum = 1000;
//		for(int i=0; i< 30; i++) {
//			final String key = keyBase + i;
//			final String datas[] = this.getUUIDArray(uuidNum);
//			this.handleWithConsumerTime(new ConsumerTimeHandle() {
//				public void handle() {
//					redisTest.setValuesToPf(key, datas);
//					redisTest.getAndPrintPfCount(key, uuidNum);
//				}
//			});
//		}
//		this.print(count, true);
		this.cycleSetArrayUUID2HyperLogLog(1000, 30);
	}
	
	@Test
	public void testMulitCycleSetArrayUUID2HyperLogLog() {
		int uuidNums[] = new int[] {50,100,200,500,1000,1500,2000,3000,4000,5000,6000,7000,8000,9000,10000};
		int cycleNum =30;
		for(int uuidNum: uuidNums) {
			this.cycleSetArrayUUID2HyperLogLog(uuidNum,cycleNum);
		}
	}
	
	private void cycleSetArrayUUID2HyperLogLog(final int uuidNum, int cycleNum) {
		String path = "C:\\xuehui\\50-temp\\90-temp\\hll\\";
		final String filePath = path + uuidNum + ".txt";
		String keyPre = "hll:" + this.getTimestampString() + ":";
		for(int i=0; i<cycleNum;i++) {
			final String key = keyPre + i;
			final String datas[] = this.getUUIDArray(uuidNum);
			this.handleWithConsumerTime(new ConsumerTimeHandle() {
				public void handle() {
					redisTest.setValuesToPf(key, datas);
					long result = redisTest.getPfCount(key);
					if(uuidNum != result) {
						diffCount++;
						redisTest.write2File(filePath, key + "->" + result + "->" + uuidNum);
						if(uuidNum - result > 0) {
							int df = uuidNum - (int)result;
							if(df > lessMax) {
								lessMax = df;
							}
						}
						else {
							int df = (int)result - uuidNum;
							if(df > moreMax) {
								moreMax = df;
							}
						}
					}
				}
			});
		}
		this.write2File(filePath, "diff count:" + diffCount);
		this.write2File(filePath, "moreMax:" + moreMax);
		this.write2File(filePath, "lessMax:" + lessMax);
		diffCount =0;
		moreMax =0;
		lessMax = 0;
	}
	
	
}
