package com.pjwstk.service.recipemanager;

import com.pjwstk.dao.RecipeDao;
import com.pjwstk.dao.StatisticsDao;
import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.domain.view.StatisticsView;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class MostPopularService {

  @EJB
  private StatisticsDao statisticsDao;

  @EJB
  private RecipeDao recipeDao;

  public List<Recipe> getMostPopularRecipes() {

    List<StatisticsView> statisticsViews = new ArrayList<>();

    List<Object[]> recipeStatistics = statisticsDao.findTop10Recipes();
    recipeStatistics.forEach(r -> {
      StatisticsView statisticsView = new StatisticsView();
      statisticsView.setRecipeName(String.valueOf(r[0]));
      statisticsView.setQuantity(Long.valueOf(String.valueOf(r[1])));
      statisticsViews.add(statisticsView);
    });

    List<Recipe> recipeList = new ArrayList<>();

    statisticsViews
        .forEach(recipe -> recipeList.add(recipeDao.getRecipeByName(recipe.getRecipeName())));

    return recipeList;
  }
}
