package me.power.speed.test.thirdparty.chronicle.map;

import java.net.InetSocketAddress;

import net.openhft.chronicle.hash.replication.TcpTransportAndNetworkConfig;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

import org.junit.Test;

import com.gameanalytics.bitmap.Bitmap;

public class TestChronicleMapStatelessClient extends AbstractCacheTest {
	
	@Test
	public void startChronicleMapStatelessServer() {
		try {
			ChronicleMap<String,Object> map = ChronicleMapBuilder.of(String.class, Object.class)
			.replication((byte)2, TcpTransportAndNetworkConfig.of(8076))
			.create();
			
			map.put("server", this.getRandomBitmap());
			while(true) {
				this.print("server map size:" + map.size());
				this.sleep(2000);
				this.printMap(map);
			}
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void startChronileMapStatelessClient() {
		try {
			ChronicleMap<String,Object> map = ChronicleMapBuilder.of(String.class, Object.class)
			.statelessClient(new InetSocketAddress("localhost", 8076))
			.create();
			//Bitmap bitmap = (Bitmap)map.get("server");
			//this.print(bitmap.cardinary());
			map.put("client", this.getRandomBitmap());
			while(true) {
				this.print("client map size:" + map.size());
				this.sleep(2000);
				this.printMap(map);
			}
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	private void printMap(ChronicleMap<String,Object> map) {
		for(String key: map.keySet()) {
			Object value = map.get(key);
			if(value instanceof Bitmap) {
				Bitmap bitmap = (Bitmap)value;
				this.print(key + "-->" + bitmap.cardinary());
			}
			else {
				this.print(key + "-->" + value);
			}
		}
	}
}
