package com.pjwstk.service.parsermanager;

import com.pjwstk.handler.ApiDataHandler;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;

@Startup
@Singleton
@Transactional
public class ApiDataInitializer {

  @EJB
  private ApiDataHandler apiDataHandler;

  private static final String URI = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=";

  @PostConstruct
  protected void init() {
    int num = 48;
    while (num++ <= 90) {
      char sign = (char) num;
      apiDataHandler.parseDataFromAPI(URI + sign);
    }
  }
}
