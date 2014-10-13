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

public class ReadP12Cert {
	public static void readP12Cert() {
		final String KEYSTORE_FILE     = "证书路径";  
        final String KEYSTORE_PASSWORD = "证书密码";  
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
}
