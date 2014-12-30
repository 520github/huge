package me.power.speed.test.thirdparty.chronicle.map;

import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import net.openhft.chronicle.hash.replication.SingleChronicleHashReplication;
import net.openhft.chronicle.hash.replication.TcpTransportAndNetworkConfig;
import net.openhft.chronicle.hash.replication.ThrottlingConfig;
import net.openhft.chronicle.hash.replication.UdpTransportConfig;
import net.openhft.chronicle.map.ChronicleMapBuilder;

public class ChronicleMapShareWithMulitServerByUDP {
	private static Map<Integer, CharSequence> map1;
	private static Map<Integer, CharSequence> map2;
	private static int udpPort = 1234;
	private static String hostName = "192.168.48.89";
	
	static {
		try {
			startServer1();
			startServer2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void startServer1() throws UnknownHostException {
		TcpTransportAndNetworkConfig tcpConfig = TcpTransportAndNetworkConfig
				.of(8076, new InetSocketAddress("localhost", 8077))
				.heartBeatInterval(1L, TimeUnit.SECONDS)
				.throttlingConfig(ThrottlingConfig.throttle(1024, TimeUnit.MILLISECONDS));
		
		UdpTransportConfig udpConfig = UdpTransportConfig.of(Inet4Address.getByName(hostName), udpPort);
		
		ChronicleMapBuilder<Integer, CharSequence> map1Builder = ChronicleMapBuilder
				.of(Integer.class, CharSequence.class)
				.entries(20000L)
				.replication(SingleChronicleHashReplication
						.builder()
						.tcpTransportAndNetwork(tcpConfig)
						.udpTransport(udpConfig)
						.createWithId((byte)1));
		
		map1 = map1Builder.create();
	}
	
	public static void startServer2() throws UnknownHostException {
		TcpTransportAndNetworkConfig tcpConfig =
                TcpTransportAndNetworkConfig.of(8077)
                .heartBeatInterval(1L, TimeUnit.SECONDS)
                .throttlingConfig(ThrottlingConfig.throttle(1024, TimeUnit.MILLISECONDS));
		
		UdpTransportConfig udpConfig = UdpTransportConfig
                .of(Inet4Address.getByName(hostName), udpPort);
		
		
		ChronicleMapBuilder<Integer, CharSequence> map2Builder = ChronicleMapBuilder
				.of(Integer.class, CharSequence.class)
				.entries(20000L)
				.replication(SingleChronicleHashReplication
						.builder()
						.tcpTransportAndNetwork(tcpConfig)
						.udpTransport(udpConfig)
						.createWithId((byte)2));
		
		
		map2 = map2Builder.create();
	}

	public static Map<Integer, CharSequence> getMap1() {
		return map1;
	}

	public static Map<Integer, CharSequence> getMap2() {
		return map2;
	}

}
