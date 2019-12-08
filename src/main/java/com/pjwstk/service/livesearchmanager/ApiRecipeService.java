package com.pjwstk.service.livesearchmanager;

import com.pjwstk.domain.view.RecipeLiveSearchView;
import com.pjwstk.dto.RecipeDto;
import com.pjwstk.service.recipemanager.RecipeService;
import com.pjwstk.web.mapper.RecipeMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ApiRecipeService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private RecipeService recipeService;

  @EJB
  private RecipeMapper recipeMapper;

  public List<RecipeLiveSearchView> getLiveSearchRecipe(String nameChars) {

    logger.info("Recipes with {} were mapped", nameChars);
    List<RecipeLiveSearchView> recipeLiveSearchViews = new ArrayList<>();
    recipeService.findRecipeForLiveSearch(nameChars).forEach(
        i -> recipeLiveSearchViews.add(recipeMapper.mapRecipeEntityForLiveSearch(i)));

    return recipeLiveSearchViews;
  }

  public List getRecipesList() {

    List<RecipeDto> recipeDtoList = new ArrayList<>();
    recipeService.getRecipesList()
        .forEach(i -> recipeDtoList.add(recipeMapper.mapRecipeEntityToDto(i)));

    return recipeDtoList;
  }
}
