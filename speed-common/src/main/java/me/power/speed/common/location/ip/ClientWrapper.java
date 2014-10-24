/**
 * 
 */
package me.power.speed.common.location.ip;

/**
 * @author xuehui.miao
 *
 */

import org.apache.commons.lang.StringUtils;
import com.tendcloud.iplocation.client.Client;
import com.tendcloud.iplocation.thrift.IpLocation;

public class ClientWrapper {
	
	private Client internalClient;
	private boolean isAvailable;

	public IpLocation getLocationFromIp(String ip) {
		if (internalClient != null && StringUtils.isNotBlank(ip) && isAvailable) {
			try {
				return internalClient.getLocationFromIp(ip);
			} catch (Exception e) {
				e.printStackTrace();
				isAvailable = Boolean.FALSE;
				internalClient.close();
				return new IpLocation("unknow", "unknow", "unknow");
			}
		} else {
			return new IpLocation("unknow", "unknow", "unknow");
		}
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void close() {
		if (internalClient != null) {
			internalClient.close();
		}
	}

	public ClientWrapper(String host, int port) {
		if (StringUtils.isBlank(host) || port <= 0) {
			throw new IllegalArgumentException();
		}

		try {
			internalClient = new Client(host, port);
			isAvailable = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
