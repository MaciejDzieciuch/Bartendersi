package com.pjwstk.mapper;

import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.domain.entity.Category;
import com.pjwstk.domain.entity.Recipe;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class RecipeMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private IngredientMapper ingredientMapper;

  public Recipe mapApiToEntity(RecipeResponse recipeResponse, Category category) {

    Recipe recipe = new Recipe();
    recipe.setId(recipeResponse.getId());
    recipe.setName(recipeResponse.getDrinkName());
    recipe.setCustom(false);
    recipe.setApproved(true);
    recipe.setInstruction(recipeResponse.getInstruction());
    recipe.setDrinkType(recipeResponse.getDrinkType());
    recipe.setModificationDate(recipeResponse.getModificationDate());
    recipe.setImageUrl(recipeResponse.getImageUrl());
    recipe.setCategory(category);
    recipe.getIngredients().addAll(ingredientMapper.mapIngredients(recipeResponse));
    logger.info("Recipe {} was mapped", recipe.getName());
    return recipe;
  }
}