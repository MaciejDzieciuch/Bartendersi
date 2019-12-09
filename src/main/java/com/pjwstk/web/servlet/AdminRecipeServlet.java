package com.pjwstk.web.servlet;

import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.freemarker.TemplateProvider;
import com.pjwstk.service.recipemanager.PagingService;
import com.pjwstk.service.recipemanager.RecipeService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
@WebServlet("/admin/recipes")
public class AdminRecipeServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private RecipeService recipeService;

  @Inject
  private PagingService pagingService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");

    List<String> pageNumber = Arrays.asList(getParametersList(req, new String[]{"1"}));
    Integer pageNo = Integer.parseInt(pageNumber.get(0));

    List<Recipe> recipes = pagingService
        .getRecipesPerPage(pageNo, recipeService.getUnauthorizedRecipes());

    Integer lastPageNo = pagingService.getLastNumberPage(recipeService.getUnauthorizedRecipes());

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put("userType", "admin");
    dataModel.put("function", "AdminRecipe");
    dataModel.put("name", req.getSession().getAttribute("name"));
    dataModel.put("recipesList", recipes);
    dataModel.put("pageNumber", pageNo);
    dataModel.put("lastPageNumber", lastPageNo);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  private static String[] getParametersList(ServletRequest request, String[] defaultValue) {

    if (request.getParameterValues("page") != null) {
      return request.getParameterValues("page");
    } else {
      return defaultValue;
    }
  }
}
