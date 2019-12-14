package com.pjwstk.web.mapper;

import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.domain.entity.Category;
import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.domain.view.RecipeLiveSearchView;
import com.pjwstk.dto.IngredientDto;
import com.pjwstk.dto.RecipeDto;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class RecipeMapper {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private IngredientMapper ingredientMapper;

  @EJB
  private CategoryMapper categoryMapper;

  public Recipe mapApiToEntity(RecipeResponse recipeResponse, Category category) {

    Recipe recipe = new Recipe();
    recipe.setId(recipeResponse.getId());
    recipe.setName(recipeResponse.getDrinkName());
    recipe.setCustom(false);
    recipe.setInstruction(recipeResponse.getInstruction());
    recipe.setDrinkType(recipeResponse.getDrinkType());
    recipe.setGlassType(recipeResponse.getGlassType());
    recipe.setModificationDate(recipeResponse.getModificationDate());
    recipe.setImageUrl(recipeResponse.getImageUrl());
    recipe.setCategory(category);
    recipe.getIngredients().addAll(ingredientMapper.mapIngredients(recipeResponse));
    logger.info("Recipe {} was mapped", recipe.getName());
    return recipe;
  }

  public RecipeDto mapRecipeEntityToDto(Recipe recipe) {

    RecipeDto recipeDto = new RecipeDto();
    recipeDto.setId(recipe.getId());
    recipeDto.setName(recipe.getName());
    recipeDto.setCategory(categoryMapper.mapEntityToDto(recipe.getCategory()));
    recipeDto.setDrinkType(recipe.getDrinkType());
    recipeDto.setGlassType(recipe.getGlassType());
    recipeDto.setImageUrl(recipe.getImageUrl());
    recipeDto.setInstruction(recipe.getInstruction());
    recipeDto.setModificationDate(recipe.getModificationDate());
    Map<String, String> ingredientDtoMap = new HashMap<>();
    recipe.getIngredients().forEach(i -> {
      IngredientDto ingredientDto = ingredientMapper.mapIngredientEntityToDto(i);
      ingredientDtoMap.put(ingredientDto.getName(), ingredientDto.getMeasure());
    });
    recipeDto.setIngredients(ingredientDtoMap);
    return recipeDto;
  }

  public RecipeLiveSearchView mapRecipeEntityForLiveSearch(Recipe recipe) {

    RecipeLiveSearchView liveSearchView = new RecipeLiveSearchView();
    liveSearchView.setId(recipe.getId());
    liveSearchView.setName(recipe.getName());
    return liveSearchView;
  }

  @Transactional
  public Recipe mapUserCustomRecipe(RecipeResponse recipeResponse, Category category)
      throws IOException {

    Recipe recipe = new Recipe();

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    recipe.setId(recipeResponse.getId());
    recipe.setName(recipeResponse.getDrinkName());
    recipe.setDrinkType(recipeResponse.getDrinkType());
    recipe.setGlassType(recipeResponse.getGlassType());
    recipe.setInstruction(recipeResponse.getInstruction());
    recipe.getIngredients().addAll(ingredientMapper.mapIngredients(recipeResponse));
    recipe.setModificationDate(LocalDateTime.now().format(dateFormat));
    recipe.setImageUrl(recipeResponse.getImageUrl());
    recipe.setCategory(category);
    recipe.setCustom(true);

    logger.info("Recipe {} was mapped", recipe.getName());

    return recipe;
  }
}