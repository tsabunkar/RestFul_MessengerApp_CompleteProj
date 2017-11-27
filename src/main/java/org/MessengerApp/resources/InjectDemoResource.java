package org.MessengerApp.resources;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotation")
	public String getParamUsingAnnotaions( @BeanParam MessageFilterBean filterBeanObj) {
		return "matrixParam Val is : "+ filterBeanObj.getMatrixParam() +" headerParam val is : "+filterBeanObj.getHeaderParam() + "cookieParam val is : "+filterBeanObj.getCookieParam();
	}
	
	@GET
	@Path("newannotation")
	public String m2(@Context UriInfo uri,@Context HttpHeaders headers) {
		String uripath = uri.getAbsolutePath().toString();
	
		String headerInfo = headers.getCookies().toString();
		return uripath +", "+headerInfo ;
	}
	
	
 
						
}
