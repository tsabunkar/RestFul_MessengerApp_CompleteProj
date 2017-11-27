package org.MessengerApp.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.MessengerApp.exception.MyFileNotFoundException;
import org.MessengerApp.model.ErrorMessage;
import org.MessengerApp.model.Message;
import org.MessengerApp.service.MessageService;

@Path("/mymessages")
public class MyMessengerResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON) // response type is XML for the
											// request made -> "/mymessage"
	public List<Message> getAllMyMessages(@QueryParam("year") int year,
			@QueryParam("start") int start,
			@QueryParam("size") int size) throws ClassNotFoundException, SQLException {

		MessageService messService = new MessageService();
		if(year>0) //if QueryParam value is not zero 
			return messService.getAllMessageForYear(year);
		if(start>0 && size >0)
			return messService.getAllMessagesPaginated(start, size);
		
		return messService.getAllMessage();
	}

	// to Map subsequent path for -> /mymessage

	/*
	 * @GET
	 * 
	 * @Path("/{messageId}")
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String m1(@PathParam("messageId")
	 * String messageId){ return "GOT messageId praram : " + messageId;
	 * 
	 * }
	 */

	/*@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getParticularMessage(@PathParam("messageId") int id)
			throws NumberFormatException, ClassNotFoundException, SQLException {
		MessageService messService = new MessageService();
		Message message =  messService.getMessage(id);
		if(message == null)
			throw new MyFileNotFoundException("Message with id -"+ id + " not found");
		return message;

	}*/
	
	/*@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getParticularMessage(@PathParam("messageId") int id)
			throws NumberFormatException, ClassNotFoundException, SQLException {
		MessageService messService = new MessageService();
		Message message =  messService.getMessage(id);
		ErrorMessage errorMess = new ErrorMessage("NOT found", 404, "https://google.com");
		Response resp = Response.status(Status.NOT_FOUND)
								.entity(errorMess)
								.build();
		if(message == null)
			throw new WebApplicationException(resp);
		return message;

	}
*/
	
	/*@GET 
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getParticularMessage(@PathParam("messageId") int id)
			throws NumberFormatException, ClassNotFoundException, SQLException {
		MessageService messService = new MessageService();
		Message message =  messService.getMessage(id);
		ErrorMessage errorMess = new ErrorMessage("NOT found", 404, "https://google.com");
		Response resp = Response.status(Status.NOT_FOUND)
								.entity(errorMess)
								.build();
		
		if(message == null)
			throw new NotFoundException("Exception not found");
		return message;

	}*/
	
	@GET 
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getParticularMessage(@PathParam("messageId") int id,@Context UriInfo uri)
			throws NumberFormatException, ClassNotFoundException, SQLException {
		MessageService messService = new MessageService();
		Message message =  messService.getMessage(id);
		if(message == null)
			throw new NotFoundException();
		
		URI myCompleteUri = getUriForSelf(uri, message);
		
		message.addLink(myCompleteUri.toString(), "selfLink");
		return message;

	}

	private URI getUriForSelf(UriInfo uri, Message message) {
		URI myCompleteUri = uri.getBaseUriBuilder()
								.path(MyMessengerResource.class)
								.path(Integer.toString(message.getId()))
								.build();
		return myCompleteUri;
	}


	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addNewMessage(Message mess) throws ClassNotFoundException, SQLException {
		MessageService messServ = new MessageService();
		return messServ.addMessage(mess);
	}*/
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewMessage(Message mess) throws ClassNotFoundException, SQLException, URISyntaxException {
	
		MessageService messServ = new MessageService();
		messServ.addMessage(mess);
		URI uriObj = new URI("/MessengerApp/webapi/mymessages/" + String.valueOf(mess.getId())	);
		Response resp =Response.status(Status.CREATED)
				.header("myheader", uriObj)
				.entity( messServ).build();
		return resp;
	}*/
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewMessage(Message mess) throws ClassNotFoundException, SQLException, URISyntaxException {
	
		MessageService messServ = new MessageService();
		messServ.addMessage(mess);
		URI uriObj = new URI("/MessengerApp/webapi/mymessages/" + String.valueOf(mess.getId())	);
		Response resp =Response.created(uriObj)
				.entity( messServ).build();
		return resp;
	}*/
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewMessage(Message mess, @Context UriInfo uri) throws ClassNotFoundException, SQLException, URISyntaxException {
	
		MessageService messServ = new MessageService();
		messServ.addMessage(mess);
		//URI uriObj = new URI("/MessengerApp/webapi/mymessages/" + String.valueOf(mess.getId())	);
		URI uriObj = uri.getAbsolutePathBuilder().path(String.valueOf(mess.getId())).build();
		Response resp =Response.created(uriObj)
				.entity( messServ).build();
		return resp;
	}
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewMessage(Message mess, @Context UriInfo uri) throws ClassNotFoundException, SQLException, URISyntaxException {
	
		MessageService messServ = new MessageService();
		messServ.addMessage(mess);
		
		String absUriPath = uri.getAbsolutePath().toString();
		
		Response res =Response.status(Status.CREATED)
				.header("myheader", absUriPath+mess.getId())
				.entity(messServ).build();
		return res;
	}*/

	@PUT
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateExistingMessage(@PathParam("messageId") int id, Message messWithOutIdProp)
			throws ClassNotFoundException, SQLException {
		MessageService messServ = new MessageService();
		return messServ.updateMessage(id, messWithOutIdProp);
	}

	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteMyMessage(@PathParam("messageId") int id)
			throws NumberFormatException, ClassNotFoundException, SQLException {
		MessageService messService = new MessageService();
		return messService.removeMessage(id);

	}
	
	
	
	@Path("/{messageId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CommentResource m1() {
		return new CommentResource();
	}
	
	
	
	
	
	
	
	
	
	
	
}
