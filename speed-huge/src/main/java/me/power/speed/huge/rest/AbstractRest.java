/**
 * 
 */
package me.power.speed.huge.rest;

import java.net.URLDecoder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author xuehui.miao
 *
 */
public abstract class AbstractRest {
	protected static String mediaTypeJson = MediaType.APPLICATION_JSON;
	
	/**
	 * 获取异常的Response信息
	 * @param e
	 * @return
	 */
	protected Response getExceptionResponse(Exception e) {
		//LogPortal.getRestAccessLog().logError(e.getMessage(), e);
		return this.getErrorResponse(this.handleException(e));
	}
	
	/**
	 * 获取异常的Response信息
	 * @param errorMsg
	 * @return
	 */
	protected Response getErrorResponse(String errorMsg) {
		return Response.status(209).entity(this.getErrorEntity(errorMsg)).build();
	}
	
	/**
	 * 处理异常
	 * @param e
	 * @return
	 */
	protected String handleException(Exception e) {
		if(e == null) {
			return null;
		}
		String errorMsg = null;
		if(e instanceof IllegalArgumentException) {
			if(e.getMessage().indexOf("Source") > -1) {
				errorMsg = "source is illegal.";
			}
			else if(e.getMessage().indexOf("Channel") > -1) {
				errorMsg = "Channel is illegal.";
			}
		}
		else if(e instanceof NullPointerException) {
			errorMsg = "NullPointerException";
		}
		else {
			errorMsg = e.getMessage();
		}
		return errorMsg;
	}
	
	protected String getErrorEntity(String errorMsg) {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("result", false);
			jsonObj.put("cause", errorMsg);
		} catch (Exception e) {
			//LogPortal.getRestAccessLog().logError("get error entity", e);
		}	
		//LogPortal.getRestAccessLog().logDebug("get error entity result {} ", jsonObj);
		return jsonObj.toString();
	}
	
	protected String getBooleanResponseResult(boolean result) {
		return this.getStringResponseResult(result);
	}
	
	protected String getStringResponseResult(Object obj) {
		return "{\"result\":" + String.valueOf(obj) + "}";
	}
	
	protected String getDecodeValue(String value) {
		try {
			value = URLDecoder.decode(value,"utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
