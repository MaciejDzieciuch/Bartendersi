package com.pjwstk.service.recipemanager;

import com.pjwstk.dao.IngredientDao;
import com.pjwstk.domain.entity.Ingredients;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class IngredientService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private IngredientDao ingredientDao;

  public void loadIngredients(List<Ingredients> ingredients) {
    ingredientDao.loadIngredient(ingredients);
    logger.info("Recipe ingredients list has been loaded");
  }

  public void save(Ingredients ingredients) {
    ingredientDao.addIngredient(ingredients);
    logger.info("Recipe ingredient {} has been saved", ingredients);
  }

  public Ingredients editIngredients(Ingredients ingredients) {
    logger.info("Recipe ingredient {} has been updated", ingredients);
    return ingredientDao.editIngredient(ingredients);
  }

  public Ingredients getIngredientByName(String name) {
    logger.info("Get recipe ingredient {} by name", name);
    return ingredientDao.getIngredientByName(name);
  }

  public Ingredients getIngredientById(Long id) {
    logger.info("Get ingredient by id:{}", id);
    return ingredientDao.getIngredientById(id);
  }

  public void deleteIngredientById(Long id) {
    logger.info("Delete ingredient with id:{}", id);
    ingredientDao.deleteIngredientById(id);
  }

  public Ingredients findIngredient(String name) {
    logger.info("Find ingredient by name: {}", name);
    return ingredientDao.findIngredient(name);
  }

  public List<String> getIngredientsList() {
    return ingredientDao.getIngredientsList();
  }
}
