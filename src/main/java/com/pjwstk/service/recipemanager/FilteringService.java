package com.pjwstk.service.recipemanager;

import com.pjwstk.dao.CategoryDao;
import com.pjwstk.dao.IngredientDao;
import com.pjwstk.dao.RecipeDao;
import com.pjwstk.domain.entity.Category;
import com.pjwstk.domain.entity.Ingredients;
import com.pjwstk.domain.entity.Recipe;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class FilteringService {

  @EJB
  private RecipeDao recipeDao;

  @EJB
  private CategoryDao categoryDao;

  @EJB
  private IngredientDao ingredientDao;

  @EJB
  private RecipeService recipeService;

  public List<Recipe> getAllFilters(List<Long> ids, List<String> names, List<String> types) {
    List<Category> categories = categoryDao.getCategoriesById(ids);
    List<Ingredients> ingredients = ingredientDao.getIngredientsByName(names);
    List<String> drinkTypes = recipeDao.getRecipeTypeByName(types);
    long namesLength = names.size();

    return recipeService
        .findRecipeByCategoryIdAndIngredientAndType(categories, ingredients, drinkTypes,
            namesLength);
  }

  public List<Recipe> getFiltersByCategoryAndType(List<Long> ids, List<String> types) {
    List<Category> categories = categoryDao.getCategoriesById(ids);
    List<String> drinkTypes = recipeDao.getRecipeTypeByName(types);

    return recipeService.findRecipeByCategoryIdAndType(categories, drinkTypes);
  }

  public List<Recipe> getFavouritesFilters(List<Long> ids, List<String> names, List<String> types,
      Long userId) {
    List<Category> categories = categoryDao.getCategoriesById(ids);
    List<Ingredients> ingredients = ingredientDao.getIngredientsByName(names);
    List<String> drinkTypes = recipeDao.getRecipeTypeByName(types);
    long namesLength = names.size();
    return recipeService
        .findFavouriteByCategoryIdAndIngredientAndType(categories, ingredients, namesLength,
            drinkTypes, userId);
  }

  public List<Recipe> getFavouritesFiltersByCategoryAndType(List<Long> ids, List<String> types,
      Long userId) {
    List<Category> categories = categoryDao.getCategoriesById(ids);
    List<String> drinkTypes = recipeDao.getRecipeTypeByName(types);
    return recipeService.findFavouriteRecipeByCategoryIdAndType(categories, drinkTypes, userId);
  }
}
