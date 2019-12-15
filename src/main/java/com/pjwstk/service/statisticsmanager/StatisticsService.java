package com.pjwstk.service.statisticsmanager;

import com.pjwstk.dao.StatisticsDao;
import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.domain.entity.RecipeStatistics;
import com.pjwstk.domain.entity.TypeStatistics;
import com.pjwstk.service.recipemanager.RecipeService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class StatisticsService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private StatisticsDao statisticsDao;

  @EJB
  private RecipeService recipeService;

  public void addRecipe(Long recipeId) {

    RecipeStatistics recipeStatistics = new RecipeStatistics();
    Recipe recipe = recipeService.getRecipeById(recipeId);
    recipeStatistics.setRecipeId(recipeId);
    recipeStatistics.setRecipeName(recipe.getName());
    recipeStatistics.setType(TypeStatistics.SINGLE_VIEW.getType());

    statisticsDao.save(recipeStatistics);
  }

  public void addCategories(List<Long> categories) {

    RecipeStatistics recipeStatistics = new RecipeStatistics();
    recipeStatistics.setCategories(getCategories(categories));
    recipeStatistics.setType(TypeStatistics.CHECKED_CATEGORY.getType());

    statisticsDao.save(recipeStatistics);
  }

  public void save(Long recipeId, List<Long> categories) {

    if (recipeId > 0) {
      addRecipe(recipeId);
    }

    addCategories(categories);
  }

  public Set<Long> getCategories(List<Long> categories) {
    return categories.stream().collect(Collectors.toSet());
  }
}
