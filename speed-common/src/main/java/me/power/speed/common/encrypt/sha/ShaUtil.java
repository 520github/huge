package me.power.speed.common.encrypt.sha;

import org.apache.commons.codec.digest.DigestUtils;

public class ShaUtil {
	
	public static String shaHex(String data) {
		return DigestUtils.shaHex(data);
	}
}
