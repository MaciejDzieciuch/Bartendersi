package com.pjwstk.dao;

import com.pjwstk.domain.entity.Ingredients;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class IngredientDao {

  @PersistenceContext
  private EntityManager entityManager;

  public void loadIngredient(List<Ingredients> ingredients) {
    ingredients.forEach(i -> entityManager.persist(i));
  }

  public void addIngredient(Ingredients ingredients) {
    entityManager.persist(ingredients);
  }

  public Ingredients editIngredient(Ingredients ingredients) {
    return entityManager.merge(ingredients);
  }

  public Ingredients getIngredientById(Long id) {
    return entityManager.find(Ingredients.class, id);
  }

  public Ingredients getIngredientByName(String name) {
    return entityManager.find(Ingredients.class, name);
  }

  public void deleteIngredientById(Long id) {
    Ingredients ingredients = getIngredientById(id);
    if (ingredients != null) {
      entityManager.remove(ingredients);
    }
  }

  public List<Ingredients> getIngredientsByName(List<String> names) {
    Query query = entityManager.createNamedQuery("Ingredients.findIngredientByName");
    query.setParameter("names", names);
    return query.getResultList();
  }

  public Ingredients findIngredient(String name) {
    Query query = entityManager.createNamedQuery("Ingredient.findIngredientByName");
    query.setParameter("name", name);
    return (Ingredients) query.getSingleResult();
  }

  public List<String> getIngredientsList() {
    Query query = entityManager.createNamedQuery("Ingredient.getIngredientList");
    return query.getResultList();
  }

  public List<String> findIngredientsByLiveSearch(String nameChars) {
    Query query = entityManager.createNamedQuery("Ingredient.findIngredientByLiveSearch");
    query.setParameter("nameChars", "%" + nameChars + "%");
    return query.getResultList();
  }
}
