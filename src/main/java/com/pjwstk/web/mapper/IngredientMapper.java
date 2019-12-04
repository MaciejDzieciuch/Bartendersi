package com.pjwstk.web.mapper;

import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.domain.entity.Ingredients;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class IngredientMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public List<Ingredients> mapIngredients(RecipeResponse recipeResponse) {

    List<Ingredients> ingredients = new ArrayList<>();

    recipeResponse.getIngredients().forEach((key, value) -> {
      Ingredients ingredient = new Ingredients();
      ingredient.setName(key);
      ingredient.setMeasure(value);
      ingredients.add(ingredient);
    });
    logger.info("Ingredients of recipe {} were mapped", recipeResponse);
    return ingredients;
  }
}
