package com.pjwstk.service.recipemanager;

import com.pjwstk.domain.entity.Recipe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PagingService {

  @EJB
  private RecipeService recipeService;

  @EJB
  private FilteringService filteringService;

  public List<Recipe> getRecipesPerPage(int pageNumber, List<Recipe> filterList) {

    int pageSize = 5;

    if (pageSize <= 0 || pageNumber <= 0) {
      throw new IllegalArgumentException("Invalid page size: " + pageSize);
    }

    int fromIndex = (pageNumber - 1) * pageSize;

    if (filterList == null || filterList.size() < fromIndex) {
      return Collections.emptyList();
    }
    return filterList.subList(fromIndex, Math.min(fromIndex + pageSize, filterList.size()));
  }

  public Integer getLastNumberPage(List<Recipe> recipes) {

    int pageSize = 5;
    return (recipes.size() + pageSize - 1) / pageSize;
  }

  public List<Recipe> filterContentList(List<String> checkedOptionList,
      List<String> checkedIngredientsList, List<Long> parsedToLongCategoriesList,
      List<String> checkedTypesList, Long userId) {

    List<Recipe> listWithFilters;

    if (checkedOptionList.contains("All Drinks")) {
      if (checkedIngredientsList.size() == 0 || checkedIngredientsList == null
          || checkedIngredientsList.isEmpty()) {
        listWithFilters = filteringService
            .getFiltersByCategoryAndType(parsedToLongCategoriesList, checkedTypesList);
      } else {
        listWithFilters = filteringService
            .getAllFilters(parsedToLongCategoriesList, checkedIngredientsList, checkedTypesList);
      }
    } else {
      if (checkedIngredientsList.size() == 0 || checkedIngredientsList == null
          || checkedIngredientsList.isEmpty()) {
        listWithFilters = filteringService
            .getFavouritesFiltersByCategoryAndType(parsedToLongCategoriesList, checkedTypesList,
                userId);
      } else {
        listWithFilters = filteringService
            .getFavouritesFilters(parsedToLongCategoriesList, checkedIngredientsList,
                checkedTypesList, userId);
      }
    }
    return listWithFilters;
  }

  public List<Recipe> getRecipeByFilterOption(String filterOption) {

    List<Recipe> recipes = new ArrayList<>();
    String allRecipes = "All Drinks";
    if (filterOption.equals(allRecipes)) {
      recipes = recipeService.getRecipesList();
    }
    return recipes;
  }
}
