package com.pjwstk.service.restmanager;

import com.pjwstk.domain.view.RecipeLiveSearchView;
import com.pjwstk.service.livesearchmanager.ApiRecipeService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/recipes")
@Stateless
public class RecipeRestService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private ApiRecipeService apiRecipeService;

  @GET
  @Path("/nameChars/{nameChars}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification(@PathParam("nameChars") String nameChars) {

    logger.info("Recipes with name contains: {} were parsed to json", nameChars);
    List<RecipeLiveSearchView> recipeLiveSearchViews = apiRecipeService
        .getLiveSearchRecipe(nameChars);

    if (recipeLiveSearchViews.isEmpty()) {
      logger.warn("Cannot find recipe contains {}", nameChars);
      return Response.status(Status.NOT_FOUND).build();
    }

    logger.info("Recipe contains {} was found", nameChars);
    return Response.ok().entity(recipeLiveSearchViews).build();
  }

  @GET
  @Path("/all")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotification() {

    logger.info("Recipes were parsed to json");
    return Response.ok().entity(apiRecipeService.getRecipesList()).build();
  }
}
