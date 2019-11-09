package com.pjwstk.service.propertiesmanager;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import javax.ejb.Stateless;

@Stateless
public class PropertiesLoaderService {

  private static final String DATE_FORMAT_PROPERTIES = "dateformat.properties";
  private static final String DATE_FORMAT = "date.format";

  public String loadDateFormatProperties() throws IOException {

    Properties dateProperties = new Properties();
    dateProperties.load(Objects.requireNonNull(Thread.currentThread()
        .getContextClassLoader().getResource(DATE_FORMAT_PROPERTIES)).openStream());
    return dateProperties.getProperty(DATE_FORMAT);
  }
}
