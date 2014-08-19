package me.power.speed.common.encode;

import java.io.UnsupportedEncodingException;

public class EncodeUtil {
	public static byte[] getBytes(String str) {
		if(str == null) {
			return null;
		}
		return str.getBytes();
	}
	
	public static byte[] getBytes(String str, String encode) throws UnsupportedEncodingException {
		if(str == null) {
			return null;
		}
		return str.getBytes(encode);
	}
}
