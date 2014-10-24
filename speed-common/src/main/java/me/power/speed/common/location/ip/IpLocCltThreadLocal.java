/**
 * 
 */
package me.power.speed.common.location.ip;

/**
 * @author xuehui.miao
 *
 */
public class IpLocCltThreadLocal {
	private static final ThreadLocal<ClientWrapper> threadLocal = new ThreadLocal<ClientWrapper>();
	
	private IpLocCltThreadLocal() throws IllegalAccessException {
		throw new IllegalAccessException();
	}
	
	public static final ClientWrapper getClient() throws Exception {
		ClientWrapper c = threadLocal.get();
		if (c == null || !c.isAvailable()) {
			//c = new ClientWrapper("10.10.33.41", 9099);
			c = new ClientWrapper("10.10.32.109", 9099);
			threadLocal.set(c);
		}
		return c;
	}
}
