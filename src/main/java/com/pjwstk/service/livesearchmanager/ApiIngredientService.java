package com.pjwstk.service.livesearchmanager;

import com.pjwstk.domain.view.IngredientLiveSearchView;
import com.pjwstk.service.recipemanager.IngredientService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ApiIngredientService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private IngredientService ingredientService;

  public List<IngredientLiveSearchView> getLiveSearchIngredient(String name) {

    logger.info("Ingredients with name: {} were mapped", name);
    List<IngredientLiveSearchView> ingredientLiveSearchViews = new ArrayList<>();
    ingredientService.findIngredientsForLiveSearch(name).forEach(i -> {
      IngredientLiveSearchView ingredientLiveSearchView = new IngredientLiveSearchView();
      ingredientLiveSearchView.setName(i);
      ingredientLiveSearchViews.add(ingredientLiveSearchView);
    });

    return ingredientLiveSearchViews;
  }
}
