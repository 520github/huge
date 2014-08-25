/**
 * 
 */
package me.power.speed.common.msqueue;

import java.io.IOException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.KestrelCommandFactory;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.utils.AddrUtil;

/**
 * @author xuehui.miao
 *
 */
public class MemcacheClientBuilder {
	
	public static MemcachedClient getCacheTemplate(String ip) throws IOException{
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(ip));
		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
		builder.setCommandFactory(new KestrelCommandFactory());
		builder.setConnectionPoolSize(1);
		MemcachedClient client = builder.build();
		return client;
	}
}
