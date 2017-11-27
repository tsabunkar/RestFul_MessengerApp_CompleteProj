package org.MessengerApp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class CommentResource {

	@GET
	public String m1() {
		return "new sub resource";
	}
	
	
	@GET
	@Path("/{commentId}")
	public String m2(@PathParam("messageId") int messId, @PathParam("commentId") int commId) {
		return "commentId is : "+ commId + " for messageId : "+ messId;
	}
	
}
