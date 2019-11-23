package com.pjwstk.mapper;

import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.domain.entity.Category;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class CategoryMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Transactional
  public Category mapCategory(RecipeResponse recipeResponse) {

    Category category = new Category();
    category.setName(recipeResponse.getCategory());
    logger.info("Category {} was mapped", category.getName());
    return category;
  }
}
