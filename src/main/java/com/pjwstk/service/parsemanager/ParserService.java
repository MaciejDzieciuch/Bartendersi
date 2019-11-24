package com.pjwstk.service.parsemanager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pjwstk.domain.api.RecipeResponse;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ParserService implements Serializable {

  private static String JSON_ROOT = "drinks";
  private ObjectMapper objectMapper = new ObjectMapper();

  public JsonNode getParsingDataFromAPI(String recipes) throws IOException {
    return objectMapper.readTree(recipes);
  }

  public JsonNode getParsingDataFromFile(File file) throws IOException {
    return objectMapper.readTree(file);
  }

  public <T> Object parse(JsonNode jsonNode) throws IOException {
    return objectMapper.readValue(jsonNode.get(JSON_ROOT).toString(),
        new TypeReference<List<RecipeResponse>>() {
        });
  }
}
