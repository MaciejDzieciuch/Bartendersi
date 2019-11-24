package com.pjwstk.servlet;

import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.freemarker.TemplateProvider;
import com.pjwstk.handler.FileDataHandler;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MultipartConfig
@WebServlet("/home/upload-data")
public class LoadFileFromJsonServlet extends HttpServlet {

  @Inject
  private FileDataHandler fileDataHandler;

  @Inject
  private TemplateProvider templateProvider;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");
    Template template = templateProvider.getTemplate(getServletContext(), "uploadManager.ftlh");
    Map<String, Object> model = new HashMap<>();
    try {
      template.process(model, resp.getWriter());
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    Part part = req.getPart("drinks");
    String fileUrl = "/drinks/" + fileDataHandler.dataUploadHandler(part);

    Recipe recipe = new Recipe();
    recipe.setImageUrl(fileUrl);
    req.getSession().setAttribute("fileUpload", true);
    logger.info("Data from {} uploaded", fileUrl);
  }
}
