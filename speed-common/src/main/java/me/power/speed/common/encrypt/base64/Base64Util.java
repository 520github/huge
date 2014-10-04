/**
 * 
 */
package me.power.speed.common.encrypt.base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author xuehui.miao
 *
 */
public class Base64Util {
	
	/**
	 * 加密
	 * @param key
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptBase64(String key) throws UnsupportedEncodingException {
		return encryptBase64(key, "utf-8");
	}
	
	/**
	 * 加密
	 * @param key
	 * @param encode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptBase64(String key, String encode) throws UnsupportedEncodingException {
		return encryptBase64(key.getBytes(encode));
	}
	
	/**
	 * 加密
	 * @param key
	 * @return
	 */
	public static String encryptBase64(byte[] key) {
		return new BASE64Encoder().encodeBuffer(key);
	}
	
	
	/**
	 * 解密
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static byte[] decryptBase64(String key) throws IOException {
		return new BASE64Decoder().decodeBuffer(key);
	}
	
	/**
	 * 解密
	 * @param key
	 * @param encode
	 * @return
	 * @throws IOException
	 */
	public static String decryptBase64(String key, String encode) throws IOException {
		return new String(decryptBase64(key), encode);
	}
	
	/**
	 * 解密
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String decryptBase64ToUtf8(String key) throws IOException {
		return decryptBase64(key, "utf-8");
	}
}
