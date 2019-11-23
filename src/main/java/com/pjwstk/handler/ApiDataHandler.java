package com.pjwstk.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.service.parsermanager.ApiConsumer;
import com.pjwstk.service.parsermanager.ParserService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ApiDataHandler {

  private static final String NULL_JSON_CONTENT = "{\"drinks\":null}";

  @Inject
  private ParserService parserService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @EJB
  private ApiConsumer apiConsumer;

  public void parseDataFromAPI(String uri) {
    String jsonContent = apiConsumer.loadBody(uri);
    try {
      if (!jsonContent.equals(NULL_JSON_CONTENT)) {
        JsonNode jsonNode = parserService.getParsingDataFromAPI(jsonContent);
        List<RecipeResponse> recipeResponses = (List<RecipeResponse>) parserService.parse(jsonNode);
        logger.info("All data from api was parsed");
      }
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }
}
