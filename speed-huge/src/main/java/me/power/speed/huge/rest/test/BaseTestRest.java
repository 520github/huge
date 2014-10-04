/**
 * 
 */
package me.power.speed.huge.rest.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
@Path("/huge/test")
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
}
