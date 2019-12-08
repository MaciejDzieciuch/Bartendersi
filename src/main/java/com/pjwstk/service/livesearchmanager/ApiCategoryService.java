package com.pjwstk.service.livesearchmanager;

import com.pjwstk.dto.CategoryDto;
import com.pjwstk.service.parsemanager.CategoryService;
import com.pjwstk.web.mapper.CategoryMapper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ApiCategoryService {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private CategoryService categoryService;

  @EJB
  private CategoryMapper categoryMapper;

  public List<CategoryDto> getCategories() {

    logger.info("Categories of recipes were mapped");
    List<CategoryDto> categoryDtoList = new ArrayList<>();
    categoryService.getCategoriesList()
        .forEach(i -> categoryDtoList.add(categoryMapper.mapEntityToDto(i)));

    return categoryDtoList;
  }
}
