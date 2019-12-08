package com.pjwstk.service.restmanager;

import com.pjwstk.service.recipemanager.UserService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/favourites/{recipeId}")
@Stateless
public class FavouritesRestService {

  @EJB
  private UserService userService;

  @GET
  public Response editFavouritesListForUser(@PathParam("recipeId") String recipeId, @Context
      HttpServletRequest request) {

    Long favouriteId = Long.parseLong(recipeId);
    Long userId = (Long) request.getSession().getAttribute("userId");

    userService.editFavouritesByIdForUser(favouriteId, userId);

    return Response.ok().build();
  }
}
