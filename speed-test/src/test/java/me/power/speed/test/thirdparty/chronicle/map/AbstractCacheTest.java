package me.power.speed.test.thirdparty.chronicle.map;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gameanalytics.bitmap.Bitmap;

import me.power.speed.test.AbstractTest;
import me.power.speed.test.ConsumerTime;
import net.openhft.chronicle.map.ChronicleMapBuilder;

public class AbstractCacheTest extends AbstractTest {
	protected static AbstractCacheTest cacheTest = new AbstractCacheTest();
	protected static Map<String,Map<String,Object>> cacheMap = new HashMap<String, Map<String,Object>>();
	
	protected Map<String,Object> getHashMap() {
		return new HashMap<String, Object>();
	}
	
	protected Map<String,Object> getKeyChronicleMap(String key, String filePath) throws IOException {
		if(cacheMap.containsKey(key)) {
			return cacheMap.get(key);
		}
		Map<String,Object> data = //this.getHashMap();  //
		this.getChronicleMapByFilePath(filePath);
		cacheMap.put(key, data);
		return data;
	}
	
	protected Map<String,Object> getChronicleMapByFilePath(String filePath) throws IOException {
		File file = new File(filePath);
		return ChronicleMapBuilder.of(String.class, Object.class)
				.entries(1900009)
				//.constantKeySizeBySample("1000000")
				//.constantValueSizeBySample(this.getRandomBitmap())//"1000000"
				.createPersistedTo(file);
	}
	
	protected Map<String,Bitmap> getChronicleMapByFilePathWithBitmap(String filePath) throws IOException {
		File file = new File(filePath);
		return ChronicleMapBuilder.of(String.class, Bitmap.class)
				//.entries(1009)
				//.constantKeySizeBySample("1000000")
				//.constantValueSizeBySample(this.getRandomBitmap())//"1000000"
				.createPersistedTo(file);
	}
	
	protected void setBitmapValueToMap(Map<String,Object> map, int cycleNum) {
		ConsumerTime ct = new ConsumerTime();
		//String key = "intKey";
		long sumBytes = 0;
		try{
			for(int i=0; i<cycleNum;i++) {
				//Bitmap bitmap = this.getRandomBitmap();
				map.put(String.valueOf(i), this.getRandomBitmap());
				//sumBytes = sumBytes +BitmapHandler.bitmapToByteArray(bitmap).length;
				this.print(String.valueOf(i));
				this.sleep(5);
			}
		}catch(Exception e){
			this.print("sumBytes:" + sumBytes);
			this.fail(e);
		}
		
		ct.endConsumeTime();
		this.print(map.size());
	}
	
}
