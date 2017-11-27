package org.MessengerApp.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.MessengerApp.model.ErrorMessage;

@Provider
public class MyFileNotFoundExceptionMapper implements ExceptionMapper<MyFileNotFoundException>{

	@Override
	public Response toResponse(MyFileNotFoundException excep) {
		ErrorMessage errorMess = new ErrorMessage(excep.getMessage(), 404, "https://google.com");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMess)
				.build();
	}

}
