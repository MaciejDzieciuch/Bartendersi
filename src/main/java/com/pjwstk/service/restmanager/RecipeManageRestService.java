package com.pjwstk.service.restmanager;

import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.service.recipemanager.RecipeService;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/admin/recipes")
public class RecipeManageRestService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private RecipeService recipeService;

  @DELETE
  @Path("/deleteRecipe/id/{id}")
  public Response deleteRecipe(@PathParam("id") String recipeId) {

    Long id = Long.parseLong(recipeId);

    if (recipeService.getRecipeById(id) == null) {
      logger.info("Recipe with id {} not found", id);
      return Response.status(Status.NOT_FOUND).build();
    } else {
      recipeService.deleteRecipeById(id);
      logger.info("Recipe with id {} removed", id);
    }
    return Response.ok().build();
  }

  @PATCH
  @Path("/updateRecipe/id/{id}")
  public Response updateRecipe(@PathParam("id") String recipeId) {

    if (!NumberUtils.isDigits(recipeId)) {
      return Response.status(Status.BAD_REQUEST).build();
    }

    Long id = Long.parseLong(recipeId);
    if (recipeService.getRecipeById(id) == null) {
      logger.info("Recipe with id {} not found", id);
      return Response.status(Status.NOT_FOUND).build();
    } else {
      Recipe recipe = recipeService.getRecipeById(id);
      recipeService.editRecipe(recipe);
      logger.info("Recipe with id {} was updated", id);
    }
    return Response.ok().build();
  }
}
