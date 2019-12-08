package com.pjwstk.dto;

import java.util.Map;

public class RecipeDto {

  private Long id;
  private String name;
  private String instruction;
  private String drinkType;
  private String glassType;
  private String modificationDate;
  private String imageUrl;
  private CategoryDto category;
  private Map<String, String> ingredients;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public String getDrinkType() {
    return drinkType;
  }

  public void setDrinkType(String drinkType) {
    this.drinkType = drinkType;
  }

  public String getGlassType() {
    return glassType;
  }

  public void setGlassType(String glassType) {
    this.glassType = glassType;
  }

  public String getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(String modificationDate) {
    this.modificationDate = modificationDate;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public CategoryDto getCategory() {
    return category;
  }

  public void setCategory(CategoryDto category) {
    this.category = category;
  }

  public Map<String, String> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Map<String, String> ingredients) {
    this.ingredients = ingredients;
  }
}
