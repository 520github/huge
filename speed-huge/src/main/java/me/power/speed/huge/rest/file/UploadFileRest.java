/**
 * 
 */
package me.power.speed.huge.rest.file;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.springframework.stereotype.Controller;

import me.power.speed.common.stream.file.FileUtil;
import me.power.speed.huge.rest.AbstractRest;

/**
 * @author xuehui.miao
 *
 */
@Controller
@Path("/huge/upload/file")
public class UploadFileRest extends AbstractRest {
	
	@POST
	@Path("/formdata")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFileByFormData(MultipartFormDataInput input) {
		try {
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("uploadedFile");//name
			
			InputPart inputPart = inputParts.get(0);
			InputStream inputStream = inputPart.getBody(InputStream.class, null);
			
			//this.log(inputStream.available() + "");
			
			FileUtil.write2FileFromInputStream("f:\\data\\df\\event\\test_" + UUID.randomUUID().toString() + ".jpg", inputStream);
			this.log("uploadFileByFormData ok");
			return Response.ok().build();
		} catch (Exception e) {
			return this.getExceptionResponse(e);
		}
	}
	
	@POST
	@Path("/formdata/request")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFileByFormData(@Context HttpServletRequest request) {
		try {
			InputStream inputStream = request.getInputStream();
			FileUtil.write2FileFromInputStream("f:\\data\\df\\event\\test_" + UUID.randomUUID().toString() + ".jpg", inputStream);
			return Response.ok().build();
		} catch (Exception e) {
			return this.getExceptionResponse(e);
		}
	}
}
