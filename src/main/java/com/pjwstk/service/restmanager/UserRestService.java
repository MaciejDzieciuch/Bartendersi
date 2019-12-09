package com.pjwstk.service.restmanager;

import com.pjwstk.domain.entity.User;
import com.pjwstk.service.recipemanager.UserService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/admin/users")
@Stateless
public class UserRestService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private UserService userService;

  @DELETE
  @Path("/deleteUser/id/{id}")
  public Response deleteUser(@PathParam("id") String userId) {

    Long id = Long.parseLong(userId);

    if (userService.getUserById(id) == null) {
      logger.info("User with id {} not found", id);
      return Response.status(Status.NOT_FOUND).build();
    }

    User user = userService.getUserById(id);

    if (user.getEmail().equals("bartenders@gmail.com")) {
      logger.info("You cannot delete admin");
      return Response.status(Status.NOT_MODIFIED).build();
    } else {
      userService.deleteUserById(id);
      logger.info("User with id {} was deleted", id);
    }
    return Response.ok().build();
  }
}
