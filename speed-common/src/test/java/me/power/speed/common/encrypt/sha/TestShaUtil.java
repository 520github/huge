package me.power.speed.common.encrypt.sha;

import org.junit.Test;

import me.power.speed.AbstractBaseTest;
import me.power.speed.common.encrypt.base64.Base64Util;

public class TestShaUtil extends AbstractBaseTest {
	private String content = "{\"user_id\":\"eu545dfhd\",\"user_name\":\"Jingxi Zhang\",\"user_name\":\"111@126.com\"}";
	private String clientKey = "1234";
	
	@Test
	public void testSha() {
		try {
			String sign = ShaUtil.shaHex(content + clientKey);
			this.print("sign:" + sign);
			
			String base64Sign = Base64Util.encryptBase64(sign);
			this.print("base64Sign:" + base64Sign);
			
			String contentBase64 = Base64Util.encryptBase64(content);
			
			String request = base64Sign + "." + contentBase64;
			this.print("request:" + request);
			
			String requests[] = request.split("\\.");
			//签名
			String encodeSig = requests[0];
			//内容
			String payload = requests[1];
			
			String oSign = Base64Util.decryptBase64ToUtf8(encodeSig);
			this.print("oSign:" + oSign);
			
			String oPayload = Base64Util.decryptBase64ToUtf8(payload);
			this.print("oPayload:" + oPayload);
			
			String resultSign = ShaUtil.shaHex(oPayload + clientKey);
			this.print("resultSign:" + resultSign);
			
			if(oSign.equals(resultSign)) {
				this.print("yes....");
			}
			
		} catch (Exception e) {
			this.fail(e);
		}
		
	}
}
