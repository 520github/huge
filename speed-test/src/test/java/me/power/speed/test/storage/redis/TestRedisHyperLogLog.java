package me.power.speed.test.storage.redis;

import java.util.ArrayList;
import java.util.List;

import me.power.speed.test.ConsumerTime.ConsumerTimeHandle;

import org.junit.Test;

public class TestRedisHyperLogLog extends AbstractRedisTest {
	private static int diffCount =0;
	private static int moreMax =0;
	private static int lessMax =0;
	private static int avg = 0;
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
	public void testSetSameUUID2HyperLogLog() {
		final String key = "hll:same:20150104:02";
		final String datas[] = this.getSameUUIDArray(100000);
		this.handleWithConsumerTime(new ConsumerTimeHandle() {
			public void handle() {
				redisTest.setValuesToPf(key, datas);
				redisTest.getAndPrintPfCount(key);
			}
		});
	}
	
	@Test
	public void testCycleArrayUUID2Set() {
		int uuidNum = 10;
		this.cycleArrayUUID2Set(uuidNum);
	}
	
	@Test
	public void testMulitCycleArrayUUID2Set() {
		int uuidNums[] = new int[] {50,100,200,500,1000,1500,2000,3000,4000,5000,6000,7000,8000,9000,10000,20000,50000,80000,100000,300000,500000,800000};
		for(int uuidNum: uuidNums) {
			this.cycleArrayUUID2Set(uuidNum);
		}
	}
	
	private void cycleArrayUUID2Set(int uuidNum) {
		final boolean isBatch = true;
		final String datas[] = this.getUUIDArray(uuidNum);
		final String key = "key:set:uuid:" + uuidNum;
		this.handleWithConsumerTimeAndRedisMemory(new ConsumerTimeHandle() {
			public void handle() {
				if(isBatch) {
					RedisUtil.addValuesToSet(key, datas);
				}
				else {
					for(String data: datas) {
						RedisUtil.addValuesToSet(key, data);
					}
				}
			}
		},1);
	}
	
	@Test
	public void testCycleSetArrayUUID2HyperLogLog() {
		for(int i=0;i<1;i++) {
			this.cycleSetArrayUUID2HyperLogLog(1, 30);
		}
	}
	
	@Test
	public void testMulitCycleSetArrayUUID2HyperLogLog() {
		int uuidNums[] = new int[] {20,30,40,50,100,200,500,1000,1500,2000,3000,4000,5000,6000,7000,8000,9000,10000,20000,50000,80000,100000,300000,500000,800000};
		int cycleNum =30;
		for(int uuidNum: uuidNums) {
			this.cycleSetArrayUUID2HyperLogLog(uuidNum,cycleNum);
		}
	}
	
	private void cycleSetArrayUUID2HyperLogLog(final int uuidNum, int cycleNum) {
		long beforeUsedMemory = this.getRedisUsedMemory();
		String ts = this.getTimestampString().replaceAll(" ", "-").replaceAll(":", "-");
		String path = "C:\\xuehui\\50-temp\\90-temp\\hll\\";//+ ts + "\\";
		final String filePath = path + uuidNum + ".txt";
		String keyPre = "hll:12347:" + ts + ":" + uuidNum;
		for(int i=0; i<cycleNum;i++) {
			final String key = keyPre + i;
			final String datas[] = this.getUUIDArray(uuidNum);
			this.handleWithConsumerTime(new ConsumerTimeHandle() {
				public void handle() {
					redisTest.setValuesToPf(key, datas);
					long result = redisTest.getPfCount(key);
					
					if(uuidNum != result) {
						diffCount++;
						avg = avg + Math.abs(uuidNum - (int)result);
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
		long afterUsedMemory = this.getRedisUsedMemory();
		long usedMemory = afterUsedMemory - beforeUsedMemory;
		this.write2File(filePath, "used total memory:" + usedMemory + ",per used memory:" + usedMemory/cycleNum);
		this.write2File(filePath, "diff count:" + diffCount);
		this.write2File(filePath, "moreMax:" + moreMax + ", percent:" + this.getPercent(moreMax, uuidNum));
		this.write2File(filePath, "lessMax:" + lessMax + ", percent:" + this.getPercent(lessMax, uuidNum));
		avg = avg /diffCount;
		this.write2File(filePath, "avg:" + avg + ", avg:" + this.getPercent(avg, uuidNum));
		diffCount =0;
		moreMax =0;
		lessMax = 0;
		avg = 0;
	}
	
	private String getPercent(int fz, int fm) {
		return String.valueOf((double)((double)fz/(double)fm)*100) + "%";
	}
	
	
}
