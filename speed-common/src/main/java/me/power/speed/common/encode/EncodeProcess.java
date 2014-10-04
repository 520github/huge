/**
 * 
 */
package me.power.speed.common.encode;

import java.io.UnsupportedEncodingException;

/**
 * @author xuehui.miao
 *
 */
//http://blog.csdn.net/leidengyan/article/details/4231460
//重新编译
//http://dingchaoqun12.blog.163.com/blog/static/11606250420111411557926/
//http://download.java.net/jdk6/binaries/
//http://lovespss.blog.51cto.com/1907593/535219

//各种进制转换
//http://blog.csdn.net/eddle/article/details/6892271
//http://franksinger.iteye.com/blog/614540
//http://bbs.csdn.net/topics/350023839

public class EncodeProcess {
	
	public static void getByte(String str) throws UnsupportedEncodingException {
		byte result[] = str.getBytes();
		for(byte b : result) {
			System.out.println(b);
			System.out.println((int)b);
			int ivalue = b & 0xFF;
			System.out.println(ivalue);
			System.out.println(Integer.toBinaryString(ivalue));
			System.out.println("---------------");
		}
	}
	
	public static void main(String[] args) {
		try {
			getByte("牛");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//1+2+4+32+64+128
}
