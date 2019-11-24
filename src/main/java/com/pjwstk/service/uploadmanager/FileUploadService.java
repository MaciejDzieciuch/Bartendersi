package com.pjwstk.service.uploadmanager;

import com.pjwstk.service.propertiesmanager.PropertiesLoaderService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class FileUploadService {

  @EJB
  private PropertiesLoaderService propertiesLoaderService;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  public File uploadFile(Part filePart) throws IOException {

    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    File file = new File(propertiesLoaderService.loadFilePathProperties() + fileName);
    Files.deleteIfExists(file.toPath());
    InputStream fileContent = filePart.getInputStream();
    Files.copy(fileContent, file.toPath());
    logger.info("File {} was load to application", fileName);
    fileContent.close();
    return file;
  }
}