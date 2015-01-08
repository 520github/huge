package me.power.speed.test.thirdparty.chronicle.map;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gameanalytics.bitmap.Bitmap;

import me.power.speed.test.AbstractTest;
import net.openhft.chronicle.map.ChronicleMapBuilder;

public abstract class AbstractCacheTest extends AbstractTest {
	protected Map<String,Object> getHashMap() {
		return new HashMap<String, Object>();
	}
	
	protected Map<String,Object> getChronicleMapByFilePath(String filePath) throws IOException {
		File file = new File(filePath);
		return ChronicleMapBuilder.of(String.class, Object.class)
				//.entries(8009)
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
	
}
