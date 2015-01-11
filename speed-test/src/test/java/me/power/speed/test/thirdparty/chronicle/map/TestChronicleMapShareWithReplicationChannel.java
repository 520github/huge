package me.power.speed.test.thirdparty.chronicle.map;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import net.openhft.chronicle.hash.replication.ReplicationChannel;
import net.openhft.chronicle.hash.replication.ReplicationHub;
import net.openhft.chronicle.hash.replication.TcpTransportAndNetworkConfig;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import me.power.speed.test.AbstractTest;

public class TestChronicleMapShareWithReplicationChannel extends AbstractTest {
	private ChronicleMap<CharSequence, CharSequence> favoriteColourServer1, favoriteColourServer2;
	private ChronicleMap<CharSequence, CharSequence> favoriteComputerServer1, favoriteComputerServer2;
	
	private ChronicleMapBuilder<CharSequence, CharSequence> builder =
            ChronicleMapBuilder.of(CharSequence.class, CharSequence.class).entries(1000);
	
	@Test
	public void testChronicleMapShareWithReplicationChannel() {
		try {			
			boolean isPersistedFile = true;
			ReplicationHub hubOnServer1 = this.getReplicationHub(this.getTcpConfig(8086,new InetSocketAddress("localhost", 8087)),(byte)1);
			if(isPersistedFile) {
				 favoriteColourServer1 = this.getChannelChronicleMapPersistedFile(this.getReplicationChannel (hubOnServer1,(short)1),"data1");
				 favoriteComputerServer1 = this.getChannelChronicleMapPersistedFile(this.getReplicationChannel (hubOnServer1,(short)2),"data2");
			}else {
				 favoriteColourServer1 = this.getChannelChronicleMap(this.getReplicationChannel (hubOnServer1,(short)1));
				 favoriteComputerServer1 = this.getChannelChronicleMap(this.getReplicationChannel (hubOnServer1,(short)2));
			}
		   
		    
		    favoriteColourServer1.put("mimi", "i am mimi");
		    favoriteComputerServer1.put("dede", "i am dede");
		    
		    ReplicationHub hubOnServer2 = this.getReplicationHub(this.getTcpConfig(8087,null),(byte)2);
		    if(isPersistedFile) {
		    	favoriteColourServer2 = this.getChannelChronicleMapPersistedFile(this.getReplicationChannel(hubOnServer2, (short)1),"data3");
			    favoriteComputerServer2 = this.getChannelChronicleMapPersistedFile(this.getReplicationChannel(hubOnServer2, (short)2),"data4");
		    }else {
		    	favoriteColourServer2 = this.getChannelChronicleMap(this.getReplicationChannel(hubOnServer2, (short)1));
			    favoriteComputerServer2 = this.getChannelChronicleMap(this.getReplicationChannel(hubOnServer2, (short)2));
		    }
		    
		    while(true) {
		    	this.sleep(1000);
		    }
		    
//		    favoriteColourServer2.put("rob", "blue");
//		    favoriteComputerServer2.put("rob", "mac");
//		    favoriteComputerServer2.put("daniel", "mac");
//		    
//		    for (int t = 0; t < 2500; t++) {
//		        if (favoriteComputerServer2.equals(favoriteComputerServer1) &&
//		                favoriteColourServer2.equals(favoriteColourServer1))
//		            break;
//		        Thread.sleep(1);
//		    }
//		   
//		    Assert.assertEquals(favoriteComputerServer1, favoriteComputerServer2);
//		    Assert.assertEquals(3, favoriteComputerServer2.size());
//
//		    Assert.assertEquals(favoriteColourServer1, favoriteColourServer2);
//		    Assert.assertEquals(2, favoriteColourServer1.size());

		   // favoriteColourServer1.close();
		    //favoriteComputerServer1.close();
		   // favoriteColourServer2.close();
		   // favoriteComputerServer2.close();
			
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	@Test
	public void testReadDataFromPersistedFile() {
		try {
			ReplicationHub hubOnServer1 = this.getReplicationHub(this.getTcpConfig(8086,new InetSocketAddress("localhost", 8087)),(byte)1);
			 favoriteColourServer1 = this.getChannelChronicleMapPersistedFile(this.getReplicationChannel (hubOnServer1,(short)1),"data1");
			 this.printChronicleMap(favoriteColourServer1);
			 
			 favoriteComputerServer1 = this.getChannelChronicleMapPersistedFile(this.getReplicationChannel (hubOnServer1,(short)2),"data2");
			 this.printChronicleMap(favoriteComputerServer1);
			 
			 ReplicationHub hubOnServer2 = this.getReplicationHub(this.getTcpConfig(8087,null),(byte)2);
			 favoriteColourServer2 = this.getChannelChronicleMapPersistedFile(this.getReplicationChannel(hubOnServer2, (short)1),"data3");
			 this.printChronicleMap(favoriteColourServer2);
			 
			 favoriteComputerServer2 = this.getChannelChronicleMapPersistedFile(this.getReplicationChannel(hubOnServer2, (short)2),"data4");
			 this.printChronicleMap(favoriteComputerServer2);
		} catch (Exception e) {
			this.fail(e);
		}
	}
	
	private void printChronicleMap(ChronicleMap<CharSequence, CharSequence> map) {
		for(CharSequence key: map.keySet()) {
			CharSequence value = map.get(key);
			this.print(key+ "-->" + value);
		}
 	}
	
	private TcpTransportAndNetworkConfig getTcpConfig(int port, InetSocketAddress ineta) {
		if(ineta != null) {
			return TcpTransportAndNetworkConfig.of(port, ineta).heartBeatInterval(1, TimeUnit.SECONDS);
		}
		return TcpTransportAndNetworkConfig.of(port).heartBeatInterval(1, TimeUnit.SECONDS);
	}
	
	private ReplicationHub getReplicationHub(TcpTransportAndNetworkConfig tcpConfig, byte identifier) {
		return ReplicationHub.builder().tcpTransportAndNetwork(tcpConfig).createWithId(identifier);
	}
	
	private ReplicationChannel getReplicationChannel(ReplicationHub hub, short channelId) {
		return hub.createChannel(channelId);
	}
	
	private ChronicleMap<CharSequence, CharSequence> getChannelChronicleMap(ReplicationChannel channel) throws IOException {
		return builder.instance().replicatedViaChannel(channel).create();
	}
	
	private ChronicleMap<CharSequence, CharSequence> getChannelChronicleMapPersistedFile(ReplicationChannel channel, String fileName) throws IOException {
		return builder.instance().replicatedViaChannel(channel).persistedTo(this.getTempFile(fileName)).create();
	}
}
