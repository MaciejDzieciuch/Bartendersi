package com.pjwstk.web.servlet;

import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.freemarker.TemplateProvider;
import com.pjwstk.service.recipemanager.RecipeService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
@WebServlet("/random-drink")
public class RandomDrinkServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private RecipeService recipeService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");

    Long userId = (Long) req.getSession().getAttribute("userId");

    List<Long> favouriteRecipeIdsFromUser = recipeService.getFavouritesListIdsForUser(userId);

    List<Recipe> recipesList = recipeService.getRecipesList();
    Random random = new Random();

    int randomRecipe = random.nextInt(recipesList.size());

    Recipe recipeById = recipeService.getRecipeById((long) randomRecipe);

    Long parseToLongRecipeId = recipeById.getId();
    Recipe responseRecipeId = recipeService.getRecipeById(parseToLongRecipeId);

    req.getSession().setAttribute("recipeType", responseRecipeId.getDrinkType());

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("responseRecipeId", responseRecipeId);
    dataModel.put("email", req.getSession().getAttribute("email"));
    dataModel.put("favouriteIdsChecked", favouriteRecipeIdsFromUser);
    dataModel.put("recipeType", req.getSession().getAttribute("recipeType"));
    dataModel.put("userType", "user");
    dataModel.put("function", "RandomDrink");
    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }
}
