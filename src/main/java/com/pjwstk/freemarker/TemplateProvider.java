package com.pjwstk.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;

@RequestScoped
public class TemplateProvider {

  private static final String TEMPLATES_DIRECTORY_PATH = "WEB-INF/fm-templates";

  @Inject
  private ConfigProvider configProvider;

  public Template getTemplate(ServletContext servletContext, String templateName)
      throws IOException {

    Configuration configuration = configProvider.getConfiguration();
    configuration.setServletContextForTemplateLoading(servletContext, TEMPLATES_DIRECTORY_PATH);
    return configuration.getTemplate(templateName);
  }
}
