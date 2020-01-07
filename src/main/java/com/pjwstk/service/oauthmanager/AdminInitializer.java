package com.pjwstk.service.oauthmanager;

import com.pjwstk.domain.entity.User;
import com.pjwstk.service.recipemanager.UserService;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
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
public class AdminInitializer {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());
  private static final String FILE_NAME = "admin.properties";

  @EJB
  private UserService userService;

  @PostConstruct
  protected void init() {
    User user = new User();
    user.setName(getProperty("user.name"));
    user.setEmail(getProperty("user.email"));
    user.setUserType("admin");
    userService.save(user);
    logger.info("Admin {} was created", user.getName());
  }

  private String getProperty(String property) {
    Properties properties = new Properties();
    try {
      properties.load(Objects.requireNonNull(Thread.currentThread()
          .getContextClassLoader().getResource(FILE_NAME))
          .openStream());
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return properties.getProperty(property);
  }
}
