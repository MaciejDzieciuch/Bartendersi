package com.pjwstk.service.parsemanager;

import com.pjwstk.dao.RecipeDao;
import com.pjwstk.domain.entity.Recipe;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
}
