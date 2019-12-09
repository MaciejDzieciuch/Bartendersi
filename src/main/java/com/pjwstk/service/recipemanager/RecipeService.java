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

  public Recipe editRecipe(Recipe recipe) {
    logger.info("Recipe: {} has been updated", recipe);
    return recipeDao.updateRecipe(recipe);
  }

  public List<Recipe> getRecipesList() {
    logger.info("Get recipes list");
    return recipeDao.getRecipesList();
  }

  public List<String> getRecipeTypes() {
    logger.info("Get recipe types");
    return recipeDao.getRecipeTypes();
  }

  @Transactional
  public Recipe getRecipeById(Long id) {
    logger.info("Get recipe by id:{}", id);
    return recipeDao.getRecipeById(id);
  }

  public void deleteRecipeById(Long id) {
    recipeDao.deleteRecipeById(id);
    logger.info("Recipe with id {} has been deleted", id);
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

  public boolean isFavourite(Long recipeId, Long userId) {
    if (recipeDao.getFavouritesListIds(userId).contains(recipeId)) {
      return true;
    }
    return false;
  }

  public List<Recipe> findRecipeForLiveSearch(String nameChars) {
    logger.info("Recipes with name contains {} found", nameChars);
    return recipeDao.findRecipeByLiveSearch(nameChars);
  }

  @Transactional
  public List<Recipe> getUnauthorizedRecipes() {
    return recipeDao.getUnauthorizedRecipes();
  }
}
