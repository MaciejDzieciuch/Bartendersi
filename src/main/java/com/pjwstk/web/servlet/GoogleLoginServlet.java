package com.pjwstk.web.servlet;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.pjwstk.service.oauthmanager.GoogleLoginCommons;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class GoogleLoginServlet extends AbstractAuthorizationCodeServlet {

  private static final Logger logger = LoggerFactory
      .getLogger(GoogleLoginServlet.class.getName());

  @Override
  protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
    return GoogleLoginCommons.buildFlow();
  }

  @Override
  protected String getRedirectUri(HttpServletRequest httpServletRequest)
      throws ServletException, IOException {
    return GoogleLoginCommons.buildRedirectUri(httpServletRequest);
  }

  @Override
  protected String getUserId(HttpServletRequest httpServletRequest)
      throws ServletException, IOException {
    String randomUserId = UUID.randomUUID().toString();
    logger.info("Random user id {}", randomUserId);
    return randomUserId;
  }
}
