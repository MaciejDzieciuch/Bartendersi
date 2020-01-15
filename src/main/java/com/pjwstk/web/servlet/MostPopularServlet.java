package com.pjwstk.web.servlet;

import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.freemarker.TemplateProvider;
import com.pjwstk.service.recipemanager.MostPopularService;
import com.pjwstk.service.recipemanager.RecipeService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/most-popular")
public class MostPopularServlet extends HttpServlet {

  private static final String USER_TYPE = "userType";

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private MostPopularService mostPopularService;

  @EJB
  private RecipeService recipeService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");

    List<Recipe> recipeList = mostPopularService.getMostPopularRecipes();

    Long userId = (Long) req.getSession().getAttribute("userId");

    List<Long> favouriteRecipeIdsFromUser = recipeService.getFavouritesListIdsForUser(userId);

    String userType;

    if (req.getSession().getAttribute(USER_TYPE) == null) {
      userType = "guest";
    } else {
      userType = String.valueOf(req.getSession().getAttribute(USER_TYPE));
    }

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("recipeList", recipeList);
    dataModel.put("favouriteIdsChecked", favouriteRecipeIdsFromUser);
    dataModel.put(USER_TYPE, userType);
    dataModel.put("function", "MostPopular");

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}
