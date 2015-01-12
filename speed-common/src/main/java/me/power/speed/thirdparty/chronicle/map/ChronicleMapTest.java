package me.power.speed.thirdparty.chronicle.map;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.openhft.chronicle.hash.replication.ReplicationChannel;
import net.openhft.chronicle.hash.replication.ReplicationHub;
import net.openhft.chronicle.map.ChronicleMap;

import org.apache.commons.lang.StringUtils;

import com.gameanalytics.bitmap.Bitmap;
import com.gameanalytics.bitmap.impl.ConciseBitmapImpl;

public class ChronicleMapTest {
	private static String remoteAddr = "";
	private static int port = 1024;
	private static byte identifier = 1;
	private static String channelIds = "";
	private static String fileNames = "";
	private static String channelIdsArrays[];
	private static String valueType ;
	private static int cacheSize = 0;
	private static int overIncreamentCount = 0;
	private static Map<String,ChronicleMap<String, Object>> cacheMap = new HashMap<String, ChronicleMap<String,Object>>();
	
	public static void main(String[] args) {
		try {
			print("handle start...");
			//System.setProperty("remoteAddr", "10.10.32.104:1024");
			
			initProperty();
			initChronicleMap();
			operateAndSyncCache();
			
			while(true) {
				printCacheSize();
				Thread.sleep(1000);
				if(overIncreamentCount >= cacheSize) {
					printCacheData();
					printCacheSize();
					break;
				}
			}
			print("handle finish...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initProperty() {
		remoteAddr = System.getProperty("remoteAddr", "");
		print("remoteAddr->" + remoteAddr);
		
		try {
			int entrySize = Integer.parseInt(System.getProperty("entrySize","1000000"));
			print("entrySize->" + entrySize);
			ChronicleMapUtil.entriesSize = entrySize;
		} catch (Exception e) {
			print("entrySize parameter invalid " + System.getProperty("entrySize"));
		}
		
		try {
			port = Integer.parseInt(System.getProperty("port","1024"));
			print("port->" + port);
		} catch (Exception e) {
			print("port parameter invalid " + System.getProperty("port"));
		}
		
		try {
			identifier = (byte)Integer.parseInt(System.getProperty("identifier","1"));
			print("identifier->" + identifier);
		} catch (Exception e) {
			print("identifier parameter invalid " + System.getProperty("identifier"));
		}
		
		valueType = System.getProperty("valueType", "bitmap");
		
		channelIds = System.getProperty("channelIds", "1,2,3");
		print("channelIds->" + channelIds);
		channelIdsArrays = channelIds.split(",");
		
		fileNames = System.getProperty("fileNames", "fn1,fn2,fn3");//fn1,fn2,fn3
		print("fileName->" + fileNames);
		
		try {
			cacheSize = Integer.parseInt(System.getProperty("cacheSize", "10"));
			print("cacheSize->" + cacheSize);
		} catch (Exception e) {
			print("cacheSize parameter invalid " + System.getProperty("cacheSize"));
		}
	}
	
	private static InetSocketAddress[] getInetSocketAddress() {
		if(StringUtils.isBlank(remoteAddr)) {
			return null;
		}
		String remoteAddrArrays[] = remoteAddr.split(",");
		InetSocketAddress[] addrs = new InetSocketAddress[remoteAddrArrays.length];
		for(int i=0;i< remoteAddrArrays.length;i++) {
			String array = remoteAddrArrays[i];
			String host = array.split(":")[0];
			int port = Integer.parseInt(array.split(":")[1]);
			addrs[i] = ChronicleMapUtil.getInetSocketAddress(port, host);
		}
		
		return addrs;
	}
	
	private static void initChronicleMap() throws IOException {
		ReplicationHub repHub = ChronicleMapUtil.getReplicationHub(ChronicleMapUtil.getTcpConfig(port, getInetSocketAddress()), identifier);
		for(int i=0;i<channelIdsArrays.length;i++) {
			String channelId = channelIdsArrays[i];
			ReplicationChannel repChannel = ChronicleMapUtil.getReplicationChannel(repHub, Short.parseShort(channelId));
			
			ChronicleMap<String, Object> map = null;
			if(StringUtils.isBlank(fileNames)) {
				map = ChronicleMapUtil.getChannelChronicleMap(repChannel);
			}
			else {
				String fileName = fileNames.split(",")[i];
				map = ChronicleMapUtil.getChannelChronicleMapPersistedFile(repChannel, getTemDir()+fileName);
			}
			
			cacheMap.put(channelId, map);
		}
	}
	
	private static String getTemDir() {
		String tmpdir =  System.getProperty("java.io.tmpdir") + "/";
		print(tmpdir);
		return tmpdir;
	}
	
//	private static File getTempFile(String fileName) {
//		return new File(getTemDir() + fileName);
//	}
	
	private static void operateAndSyncCache() {
		for(String channel: channelIdsArrays) {
			final String finChannel = channel;
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					ChronicleMap<String, Object> map = cacheMap.get(finChannel);
					for(int i=0; i< cacheSize; i++) {
						try {
							map.put(String.valueOf(identifier)+":"+finChannel+":"+String.valueOf(i), ChronicleMapTest.getValue(finChannel, i));
							Thread.sleep(50);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}
			});
			thread.start();
		}
	}
	
	private static Object getValue(String channel, int index) {
		if("bitmap".equalsIgnoreCase(valueType)) {
			return getBitmapValue(channel, index);
		}
		else if("int".equalsIgnoreCase(valueType)) {
			return getIntValue(channel, index);
		}
		else {
			return getStringValue(channel, index);
		}
	}
	
	private static Bitmap getBitmapValue(String channel, int index) {
		return getRandomBitmap();
	}
	
	protected static Bitmap getRandomBitmap() {
		Bitmap bitmap = new ConciseBitmapImpl();
		int randoms[] = getRandomIntArrays(100000, 1000000);
		List<Integer> offsets = getSortOffsetListByOffsetArray(randoms);
		for(int offset : offsets) {
			bitmap.set(offset);
		}
		return bitmap;
	}
	
	protected static int[] getRandomIntArrays(int cycleNum, int baseNum) {
		int resultArrays[] = new int[cycleNum];
		for(int i=0;i<cycleNum;i++) {
			double d = Math.random();
			int result = (int)(d*d*d*baseNum);
			resultArrays[i] = result;
		}
		print("random int size:" + resultArrays.length);
		return resultArrays;
	}
	
	protected static List<Integer> getSortOffsetListByOffsetArray(int offsets[]) {
		List<Integer> offsetLists = getOffsetsToList(offsets);
		Collections.sort(offsetLists);
		return offsetLists;
	}
	
	protected static List<Integer> getOffsetsToList(int offsets[]) {
		List<Integer> offsetLists = new ArrayList<Integer>();
		for(int offset: offsets) {
			offsetLists.add(offset);
		}
		return offsetLists;
	}
	
	private static int getIntValue(String channel, int index) {
		return port*identifier*Integer.parseInt(channel)*index;
	}
	
	private static String getStringValue(String channel, int index) {
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("->");
		sb.append(identifier).append("->");
		sb.append(channel).append("->");
		sb.append(index).append("->");
		return sb.toString();
	}
	
	private static void printCacheSize() {
		for(String key: cacheMap.keySet()) {
			ChronicleMap<String, Object> map = cacheMap.get(key);
			print("size:" + key + "-->" + map.size());
			if(map.size() >= cacheSize) {
				overIncreamentCount++;
			}
		}
	}
	
	private static void printCacheData() {
		for(String key: cacheMap.keySet()) {
			ChronicleMap<String, Object> map = cacheMap.get(key);
			print(key+" data list----->");
			for(String dataKey: map.keySet()) {
				Object value = map.get(dataKey);
				String printValue = "";
				if("string".equalsIgnoreCase(valueType)
				 ||"int".equalsIgnoreCase(valueType)) {
					printValue = dataKey + "->" + value;
				}
				else if("bitmap".equalsIgnoreCase(valueType)) {
					Bitmap bitmap = (Bitmap)value;
					printValue = dataKey + "->" + bitmap.cardinary();
				}
				else {
					printValue = dataKey + "->" + value;
				}
				print("keyValue->" + printValue);
			}
		}
	}
	
	private static void print(Object obj) {
		if(obj == null) {
			return;
		}
		System.out.println(obj.toString());
	}
}
