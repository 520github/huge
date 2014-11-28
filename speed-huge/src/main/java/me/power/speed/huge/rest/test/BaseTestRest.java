/**
 * 
 */
package me.power.speed.huge.rest.test;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Controller;

import me.power.speed.entity.User;
import me.power.speed.huge.rest.AbstractRest;

/**
 * @author xuehui.miao
 *
 */
@Controller
@Path("/huge/test")
@Provider
public class BaseTestRest extends AbstractRest {
	
	@GET
	@Path("/return/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testReturnJson() {
		try {
			return Response.ok("{\"id\":1235664}").build();
		} catch (Exception e) {
			return this.getExceptionResponse(e);
		}
	}
	
	@POST
	@Path("/post/user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postOneUser(User user) {
		try {
			return Response.ok(user).build();
		} catch (Exception e) {
			return this.getExceptionResponse(e);
		}
	}
}
