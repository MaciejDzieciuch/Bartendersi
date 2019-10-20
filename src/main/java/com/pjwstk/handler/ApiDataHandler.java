package com.pjwstk.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.service.parser.ApiConsumer;
import com.pjwstk.service.parser.ParserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@Stateless
public class ApiDataHandler {

    private static final String NULL_JSON_CONTENT = "{\"drinks\":null}";

    @Inject
    private ParserService parserService;

    @EJB
    private ApiConsumer apiConsumer;

    public void parseDataFromAPI(String uri) {
        String jsonContent = apiConsumer.loadBody(uri);
        try {
            if (!jsonContent.equals(NULL_JSON_CONTENT)) {
                JsonNode jsonNode = parserService.getParsingDataFromAPI(jsonContent);
                List<RecipeResponse> recipeResponses = (List<RecipeResponse>) parserService.parse(jsonNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
