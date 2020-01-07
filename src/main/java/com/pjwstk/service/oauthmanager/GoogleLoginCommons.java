package com.pjwstk.service.oauthmanager;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleLoginCommons {

  private static final Logger logger = LoggerFactory.getLogger(GoogleLoginCommons.class.getName());
  private static final List<String> SCOPES = List.of("openid", "email", "profile");
  private static final String OAUTH_PROPERTIES = "oauth.properties";

  public static String buildRedirectUri(HttpServletRequest request) {
    GenericUrl genericUrl = new GenericUrl(request.getRequestURL().toString());
    genericUrl.setRawPath(loadOAuthProperties("redirect.url"));
    return genericUrl.build();
  }

  public static GoogleAuthorizationCodeFlow buildFlow() {
    return new GoogleAuthorizationCodeFlow.Builder(
        new NetHttpTransport(),
        JacksonFactory.getDefaultInstance(), loadOAuthProperties("client.id"),
        loadOAuthProperties("secret"), SCOPES)
        .setAccessType("online")
        .build();
  }

  private static String loadOAuthProperties(String property) {

    Properties properties = new Properties();
    try {
      properties.load(Objects.requireNonNull(Thread.currentThread()
          .getContextClassLoader().getResource(OAUTH_PROPERTIES))
          .openStream());
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return properties.getProperty(property);
  }
}
