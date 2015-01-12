package me.power.speed.thirdparty.chronicle.map;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import net.openhft.chronicle.hash.replication.ReplicationChannel;
import net.openhft.chronicle.hash.replication.ReplicationHub;
import net.openhft.chronicle.hash.replication.TcpTransportAndNetworkConfig;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;

public class ChronicleMapUtil {
	public static int entriesSize = 1900009;
	
	private static ChronicleMapBuilder<String, Object> builder =
            ChronicleMapBuilder.of(String.class, Object.class).entries(entriesSize);
	
	public static InetSocketAddress getInetSocketAddress(int port, String host) {
		return new InetSocketAddress(host, port);
	}
	
	public static TcpTransportAndNetworkConfig getTcpConfig(int port, InetSocketAddress... ineta) {
		if(ineta != null && ineta.length>0) {
			return TcpTransportAndNetworkConfig.of(port, ineta).heartBeatInterval(1, TimeUnit.SECONDS);
		}
		return TcpTransportAndNetworkConfig.of(port).heartBeatInterval(1, TimeUnit.SECONDS);
	}
	
	public static ReplicationHub getReplicationHub(TcpTransportAndNetworkConfig tcpConfig, byte identifier) {
		return ReplicationHub.builder().tcpTransportAndNetwork(tcpConfig).createWithId(identifier);
	}
	
	public static ReplicationChannel getReplicationChannel(ReplicationHub hub, short channelId) {
		return hub.createChannel(channelId);
	}
	
	public static ChronicleMap<String, Object> getChannelChronicleMap(ReplicationChannel channel) throws IOException {
		return builder.instance().replicatedViaChannel(channel).create();
	}
	
	public static ChronicleMap<String, Object> getChannelChronicleMapPersistedFile(ReplicationChannel channel, String fileName) throws IOException {
		return builder.instance().replicatedViaChannel(channel).persistedTo(new File(fileName)).create();
	}
}
