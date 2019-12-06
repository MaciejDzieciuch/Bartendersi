package com.pjwstk.dao;

import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.domain.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserDao {

  @PersistenceContext
  private EntityManager entityManager;

  public void save(User user) {
    entityManager.persist(user);
  }

  public User updateUser(User user) {
    return entityManager.merge(user);
  }

  public User getUserById(Long id) {
    return entityManager.find(User.class, id);
  }

  public List<User> getUsersList() {
    Query query = entityManager.createNamedQuery("User.getUsersList");
    return query.getResultList();
  }

  public void deleteUserById(Long id) {
    User user = getUserById(id);
    if (user != null) {
      entityManager.remove(user);
    }
  }

  public User findUserByEmail(String email) {
    Query query = entityManager.createNamedQuery("User.findUserByEmail");
    query.setParameter("email", email);
    return (User) query.getResultList().stream().findFirst().orElse(null);
  }

  public void editFavouritesByIdForUser(Long recipeId, Long userId) {
    User user = getUserById(userId);

    List<Recipe> favouriteRecipesListForUser = user.getRecipes();
    Recipe editRecipe = entityManager.find(Recipe.class, recipeId);

    if (favouriteRecipesListForUser.stream().anyMatch(r -> recipeId.equals(r.getId()))) {
      favouriteRecipesListForUser.remove(editRecipe);
    } else {
      favouriteRecipesListForUser.add(editRecipe);
    }
    user.setRecipes(favouriteRecipesListForUser);
  }
}
