package com.pjwstk.dao;

import com.pjwstk.domain.entity.RecipeStatistics;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StatisticsDao {

  @PersistenceContext
  private EntityManager entityManager;

  public void save(RecipeStatistics recipeStatistics) {
    entityManager.persist(recipeStatistics);
  }

  public List<Object[]> findTop10Recipes() {
    return entityManager.createNamedQuery("Recipe.findTop10Recipes").setMaxResults(10)
        .getResultList();
  }

  public List<Object[]> getCategoryRank() {
    return entityManager.createNativeQuery(
        "SELECT category.name, COUNT(categories) AS quantity FROM RecipeStatistics_categories JOIN category WHERE categories = category.id GROUP BY categories ORDER BY quantity DESC")
        .getResultList();
  }

  public List<Object[]> getRecipeCategoryRank() {
    return entityManager.createNamedQuery("Recipe.getRecipePerCategoryRank").getResultList();
  }
}
