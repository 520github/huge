/**
 * 
 */
package me.power.speed.huge.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author xuehui.miao
 *
 */
//http://www.ibm.com/developerworks/cn/web/wa-jaxrs/
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class MyJacksonJsonProvider extends JacksonJsonProvider {
	
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String,String> httpHeaders, InputStream entityStream) 
	        throws IOException
	{
		ObjectMapper mapper = locateMapper(type, mediaType);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		return super.readFrom(type, genericType, annotations, mediaType, httpHeaders, entityStream);
	}
	
	@Override
    public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations, 
            MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) 
            throws IOException 
    {
        ObjectMapper mapper = locateMapper(type, mediaType);

        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        super.writeTo(value, type, genericType, annotations, mediaType, httpHeaders, entityStream);
    }
}
