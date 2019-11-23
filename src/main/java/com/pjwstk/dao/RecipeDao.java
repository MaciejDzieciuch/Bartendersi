package com.pjwstk.dao;

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
}
