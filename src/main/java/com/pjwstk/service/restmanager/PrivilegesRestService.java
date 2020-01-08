package com.pjwstk.service.restmanager;

import com.pjwstk.domain.entity.User;
import com.pjwstk.service.recipemanager.UserService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/admin")
@Stateless
public class PrivilegesRestService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private UserService userService;

  @PATCH
  @Path("/addAdminPermissions/id/{id}")
  public Response getAdminPrivileges(@PathParam("id") String userId) {

    if (!NumberUtils.isDigits(userId)) {
      return Response.status(Status.BAD_REQUEST).build();
    }

    Long id = Long.parseLong(userId);

    if (userService.getUserById(id) == null) {
      logger.info("User with id {} not found", id);
      return Response.status(Status.NOT_FOUND).build();
    }

    User user = userService.getUserById(id);

    if (user.getEmail().equals("bart3nd3rs@gmail.com")) {
      return Response.status(Status.NOT_MODIFIED).build();
    } else if (user.getUserType().equals("user")) {
      user.setUserType("admin");
      userService.updateUser(user);
      logger.info("User with id {} received admin privileges", id);
    } else {
      return Response.status(Status.NOT_MODIFIED).build();
    }
    return Response.ok().build();
  }

  @PATCH
  @Path("/revokeAdminPermissions/id/{id}")
  public Response revokeAdminPrivileges(@PathParam("id") String userId) {

    if (!NumberUtils.isDigits(userId)) {
      return Response.status(Status.BAD_REQUEST).build();
    }

    Long id = Long.parseLong(userId);

    if (userService.getUserById(id) == null) {
      logger.info("User not found with id {}", id);
      return Response.status(Status.NOT_FOUND).build();
    }
    User user = userService.getUserById(id);

    if (id == 1) {
      return Response.status(Status.NOT_MODIFIED).build();
    } else if (user.getUserType().equals("admin")) {
      user.setUserType("user");
      userService.updateUser(user);
      logger.info("User with id {} revoke admin privileges", id);
    } else {
      return Response.status(Status.NOT_MODIFIED).build();
    }
    return Response.ok().build();
  }
}