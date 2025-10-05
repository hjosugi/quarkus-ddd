package org.acme;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.acme.domain.exception.CannotRegisterUserException;
import org.acme.domain.exception.UserNotFoundException;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

  private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

  @Override
  public Response toResponse(Throwable exception) {
    LOG.error("Unhandled exception", exception);

    int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    String error = "Internal Server Error";

    if (exception instanceof UserNotFoundException) {
      status = Response.Status.NOT_FOUND.getStatusCode();
      error = "User Not Found";
    } else if (exception instanceof CannotRegisterUserException) {
      status = Response.Status.CONFLICT.getStatusCode();
      error = "Cannot Register User";
    } else if (exception instanceof jakarta.validation.ConstraintViolationException) {
      status = Response.Status.BAD_REQUEST.getStatusCode();
      error = "Validation Failed";
    }

    var body = new ErrorResponse(status, error, exception.getMessage());
    return Response.status(status).entity(body).build();
  }

  public record ErrorResponse(int status, String error, String message) {
  }
}
