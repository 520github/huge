package me.power.speed.test.thirdparty.chronicle.map;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.common.BitmapHandler;

import me.power.speed.test.ConsumerTime;
import me.power.speed.test.ConsumerTime.ConsumerTimeHandle;

public class TestCompareDiffCache extends AbstractCacheTest {
	protected static int cycleNum = 100;
	protected String path = "C:\\xuehui\\50-temp\\90-temp\\cache\\";
	protected String filePath = path+"bitmapCache"+cycleNum;
	
	@Test
	public void testSetToChronicleMapByFilePath() {
		try {
			Map<String,Set> map = this.getSetChronicleMapByFilePath(path+"set"+cycleNum);
			this.print("before map size:" + map.size());
			this.sleep(2000);
			this.setSetValueToMap(map);
			this.showMapSizeAndValue(map);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testShowMapSizeAndValue() {
		try {
			Map<String,Set> map = this.getSetChronicleMapByFilePath(path+"set"+cycleNum);
			this.showMapSizeAndValue(map);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	private void setSetValueToMap(Map<String,Set> map) {
		for(int i=0; i<cycleNum; i++) {
			String key = String.valueOf(i);
			Set<Integer> obj = map.get(key);
			if(obj == null) {
				obj = this.getNewSet();
				for(int j=0;j<cycleNum;j++) {
					int random = (int)(Math.random()*10000000);
					//this.print("random:" + random);
					obj.add(random);
				}
				map.put(key, obj);
			}
			else {
				this.print("is exit key: " + key);
				for(int j=0;j<10;j++) {
					int random = (int)(Math.random()*10000000);
					//this.print("random:" + random);
					obj.add(random);
				}
				map.put(key, obj);
			}
		}
	}
	
	private void showMapSizeAndValue(Map<String,Set> map) {
		for(String key: map.keySet()) {
			Set<Integer> set = map.get(key);
			if(set == null) {
				this.print(key + "---> the value is empty." );
				continue;
			}
			this.print(key + "--->" + set.size());
			StringBuffer values = new StringBuffer();
			for(Integer value: set) {
				values.append(value).append(",");
			}
			this.print(key + "-->" + values.toString());
		}
		this.print("map size:" + map.size());
	}
	
	@Test
	public void testSetHashMap() {
		Map<String,Object> map = this.getHashMap();
		//this.setIntValueToMap(map);
		this.setBitmapValueToMap(map);
	}
	
	@Test
	public void testSetBitmapBytesToChronicleMapByFilePath() {
		try {
			//this.sleep(3500);
			Map<String,Object> map = this.getChronicleMapByFilePath(path+"bitmapByteCache"+cycleNum);
			this.setBitmapBytesValueToMap(map);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testSetBitmapBytesToChronicleMapByFilePath2() {
		try {
			this.sleep(5000);
			Map<String,Object> map = this.getChronicleMapByFilePath(path+"bitmapByteCache"+cycleNum);
			this.setBitmapBytesValueToMap(map);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testSetChronicleMapByFilePath() {
		try {
			{
				//this.sleep(3500);
			Map<String,Object> map = this.getChronicleMapByFilePath(filePath);
				//this.setIntValueToMap(map);
			for(int i=0;i<2;i++) 
				this.setBitmapValueToMap(map);
			}
			this.getBitmapFromChronicleMap(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		this.handleWithConsumerTime(new ConsumerTimeHandle() {
//			public void handle() {
//				
//			}
//		});
	}
	
	@Test
	public void testStringChronicleMapByFilePath() {
		try {
			Map<String,Object> map = this.getChronicleMapByFilePath(filePath);
			for(int i=0;i<100;i++) {
				this.setStringValueToMap(map, cycleNum);
				this.getBitmapFromChronicleMap(filePath);
			}
			//this.getBitmapFromChronicleMap(filePath);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testSetChronicleMapByFilePath2() {
		try {
			this.sleep(5000);
			Map<String,Object> map = this.getChronicleMapByFilePath(filePath);
			//this.setIntValueToMap(map);
			this.setBitmapValueToMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetRandomBitmap() {
		this.getRandomBitmap();
	}
	
	@Test
	public void testSetBitmapToChronicleMap() {
		try {
			Map<String,Bitmap> map = this.getChronicleMapByFilePathWithBitmap(filePath);
			Map<String,Object> mapOther = this.getChronicleMapByFilePath(filePath+"bytes");
			this.setBitmapValueToMapBimtap(map, mapOther);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testGetBitmapFromChronicleMap() {
		try {
			filePath = path + "fn1";
			filePath = "C:\\xuehui\\50-temp\\10-datafilter-data\\20-thread\\fn999";
			this.getBitmapFromChronicleMap(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void getBitmapFromChronicleMap(String filePath) {
		try {
			Map<String,Object> map = this.getChronicleMapByFilePath(filePath);
			this.print("get map size:" + map.size());
			String lkey = "";
			try {
				for(String key : map.keySet()) {
					lkey =key;
					Object obj = map.get(key);
					if(obj == null) {
						this.print(key + "--> is empty." );
						continue;
					}
					if(obj instanceof String) {
						this.print(key + "-->" + obj.toString());
					}
					else {
						Bitmap bitmap = (Bitmap)map.get(key);
						this.print(key + "-->" + bitmap.cardinary());	
					}
				}
			} catch (Exception e) {
				this.print(lkey + "--> exception:" + e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testMulitDimeChronicleMap() {
		try {
			String[] keys = new String[]{"12347","12347-1","12347-1-87653"};//,"12347-1","12347-1-87653","12347-2-98654","12347-98643"
			for(String key: keys) {
				Map<String,Object> map = this.getKeyChronicleMap(key, filePath + key);
				this.setBitmapValueToMap(map);
//				try {
//					Thread thread = new Thread(new Runnable() {
//						public void run() {
//							System.out.println("ayou okd");
//							cacheTest.setBitmapValueToMap(map, cycleNum);
//						}
//					});
//					thread.start();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	protected void setIntValueToMap(Map<String,Object> map) {
		ConsumerTime ct = new ConsumerTime();
		//String key = "intKey";
		for(int i=0; i<cycleNum;i++) {
			map.put(String.valueOf(i), i);
		}
		ct.endConsumeTime();
		this.print(map.size());
	}
	
	protected void setBitmapValueToMap(Map<String,Object> map) {
		this.setBitmapValueToMap(map, cycleNum);
	}
	
	protected void setBitmapBytesValueToMap(Map<String,Object> map) {
		ConsumerTime ct = new ConsumerTime();
		//String key = "intKey";
		for(int i=0; i<cycleNum;i++) {
			map.put(String.valueOf(i), BitmapHandler.bitmapToByteArray(this.getRandomBitmap()));
			this.print(String.valueOf(i));
		}
		ct.endConsumeTime();
		this.print(map.size());
	}
	
	protected void setBitmapValueToMapBimtap(Map<String,Bitmap> map, Map<String, Object> mapOther) {
		ConsumerTime ct = new ConsumerTime();
		//String key = "intKey";
		for(int i=0; i<cycleNum;i++) {
			Bitmap bitmap = this.getRandomBitmap();
			map.put(String.valueOf(i), bitmap);
			//mapOther.put(String.valueOf(i), BitmapHandler.bitmapToByteArray(bitmap));
		}
		ct.endConsumeTime();
		this.print(map.size());
	}
	
}
