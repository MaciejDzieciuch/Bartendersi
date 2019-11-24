package com.pjwstk.service.parsemanager;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ApiConsumer {

  private WebTarget webTarget;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public String loadBody(String uri) {
    configureClient(uri);
    Response response = webTarget.request().get();
    logger.info("Load data from url");
    return response.readEntity(String.class);
  }

  private void configureClient(String uri) {
    Client client = ClientBuilder.newClient();
    webTarget = client.target(uri);
  }
}