package com.pjwstk.service.parsermanager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.service.propertiesmanager.PropertiesLoaderService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;

public class RecipeDeserializer extends JsonDeserializer<RecipeResponse> {

/*  @EJB
  private PropertiesLoaderService propertiesLoaderService;*/

  @Override
  public RecipeResponse deserialize(JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    Map<String, String> ingredients = new HashMap<>();

    RecipeResponse recipeResponse = new RecipeResponse();
    JsonNode jsonNode = jsonParser.readValueAsTree();

    String[] errors = {"null"};

    for (int i = 1; i < 16; i++) {

      i = (char) i;

      JsonNode ingredientNode = jsonNode.get("strIngredient" + i);

      if (ingredientNode == null) {
        break;
      }

      String formatIngredient = ingredientNode.asText().trim();

      for (String error : errors) {

        if (!formatIngredient.equals(error) && !formatIngredient.isEmpty()) {

          ingredients.put(jsonNode.get("strIngredient" + i).asText().trim().toLowerCase(),
              jsonNode.get("strMeasure" + i).asText().trim().toLowerCase());
        }
      }
    }

    prepareRecipe(recipeResponse, jsonNode, ingredients);

    return recipeResponse;
  }

  private void prepareRecipe(RecipeResponse recipeResponse, JsonNode jsonNode,
      Map<String, String> ingredients) throws IOException {

    recipeResponse.setId(jsonNode.get("idDrink").asLong());
    recipeResponse.setDrinkName(jsonNode.get("strDrink").asText().toLowerCase());
    recipeResponse.setInstruction(jsonNode.get("strInstructions").asText().toLowerCase());
    recipeResponse.setCategory(jsonNode.get("strCategory").asText().toLowerCase());
    recipeResponse.setDrinkType(jsonNode.get("strAlcoholic").asText().toLowerCase());
    recipeResponse.setGlassType(jsonNode.get("strGlass").asText().toLowerCase());
    if ((jsonNode.get("dateModified")).isNull()) {
      //String dateFormat = propertiesLoaderService.loadDateFormatProperties();
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      recipeResponse.setModificationDate(LocalDateTime.now().format(dateTimeFormatter));
    } else {
      recipeResponse.setModificationDate(jsonNode.get("dateModified").asText());
    }
    recipeResponse.setImageUrl(jsonNode.get("strDrinkThumb").asText());
    recipeResponse.setIngredients(ingredients);
  }
}
