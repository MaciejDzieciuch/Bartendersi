package com.pjwstk.service.parsemanager;

import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.domain.entity.Category;
import com.pjwstk.mapper.CategoryMapper;
import com.pjwstk.mapper.RecipeMapper;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class FileParserService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private RecipeMapper recipeMapper;

  @EJB
  private CategoryMapper categoryMapper;

  @EJB
  private CategoryService categoryService;

  public Object loadDataToDatabase(List<RecipeResponse> recipeResponseList) {
    for (RecipeResponse recipeResponse : recipeResponseList) {
      Category category = Optional
          .ofNullable(categoryService.findCategoryByName(recipeResponse.getCategory()))
          .orElseGet(() -> categoryMapper.mapCategory(recipeResponse));
      category.getRecipes().add(recipeMapper.mapApiToEntity(recipeResponse, category));
      categoryService.updateCategory(category);
    }
    logger.info("Recipes {} were saved successfully", recipeResponseList.toString());
    return null;
  }

}
