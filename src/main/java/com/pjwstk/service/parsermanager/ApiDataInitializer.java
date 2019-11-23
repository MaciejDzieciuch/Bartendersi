package com.pjwstk.service.parsermanager;

import com.pjwstk.handler.ApiDataHandler;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Startup
@Singleton
@Transactional
public class ApiDataInitializer {

  @EJB
  private ApiDataHandler apiDataHandler;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  private static final String URI = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=";

  @PostConstruct
  protected void init() {
    int num = 48;
    logger.info("Load each drink from api by alphabet letter");
    while (num++ <= 90) {
      char sign = (char) num;
      apiDataHandler.parseDataFromAPI(URI + sign);
    }
  }
}
