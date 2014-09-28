package me.power.speed.common.encode;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

public class EncodeUtil {
	private static String ENCODE_CHARSET = "utf-8";
	
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
	
	public static String filter4BitCharset(String str) {
		if(StringUtils.isBlank(str)) {
			return str;
		}
		try {
			byte[] bytes = str.getBytes(ENCODE_CHARSET);
			System.out.println(bytes.length);
			
			for(int i = 0; i < bytes.length ; i++) {
				byte currentByte = bytes[i];
				System.out.println(currentByte);
				if((currentByte & 0xF8) == 0xF0) {//4个字节的字符情况
					System.out.println("find 4 byte charset");
					for(int j = 0; j < 4; j++) {
						//转换成0
						bytes[i+j] = 0x30;
						//bytes[i+j] = ;
					}
					i+=3;
				}
			}
			return new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void checkCharsetBitLength() throws UnsupportedEncodingException {
		String nickName = "12葫";  
		  
        byte[] t = nickName.substring(0, 1).getBytes("UTF-8");  
        for (byte tt : t) {  
            System.out.println(tt);  
        }  
        System.out.println("====================");  
        byte[] t1 = nickName.getBytes("UTF-8");  
        for (int i = 0; i < t1.length;) {  
            byte tt = t1[i];  
//            if (CharUtils.isAscii((char) tt)) {  
//                byte[] ba = new byte[1];  
//                ba[0] = tt;  
//                i++;  
//                String result = new String(ba);  
//                System.out.println("1个字节的字符");  
//                System.out.println("字符为：" + result);  
//            }  
            if ((tt & 0xE0) == 0xC0) {  
                byte[] ba = new byte[2];  
                ba[0] = tt;  
                ba[1] = t1[i+1];  
                i++;  
                i++;  
                String result = new String(ba);  
                System.out.println("2个字节的字符");  
                System.out.println("字符为：" + result);  
            }  
            if ((tt & 0xF0) == 0xE0) {  
                byte[] ba = new byte[3];  
                ba[0] = tt;  
                ba[1] = t1[i+1];  
                ba[2] = t1[i+2];  
                i++;  
                i++;  
                i++;  
                String result = new String(ba);  
                System.out.println("3个字节的字符");  
                System.out.println("字符为：" + result);  
            }  
            if ((tt & 0xF8) == 0xF0) {  
                byte[] ba = new byte[4];  
                ba[0] = tt;  
                ba[1] = t1[i+1];  
                ba[2] = t1[i+2];  
                ba[3] = t1[i+3];  
                i++;  
                i++;  
                i++;  
                i++;  
                String result = new String(ba);  
                System.out.println("4个字节的字符");  
                System.out.println("字符为：" + result);  
            }  
        }  
	}
}
