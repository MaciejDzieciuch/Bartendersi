package com.pjwstk.dao;

import com.pjwstk.domain.entity.Category;
import com.pjwstk.domain.entity.Ingredients;
import com.pjwstk.domain.entity.Recipe;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Stateless
public class RecipeDao {

  @PersistenceContext
  private EntityManager entityManager;

  public void loadRecipe(List<Recipe> recipes) {
    for (Recipe recipe : recipes) {
      entityManager.persist(recipe);
    }
  }

  public void addRecipe(Recipe recipe) {
    entityManager.persist(recipe);
  }

  public Recipe updateRecipe(Recipe recipe) {
    return entityManager.merge(recipe);
  }

  @Transactional
  public Recipe getRecipeById(Long id) {
    return entityManager.find(Recipe.class, id);
  }

  public List<Recipe> getRecipesList() {
    Query query = entityManager.createNamedQuery("Recipe.getRecipesList");
    return query.getResultList();
  }

  public List<String> getRecipeTypeByName(List<String> types) {
    Query query = entityManager.createNamedQuery("Type.findTypeByName");
    query.setParameter("types", types);
    return query.getResultList();
  }

  public List<String> getRecipeTypes() {
    Query query = entityManager.createNamedQuery("Recipe.getRecipeTypes");
    return query.getResultList();
  }

  public List<Recipe> findRecipeByCategoryIdAndIngredientAndType(List<Category> categories,
      List<Ingredients> ingredients, List<String> drinkTypes, long namesLength) {
    Query query = entityManager
        .createNamedQuery("Recipe.findRecipeByCategoryIdAndIngredientNameAndType");
    query.setParameter("categories", categories);
    query.setParameter("ingredients", ingredients);
    query.setParameter("drinkTypes", drinkTypes);
    query.setParameter("namesLength", namesLength);
    return query.getResultList();
  }

  public List<Recipe> findRecipeByCategoryIdAndType(List<Category> categories,
      List<String> drinkTypes) {
    Query query = entityManager.createNamedQuery("Recipe.findRecipeByCategoryIdAndType");
    query.setParameter("categories", categories);
    query.setParameter("drinkTypes", drinkTypes);
    return query.getResultList();
  }

  public List<Recipe> findFavouriteRecipeByCategoryIdAndType(List<Category> categories,
      List<String> drinkTypes, Long userId) {
    Query query = entityManager
        .createNamedQuery("Recipe.findRecipeByCategoryIdAndTypeAndFavourites");
    query.setParameter("categories", categories);
    query.setParameter("drinkTypes", drinkTypes);
    query.setParameter("id", userId);
    return query.getResultList();
  }

  public List<Recipe> findFavouriteByCategoryIdAndIngredientAndType(List<Category> categories,
      List<Ingredients> ingredients, long namesLength, List<String> drinkTypes, Long userId) {
    Query query = entityManager
        .createNamedQuery("Recipe.findRecipeByCategoryAndIngredientAndTypeAndFavourites");
    query.setParameter("categories", categories);
    query.setParameter("ingredients", ingredients);
    query.setParameter("namesLength", namesLength);
    query.setParameter("drinkTypes", drinkTypes);
    query.setParameter("userId", userId);
    return query.getResultList();
  }

  @Transactional
  public List<Long> getFavouritesListIds(Long userId) {
    Query query = entityManager.createNamedQuery("Recipe.getFavouritesListIdsForUser");
    query.setParameter("id", userId);
    return query.getResultList();
  }
}
