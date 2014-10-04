/**
 * 
 */
package me.power.speed.common.rpc.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

/**
 * @author xuehui.miao
 *
 */
public class HttpClientUtil {
	
	public static String postBytesData(String url, String contentType, String encode, byte[] content) {
		String result = null;
		try {
			HttpParams params = new BasicHttpParams();
			HttpClient hc = new DefaultHttpClient(params);
			HttpPost post = new HttpPost(url);
			post.addHeader("Accept-Encoding","gzip, deflate");
			
			ByteArrayEntity be = new ByteArrayEntity(content);
			be.setContentType(contentType);
			be.setContentEncoding(encode);
			
			post.setEntity(be);
			HttpResponse rp = hc.execute(post);
			
			HttpEntity entity = rp.getEntity();
			int code = rp.getStatusLine().getStatusCode();
			
			if (code >= 200 && code < 300) { 
				String line = null;
				if (entity != null) {
					BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), encode));
					while ((line = br.readLine()) != null) {
						result += line + " ";
					}
				}
			}
			else {
				result = "fail[http状态码：" + code + "]";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
