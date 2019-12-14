package com.pjwstk.service.parsemanager;

import com.pjwstk.dao.CategoryDao;
import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.domain.entity.Category;
import com.pjwstk.web.mapper.CategoryMapper;
import com.pjwstk.web.mapper.RecipeMapper;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class DataParserService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private RecipeMapper recipeMapper;

  @EJB
  private CategoryMapper categoryMapper;

  @EJB
  private CategoryDao categoryDao;

  @Transactional
  public Object loadDataToDatabase(List<RecipeResponse> recipeResponseList) {
    for (RecipeResponse recipeResponse : recipeResponseList) {
      Category category = Optional
          .ofNullable(categoryDao.findCategoryByName(recipeResponse.getCategory()))
          .orElseGet(() -> categoryMapper.mapCategory(recipeResponse));
      category.getRecipes().add(recipeMapper.mapApiToEntity(recipeResponse, category));
      categoryDao.updateCategory(category);
    }
    logger.info("Recipes {} were saved successfully", recipeResponseList.toString());
    return null;
  }

}
