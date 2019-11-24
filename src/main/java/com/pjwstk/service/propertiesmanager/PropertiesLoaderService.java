package com.pjwstk.service.propertiesmanager;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class PropertiesLoaderService {

  private static final String FILEPATH_PROPERTIES = "filepath.properties";

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public String loadFilePathProperties() throws IOException {

    Properties filePathProperties = new Properties();
    filePathProperties.load(Objects.requireNonNull(Thread.currentThread()
        .getContextClassLoader().getResource(FILEPATH_PROPERTIES))
        .openStream());
    String filePath = filePathProperties.getProperty("upload.path");
    logger.info("Load file path properties {}", filePath);
    return filePath;
  }
}
