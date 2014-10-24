/**
 * 
 */
package me.power.speed.common.cert;

/**
 * @author xuehui.miao
 *
 */

import java.io.FileInputStream;  
import java.security.KeyStore;  
import java.security.PrivateKey;  
import java.security.PublicKey;  
import java.security.cert.Certificate;  
import java.util.Enumeration; 

//http://blog.csdn.net/byg760/article/details/10228433
//http://developer.51cto.com/art/200909/150930.htm
//http://blog.csdn.net/jinzhu117/article/details/7958181
//http://blog.csdn.net/x1135768777/article/details/17112485
//http://blog.csdn.net/nogodoss/article/details/8209151
//http://blog.csdn.net/linfei2707/article/details/22617973
//http://www.360doc.com/content/14/0208/12/9200790_350679134.shtml

public class ReadP12Cert {
	public static void readP12Cert() {
		final String KEYSTORE_FILE     = "C:\\xuehui\\40-information\\ios推送证书\\talkingdata.p12";  
        final String KEYSTORE_PASSWORD = "wangying";  
        final String KEYSTORE_ALIAS    = "alias";
        
        try {
        	KeyStore ks = KeyStore.getInstance("PKCS12");
        	FileInputStream fis = new FileInputStream(KEYSTORE_FILE);
        	
        	char[] nPassword = null;
        	if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("")) {
        		nPassword = null; 
        	}
        	else {
        		nPassword = KEYSTORE_PASSWORD.toCharArray();
        	}
        	
        	ks.load(fis, nPassword); 
        	fis.close();
        	
        	System.out.println("keystore type=" + ks.getType()); 
        	Enumeration enum1 = ks.aliases();
        	String keyAlias = null;  
        	if (enum1.hasMoreElements()) {
        		keyAlias = (String)enum1.nextElement(); 
        		System.out.println("alias=[" + keyAlias + "]");
        	}
        	
        	System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));     
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);    
            Certificate cert = ks.getCertificate(keyAlias);    
            PublicKey pubkey = cert.getPublicKey();
                        
            System.out.println("cert class = " + cert.getClass().getName());              
            System.out.println("cert = " + cert);    
            System.out.println("public key = " + pubkey);    
            System.out.println("private key = " + prikey);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		readP12Cert();
	}
}
