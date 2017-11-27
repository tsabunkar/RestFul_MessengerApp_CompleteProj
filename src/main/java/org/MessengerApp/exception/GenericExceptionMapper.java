package org.MessengerApp.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.MessengerApp.model.ErrorMessage;

/*@Provider*/
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable excep) {
		ErrorMessage errorMess = new ErrorMessage(excep.getMessage(), 500, "https://google.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMess)
				.build();
	}

}
