/**
 * 
 */
package me.power.speed.huge.rest.test;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import me.power.speed.huge.rest.AbstractRest;

/**
 * @author xuehui.miao
 *
 */
@Controller
@Path("/huge/dynamic")
public class DynamicRest extends AbstractRest {
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postDynamicData(JSONObject json) {
		try {
			System.out.println(json.toString());
			return Response.ok().build();
		} catch (Exception e) {
			return this.getExceptionResponse(e);
		}
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDynamicData(JSONObject json) {
		try {
			System.out.println(json.toString());
			return Response.ok().build();
		} catch (Exception e) {
			return this.getExceptionResponse(e);
		}
	}
	
	@GET
	@Path("/getUnConfigChannels")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUnConfigChannels() {
		try {
			JSONArray array = new JSONArray();
			array.add(this.getChannelData("mpush", "魔推", Arrays.asList("appKey","appId")));
			array.add(this.getChannelData("getui", "个推", Arrays.asList("appKey","appId","appScrect")));
			return Response.ok(array).build();
		} catch (Exception e) {
			return this.getExceptionResponse(e);
		}
	}
	
	private JSONObject getChannelData(String pushType, String pushName, List<String> keys) {//, List<String> keys
		JSONObject json = new JSONObject();
		json.put("pushType", pushType);
		json.put("pushName", pushName);
		json.put("keys", keys);
		
		return json;
	}
}
