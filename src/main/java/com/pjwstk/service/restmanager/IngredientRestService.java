package com.pjwstk.service.restmanager;

import com.pjwstk.domain.view.IngredientLiveSearchView;
import com.pjwstk.service.livesearchmanager.ApiIngredientService;
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

@Path("/ingredients")
@Stateless
public class IngredientRestService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private ApiIngredientService apiIngredientService;

  @GET
  @Path("/nameChars/{nameChars}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getIngredient(@PathParam("nameChars") String nameChars) {

    logger.info("Ingredients with name contains {} was parsed to json", nameChars);
    List<IngredientLiveSearchView> ingredientLiveSearchViews = apiIngredientService
        .getLiveSearchIngredient(nameChars);

    if (ingredientLiveSearchViews.isEmpty()) {
      logger.warn("Cannot find ingredient contains: {}", nameChars);
      return Response.status(Status.NOT_FOUND).build();
    }

    logger.info("Found ingredient contains: {}", nameChars);
    return Response.ok().entity(ingredientLiveSearchViews).build();
  }
}
