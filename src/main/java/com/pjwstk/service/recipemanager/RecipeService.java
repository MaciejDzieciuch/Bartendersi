package com.pjwstk.service.recipemanager;

import com.pjwstk.dao.RecipeDao;
import com.pjwstk.domain.entity.Category;
import com.pjwstk.domain.entity.Ingredients;
import com.pjwstk.domain.entity.Recipe;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class RecipeService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private RecipeDao recipeDao;

  public void loadRecipe(List<Recipe> recipes) {
    recipeDao.loadRecipe(recipes);
    logger.info("Recipes {} has been saved", recipes);
  }

  public List<Recipe> getRecipesList() {
    logger.info("Get recipes list");
    return recipeDao.getRecipesList();
  }

  public List<String> getRecipeTypes() {
    logger.info("Get recipe types");
    return recipeDao.getRecipeTypes();
  }

  public List<Recipe> findRecipeByCategoryIdAndIngredientAndType(List<Category> categories,
      List<Ingredients> ingredients, List<String> drinkTypes, long namesLength) {
    return recipeDao.findRecipeByCategoryIdAndIngredientAndType(categories, ingredients, drinkTypes,
        namesLength);
  }

  public List<Recipe> findRecipeByCategoryIdAndType(List<Category> categories,
      List<String> drinkTypes) {
    return recipeDao.findRecipeByCategoryIdAndType(categories, drinkTypes);
  }

  public List<Recipe> findFavouriteRecipeByCategoryIdAndType(List<Category> categories,
      List<String> drinkTypes, Long userId) {
    return recipeDao.findFavouriteRecipeByCategoryIdAndType(categories, drinkTypes, userId);
  }

  public List<Recipe> findFavouriteByCategoryIdAndIngredientAndType(List<Category> categories,
      List<Ingredients> ingredients, long namesLength, List<String> drinkTypes, Long userId) {
    return recipeDao
        .findFavouriteByCategoryIdAndIngredientAndType(categories, ingredients, namesLength,
            drinkTypes, userId);
  }

  @Transactional
  public List<Long> getFavouritesListIdsForUser(Long userId) {
    return recipeDao.getFavouritesListIds(userId);
  }
}
