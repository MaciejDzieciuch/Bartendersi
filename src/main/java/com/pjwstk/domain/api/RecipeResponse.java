package com.pjwstk.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties({"strDrinkAlternate", "strDrinkES", "strDrinkDE", "strDrinkFR",
        "strDrinkZH-HANS", "strDrinkZH-HANT", "strTags", "strVideo", "strIBA",
        "strInstructionsES", "strInstructionsDE", "strInstructionsFR", "strInstructionsZH-HANS",
        "strInstructionsZH-HANT", "strDrinkThumb", "strCreativeCommonsConfirmed"})

public class RecipeResponse {
}
