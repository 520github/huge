/**
 * 
 */
package me.power.speed.huge.rest.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}
