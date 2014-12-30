/**
 * 
 */
package me.power.speed.test.thirdparty.chronicle.map;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.openhft.chronicle.hash.replication.TcpTransportAndNetworkConfig;
import net.openhft.chronicle.map.ChronicleMapBuilder;

/**
 * @author xuehui.miao
 *
 */
public class ChronicleMapShareWithMulitServer {
	private static Map<Integer, CharSequence> map1;
	private static Map<Integer, CharSequence> map2;
	
	static {
		try {
			startServer1();
			startServer2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void startServer1() {
		TcpTransportAndNetworkConfig tcpConfig = TcpTransportAndNetworkConfig
				.of(8076, new InetSocketAddress("localhost", 8077))
				.heartBeatInterval(1L, TimeUnit.SECONDS);
		ChronicleMapBuilder<Integer, CharSequence> map1Builder = ChronicleMapBuilder
				.of(Integer.class, CharSequence.class)
				.entries(20000L).replication((byte)1, tcpConfig);
		map1 = map1Builder.create();
	}
	
	public static void startServer2() {
		TcpTransportAndNetworkConfig tcpConfig =
                TcpTransportAndNetworkConfig.of(8077)
                .heartBeatInterval(1L, TimeUnit.SECONDS);
		ChronicleMapBuilder<Integer, CharSequence> map2Builder = ChronicleMapBuilder
				.of(Integer.class, CharSequence.class)
				.entries(20000L)
				.replication((byte)2, tcpConfig);
		map2 = map2Builder.create();
	}

	public static Map<Integer, CharSequence> getMap1() {
		return map1;
	}

	public static void setMap1(Map<Integer, CharSequence> map1) {
		ChronicleMapShareWithMulitServer.map1 = map1;
	}

	public static Map<Integer, CharSequence> getMap2() {
		return map2;
	}

	public static void setMap2(Map<Integer, CharSequence> map2) {
		ChronicleMapShareWithMulitServer.map2 = map2;
	}
	
}
