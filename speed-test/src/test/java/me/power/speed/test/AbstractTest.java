/**
 * 
 */
package me.power.speed.test;

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
	
	protected void print(Object obj) {
		this.print(obj, this.isPrint);
	}
	
	
	protected void print(Object obj, boolean isPrint) {
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
