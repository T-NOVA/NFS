package eu.tnova.nfs.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	private Status status;

	public ValidationException() {
		super();
	}

	public ValidationException(String message) {
		this(message,Status.BAD_REQUEST);
	}

	public ValidationException(Throwable cause) {
		super(cause);
		status = null;
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	public ValidationException(String message, Status status) {
		super(message);
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Response getResponse() {
		return Response.status(status).entity(super.getMessage()).type(MediaType.TEXT_PLAIN).build();
	}

}
