package com.pjwstk.service.recipemanager;

import com.pjwstk.dao.UserDao;
import com.pjwstk.domain.entity.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private UserDao userDao;

  public void save(User user) {
    userDao.save(user);
    logger.info("User {} has been saved", user);
  }

  public User updateUser(User user) {
    logger.info("User {} has been updated", user);
    return userDao.updateUser(user);
  }

  public User getUserById(Long id) {
    logger.info("Get user:{} by id", id);
    return userDao.getUserById(id);
  }

  public List<User> getUsersList() {
    logger.info("Get user list");
    return userDao.getUsersList();
  }

  public void deleteUserById(Long id) {
    userDao.deleteUserById(id);
    logger.info("User:{} has been deleted", id);
  }

  public User findUserByEmail(String email) {
    logger.info("Get user by email: {}", email);
    return userDao.findUserByEmail(email);
  }

  public void editFavouritesByIdForUser(Long recipeId, Long userId) {
    userDao.editFavouritesByIdForUser(recipeId, userId);
    logger.info("Recipe id:{} changed its favourites status by user id:{}", recipeId, userId);
  }
}
