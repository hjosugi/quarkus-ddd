package org.acme.presentation;

import org.acme.application.UserApplicationService;
import org.acme.application.command.UserDeleteCommand;
import org.acme.application.command.UserGetCommand;
import org.acme.application.command.UserRegisterCommand;
import org.acme.application.command.UserUpdateCommand;
import org.acme.presentation.dto.UserIndexResponse;
import org.acme.presentation.dto.UserPostRequest;
import org.acme.presentation.dto.UserPutRequest;
import org.acme.presentation.dto.UserResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

  @Inject
  UserApplicationService userApplicationService;

  /** POST /api/users */
  @POST
  public void register(UserPostRequest request) {
    var command = new UserRegisterCommand(request.name());
    userApplicationService.register(command);
  }

  /** GET /api/users */
  @GET
  public UserIndexResponse index() {
    var users = userApplicationService.getAll()
        .stream()
        .map(UserResponse::from)
        .toList();
    return new UserIndexResponse(users);
  }

  /** GET /api/users/{id} */
  @GET
  @Path("/{id}")
  public UserResponse get(@PathParam("id") String id) {
    var user = userApplicationService.get(new UserGetCommand(id));
    return UserResponse.from(user);
  }

  /** PUT /api/users/{id} */
  @PUT
  @Path("/{id}")
  public void update(@PathParam("id") String id, UserPutRequest request) {
    var command = new UserUpdateCommand(id, request.name());
    userApplicationService.update(command);
  }

  /** DELETE /api/users/{id} */
  @DELETE
  @Path("/{id}")
  public void delete(@PathParam("id") String id) {
    var command = new UserDeleteCommand(id);
    userApplicationService.delete(command);
  }
}
