/**
 * 
 */
package me.power.speed.huge.rest.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import me.power.speed.huge.rest.AbstractRest;

/**
 * @author xuehui.miao
 *
 */
@Controller
@Path("/huge/test/angular")
public class AngularRest extends AbstractRest {
	@GET
	@Path("get/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response testReturnJson(@PathParam("userId") int userId) {
		try {
			System.out.println("userId:" + userId);
			return Response.ok("{\"id\":1235664}").build();
		} catch (Exception e) {
			return this.getExceptionResponse(e);
		}
	}
}
