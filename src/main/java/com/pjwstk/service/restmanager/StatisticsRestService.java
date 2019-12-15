package com.pjwstk.service.restmanager;

import com.pjwstk.dao.StatisticsDao;
import com.pjwstk.domain.view.StatisticsCategoryView;
import com.pjwstk.domain.view.StatisticsView;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
public class StatisticsRestService {

  @EJB
  private StatisticsDao statisticsDao;

  @GET
  @Path("/statistics/top")
  @Produces(MediaType.APPLICATION_JSON)
  public Response topRecipes() {

    List<StatisticsView> statisticsViews = new ArrayList<>();

    List<Object[]> recipeStatistics = statisticsDao.findTop10Recipes();
    recipeStatistics.forEach(r -> {
      StatisticsView statisticsView = new StatisticsView();
      statisticsView.setRecipeName(String.valueOf(r[0]));
      statisticsView.setQuantity(Long.valueOf(String.valueOf(r[1])));
      statisticsViews.add(statisticsView);
    });

    return Response.ok(statisticsViews).build();
  }

  @GET
  @Path("/statistics/categoryRank")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategoriesRank() {

    List<StatisticsCategoryView> statisticsCategoryViews = new ArrayList<>();

    List<Object[]> categoryStatistics = statisticsDao.getCategoryRank();
    categoryStatistics.forEach(r -> {
      StatisticsCategoryView statisticsCategoryView = new StatisticsCategoryView();
      statisticsCategoryView.setCategoryName(String.valueOf(r[0]));
      statisticsCategoryView.setQuantity(Long.valueOf(String.valueOf(r[1])));
      statisticsCategoryViews.add(statisticsCategoryView);
    });

    return Response.ok(statisticsCategoryViews).build();
  }

  @GET
  @Path("/statistics/recipeCategoryRank")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getRecipeCategoriesRank() {

    List<StatisticsCategoryView> statisticsCategoryViews = new ArrayList<>();

    List<Object[]> recipeCategoryRank = statisticsDao.getRecipeCategoryRank();
    recipeCategoryRank.forEach(r -> {
      StatisticsCategoryView statisticsCategoryView = new StatisticsCategoryView();
      statisticsCategoryView.setCategoryName(String.valueOf(r[0]));
      statisticsCategoryView.setQuantity(Long.valueOf(String.valueOf(r[1])));
      statisticsCategoryViews.add(statisticsCategoryView);
    });

    return Response.ok(statisticsCategoryViews).build();
  }
}
