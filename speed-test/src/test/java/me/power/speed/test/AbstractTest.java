/**
 * 
 */
package me.power.speed.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.power.speed.test.ConsumerTime.ConsumerTimeHandle;

import org.junit.Assert;
import com.alisoft.nano.bench.Nano;

/**
 * @author keke
 * 
 */
public abstract class AbstractTest {
	
	protected int measurements = 1;//并发数
	protected int threads = 1;//线程数
	protected int SerialTimes = 1;//循环次数
	protected String title = "";
	protected boolean isPrint = false;
	protected static Set<Integer> offsetSets = new HashSet<Integer>();
	protected static List<Integer> offsetLists = new ArrayList<Integer>();
	
	protected void handleWithConsumerTime(ConsumerTimeHandle handle) {
		ConsumerTime ct = new ConsumerTime();
		handle.handle();
		ct.endConsumeTime();
	}
	
	protected void runDefaultByMilitThread(Runnable runnable) {
		this.runByMulitThread(measurements, threads, SerialTimes, title, runnable);
	}
	
	protected void runByMulitThread(int measurements, int threads, int SerialTimes,String title, Runnable runnable) {
		Nano.bench().measurements(measurements).threads(threads).measure(title, runnable);
	}
	
	protected List<Integer> getSortOffsetListByOffsetArray(int offsets[]) {
		this.setOffsetsToList(offsets);
		Collections.sort(offsetLists);
		return offsetLists;
	}
	
	protected void setOffsetsToList(int offsets[]) {
		for(int offset: offsets) {
			this.setOffsetToList(offset);
		}
	}
	
	protected void setOffsetToList(int offset) {
		offsetLists.add(offset);
	}
	
	public static List<Integer> getOffsetLists() {
		return offsetLists;
	}

	protected void setOffsetToSet(int offset) {
		offsetSets.add(offset);
	}
	
	public static Set<Integer> getOffsetSets() {
		return offsetSets;
	}

	protected void print(Object obj) {
		this.print(obj, this.isPrint);
	}
	
	protected List<Integer> getRandomIntegerList(int cycleNum, int baseNum) {
		ConsumerTime ct = new ConsumerTime();
		List<Integer> resultList = new ArrayList<Integer>();
		for(int i=0;i<cycleNum;i++) {
			double d = Math.random();
			int result = (int)(d*d*d*baseNum);
			this.print(result, true);
			resultList.add(result);
		}
		ct.endConsumeTime();
		this.print("random size:" + resultList.size(), true);
		return resultList;
	}
	
	protected int[] getRandomIntArrays(int cycleNum, int baseNum) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			double d = Math.random();
			int result = (int)(d*d*d*baseNum);
			this.print(result, false);
			resultArrays[i] = result;
		}
		ct.endConsumeTime();
		this.print("random int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	protected int[] getRandomRangeIntArrays(int cycleNum, int baseNum) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			double d = Math.random()+1;
			int result = (int)(d*baseNum);
			this.print(result, false);
			resultArrays[i] = result;
		}
		ct.endConsumeTime();
		this.print("random int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	protected int[] getSameIntArrays(int cycleNum, int value) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			this.print(value, true);
			resultArrays[i] = value;
		}
		ct.endConsumeTime();
		this.print("same int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	protected int[] getTwoIncreamIntArrays(int cycleNum, int start, int step) {
		int[] results = new int[cycleNum*2];
		int[] rs1 = this.getIncreamIntArrays(cycleNum, start, step);
		for(int i=0;i<cycleNum;i++) {
			results[i] = rs1[i];
		}
		int[] rs2 = this.getIncreamIntArrays(cycleNum, start, step);
		for(int i=0;i<cycleNum;i++) {
			results[cycleNum+i] = rs2[i];
		}
		
		this.print("two incream int size:" + results.length, true);
		return results;
	}
	
	protected int[] getIncreamIntArrays(int cycleNum, int start, int step) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			int result = start;
			start = start + step;
			this.print(result, false);
			resultArrays[i] = result;
		}
		ct.endConsumeTime();
		this.print("incream int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	protected int[] getDecreamIntArrays(int cycleNum, int start, int step) {
		ConsumerTime ct = new ConsumerTime();
		int resultArrays[] = new int[cycleNum];
		int count =0;
		for(int i=cycleNum-1;i>=0;i--) {
			int result = i * step +start;
			this.print(result, true);
			resultArrays[count] = result;
			count++;
		}
		ct.endConsumeTime();
		this.print("incream int size:" + resultArrays.length, true);
		return resultArrays;
	}
	
	
	public void print(Object obj, boolean isPrint) {
		try {
			if(!isPrint) {
				return;
			}
			if (obj == null) {
				return;
			}

			if (obj.getClass().isArray()) {
				this.printArrayValue(obj);
				return;
			}

			if (obj instanceof String) {
				System.out.println(obj.toString());
				return;
			}
			
			System.out.println(obj.toString());

//			String json = JacksonJsonUtil.convertObject2Json(obj);
//			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(obj.toString());
		}
	}

	protected void printArrayValue(Object obj) {
		if (obj instanceof String[]) {
			String values[] = (String[]) obj;
			for (String value : values) {
				this.print(value);
			}
		}
	}

	protected void fail(Exception e) {
		e.printStackTrace();
		Assert.fail(e.getMessage());
	}
	
	protected String Int2Binary(int value) {
		return Integer.toBinaryString(value);
	}

	public AbstractTest setMeasurements(int measurements) {
		this.measurements = measurements;
		return this;
	}

	public AbstractTest setThreads(int threads) {
		this.threads = threads;
		return this;
	}

	public AbstractTest setSerialTimes(int serialTimes) {
		this.SerialTimes = serialTimes;
		return this;
	}

	public AbstractTest setTitle(String title) {
		this.title = title;
		return this;
	}
}
