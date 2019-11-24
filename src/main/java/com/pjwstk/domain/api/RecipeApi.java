package com.pjwstk.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pjwstk.service.parsemanager.RecipeDeserializer;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties({"strDrinkAlternate", "strDrinkES", "strDrinkDE", "strDrinkFR",
    "strDrinkZH-HANS", "strDrinkZH-HANT", "strTags", "strVideo", "strIBA",
    "strInstructionsES", "strInstructionsDE", "strInstructionsFR", "strInstructionsZH-HANS",
    "strInstructionsZH-HANT", "strCreativeCommonsConfirmed"})
@JsonDeserialize(using = RecipeDeserializer.class)

public class RecipeApi {

  @JsonProperty("idDrink")
  private Long id;

  @JsonProperty("strDrink")
  private String drinkName;

  @JsonProperty("strInstructions")
  private String instruction;

  @JsonProperty("strCategory")
  private String category;

  @JsonProperty("strAlcoholic")
  private String drinkType;

  @JsonProperty("strGlass")
  private String glassType;

  @JsonProperty("dateModified")
  private String modificationDate;

  @JsonProperty("strDrinkThumb")
  private String imageUrl;

  private Map<String, String> ingredients = new HashMap<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDrinkName() {
    return drinkName;
  }

  public void setDrinkName(String drinkName) {
    this.drinkName = drinkName;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
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

  public Map<String, String> getIngredients() {
    return ingredients;
  }

  public void setIngredients(Map<String, String> ingredients) {
    this.ingredients = ingredients;
  }

  @Override
  public String toString() {
    return "RecipeApi{" +
        "id=" + id +
        ", drinkName='" + drinkName + '\'' +
        ", instruction='" + instruction + '\'' +
        ", category='" + category + '\'' +
        ", drinkType='" + drinkType + '\'' +
        ", glassType='" + glassType + '\'' +
        ", modificationDate='" + modificationDate + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        ", ingredients=" + ingredients +
        '}';
  }
}
