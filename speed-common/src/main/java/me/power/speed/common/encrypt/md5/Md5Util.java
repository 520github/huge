package me.power.speed.common.encrypt.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
	
	public static String md5ByApache(String s) {
		return DigestUtils.md5Hex(s);
	}
	
	public static String md5ByJdk(String str) {
		String md5Str = null;  
        if (str != null && str.length() != 0) {  
            try {  
                MessageDigest md = MessageDigest.getInstance("MD5");  
                md.update(str.getBytes());  
                byte b[] = md.digest();  
                  
                int i;  
                StringBuffer buf = new StringBuffer("");  
                for (int offset = 0; offset < b.length; offset++) {  
                    i = b[offset];  
                    if (i < 0)  
                        i += 256;  
                    if (i < 16)  
                        buf.append("0");  
                    buf.append(Integer.toHexString(i));  
                }  
                //32λ  
                //md5Str = buf.toString();  
                //16λ  
                //md5Str = buf.toString().substring(8, 24);  
                md5Str = buf.toString();
            } catch (NoSuchAlgorithmException e) {  
                e.printStackTrace();  
            }  
        }  
        return md5Str; 
	}
}
