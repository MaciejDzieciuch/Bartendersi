package com.pjwstk.web.servlet;

import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.freemarker.TemplateProvider;
import com.pjwstk.service.recipemanager.RecipeService;
import com.pjwstk.service.statisticsmanager.StatisticsService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
@WebServlet("/recipeView")
public class SingleRecipeServlet extends HttpServlet {

  private static final String USER_TYPE = "userType";
  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private RecipeService recipeService;

  @EJB
  private StatisticsService statisticsService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");

    Long userId = Long.parseLong("2");

    String recipeId = req.getParameter("recipeId");
    Long parseToLongRecipeId = Long.parseLong(recipeId);
    Recipe responseRecipeId = recipeService.getRecipeById(parseToLongRecipeId);

    req.getSession().setAttribute("recipeType", responseRecipeId.getDrinkType());

    boolean isFavourite = recipeService.isFavourite(parseToLongRecipeId, userId);

    List<Long> longList = new ArrayList<>();
    statisticsService.save(parseToLongRecipeId, longList);

    String userType;

    if (req.getSession().getAttribute(USER_TYPE) == null) {
      userType = "guest";
    } else {
      userType = String.valueOf(req.getSession().getAttribute(USER_TYPE));
    }

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    if (responseRecipeId != null) {
      dataModel.put("function", "SingleRecipe");
      dataModel.put("responseRecipeId", responseRecipeId);
      dataModel.put("email", req.getSession().getAttribute("email"));
      dataModel.put(USER_TYPE, userType);
      dataModel.put("isFavourite", isFavourite);
      dataModel.put("recipeType", req.getSession().getAttribute("recipeType"));
    }

    try {
      template.process(dataModel, resp.getWriter());
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}
