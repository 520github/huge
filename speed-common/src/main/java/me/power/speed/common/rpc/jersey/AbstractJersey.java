/**
 * 
 */
package me.power.speed.common.rpc.jersey;

/**
 * @author xuehui.miao
 *
 */
import java.io.InputStream;

import javax.ws.rs.core.MediaType;

import me.power.speed.common.json.JsonUtil;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class AbstractJersey {
	// 是否输出一些参数日志
	private static boolean isPrint = false;

	protected static Client getDefaultClient() {
		return Client.create(getDefaultClientConfig());
	}

	protected static ClientConfig getDefaultClientConfig() {
		ClientConfig config = new DefaultClientConfig();
		config.getClasses().add(JacksonJaxbJsonProvider.class);
		// config.getClasses().add(SerializationConfig.Feature.);
		// config.getClasses().add(JacksonContextResolver.class);
		return config;
	}

	protected static WebResource getWebResource(String url) {
		return getDefaultClient().resource(url);
	}
	
	protected String postInputStream(String url, InputStream inputStream) {
		return getWebResource(url).type(MediaType.MULTIPART_FORM_DATA).post(String.class, inputStream);
	}

	/**
	 * 获取请求url信息
	 * 
	 * @param preUrl
	 * @param paras
	 * @return
	 */
	protected String getUrl(String hostAndPreUrl, String... paras) {
		StringBuffer url = new StringBuffer(hostAndPreUrl);
		for (String para : paras) {
			url.append("/" + para);
		}
		return url.toString();
	}

	/**
	 * 判断是否返回错误结果
	 * 
	 * @param errorKey
	 *            错误key
	 * @param errorValue
	 *            错误key的对应值
	 * @param errorMesKey
	 *            错误内容的key
	 * @param jsonStr
	 *            待处理json字符串
	 * @return
	 */
	protected boolean isError(String errorKey, Object errorValue,
			String errorMesKey, String jsonStr) {
		boolean isError = false;
		try {
			JSONObject jsonObj = JsonUtil.getJsonObjectByBean(jsonStr);
			return this.isError(errorKey, errorValue, errorMesKey, jsonObj);
		} catch (Exception e) {
			isError = true;
			e.printStackTrace();
			System.out.println("format [" + jsonStr
					+ "] to json is exception: " + e.getMessage());
		}
		return isError;
	}

	/**
	 * 判断是否返回错误结果
	 * 
	 * @param errorKey
	 *            错误key
	 * @param errorValue
	 *            错误key的对应值
	 * @param errorMesKey
	 *            错误内容的key
	 * @param jsonStr
	 *            待处理json字符串
	 * @return
	 */
	protected boolean isError(String errorKey, Object errorValue,
			String errorMesKey, JSONObject jsonObj) {
		boolean isError = false;
		try {
			if (jsonObj == null) {
				return true;
			}
			if (jsonObj.has(errorKey)) {
				Object value = jsonObj.get(errorKey);
				if (errorValue == value) {
					if (jsonObj.has(errorMesKey)) {
						System.out.println(jsonObj.getString(errorMesKey));
					}
					return true;
				}
			}
		} catch (Exception e) {
			isError = true;
			e.printStackTrace();
		}
		return isError;
	}

	/**
	 * 获取错误信息
	 * 
	 * @param errorKey
	 * @param errorValue
	 * @param errorMesKey
	 * @param jsonObj
	 * @return
	 */
	protected String getErrorMsg(String errorKey, Object errorValue,
			String errorMesKey, JSONObject jsonObj) {
		String errorMsg = null;

		try {
			if (jsonObj == null) {
				return "return json object is empty.";
			}
			if (jsonObj.has(errorKey)) {
				Object value = jsonObj.get(errorKey);
				if (errorValue == value) {
					if (jsonObj.has(errorMesKey)) {
						errorMsg = jsonObj.getString(errorMesKey);
					} else {
						errorMsg = "error message is empty.";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
		}

		return errorMsg;
	}

	protected void print(Object obj) {
		try {
			if (!isPrint) {
				return;
			}
			if (obj == null) {
				return;
			}
			String para = JsonUtil.writeBeanAsJsonString(obj);
			System.out.println(para);
		} catch (Exception e) {
			System.out.println(obj.toString());
		}
	}
}
