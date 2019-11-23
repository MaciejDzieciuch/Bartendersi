package com.pjwstk.service.parsermanager;

import com.pjwstk.dao.CategoryDao;
import com.pjwstk.domain.entity.Category;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CategoryService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private CategoryDao categoryDao;

  public void addCategory(Category category) {
    categoryDao.addCategory(category);
  }

  @Transactional
  public Category updateCategory(Category category) {
    return categoryDao.updateCategory(category);
  }

  public Category getCategoryById(Long id) {
    return categoryDao.getCategoryById(id);
  }

  public List<Category> getCategoriesList() {
    return categoryDao.getCategoriesList();
  }

  @Transactional
  public Category findCategoryByName(String name) {
    logger.info("Ingredients name: {} found in DB", name);
    return categoryDao.findCategoryByName(name);
  }
}
