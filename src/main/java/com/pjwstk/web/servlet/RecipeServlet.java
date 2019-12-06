package com.pjwstk.web.servlet;

import com.pjwstk.domain.entity.Category;
import com.pjwstk.domain.entity.Recipe;
import com.pjwstk.freemarker.TemplateProvider;
import com.pjwstk.service.parsemanager.CategoryService;
import com.pjwstk.service.recipemanager.IngredientService;
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
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/recipe")
public class RecipeServlet extends HttpServlet {

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @EJB
  private CategoryService categoryService;

  @EJB
  private RecipeService recipeService;

  @EJB
  private IngredientService ingredientService;

  @Inject
  private PagingService pagingService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String[] allCheckedCategoriesList = categoryService.getCategoryIds();
    String[] allCheckedTypesList = recipeService.getRecipeTypes()
        .toArray(new String[recipeService.getRecipeTypes().size()]);

    resp.setContentType("text/html;charset=UTF-8");
    List<String> pageNumber = Arrays.asList(getParametersList(req, "page", new String[]{"1"}));
    List<String> checkedCategoriesList = Arrays
        .asList(getParametersList(req, "categories[]", allCheckedCategoriesList));
    List<String> checkedTypesList = Arrays
        .asList(getParametersList(req, "types[]", allCheckedTypesList));
    List<String> checkedIngredientsList = Arrays
        .asList(getParametersList(req, "ingredients[]", new String[]{}));

    String active = req.getParameter("active");
    List<String> checkedOptionList = Arrays
        .asList(getParametersList(req, "listOptions[]", new String[]{"All Drinks"}));
    List<String> favouriteIdLst = Arrays
        .asList(getParametersList(req, "favToChangeId", new String[]{"0"}));
    Long userId = (Long) req.getSession().getAttribute("userId");

    Integer pageNo = Integer.parseInt(pageNumber.get(0));
    String checkedListOption = checkedOptionList.get(0);
    Long favouriteId = Long.parseLong(favouriteIdLst.get(0));

    List<Category> categories = categoryService.getCategoriesList();
    List<String> ingredients = ingredientService.getIngredientsList();
    List<String> types = recipeService.getRecipeTypes();

    List<Long> parsedToLongCategoriesList = checkedCategoriesList.stream()
        .map(Long::parseLong)
        .collect(Collectors.toList());

    Long recipe = 0L;

    List<Recipe> checkedCategoriesAndIngredientsAndTypes;

    checkedCategoriesAndIngredientsAndTypes = pagingService
        .filterContentList(checkedOptionList, checkedIngredientsList, parsedToLongCategoriesList,
            checkedTypesList, userId);

    List<Recipe> recipesPerPage = pagingService
        .getRecipesPerPage(pageNo, checkedCategoriesAndIngredientsAndTypes);

    List<Long> favouriteRecipeIdsFromUser = recipeService.getFavouritesListIdsForUser(userId);

    Integer lastPageNumber = pagingService
        .getLastNumberPage(checkedCategoriesAndIngredientsAndTypes);

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();

    if (categories != null || categories.isEmpty()
        || checkedCategoriesAndIngredientsAndTypes != null
        || checkedCategoriesAndIngredientsAndTypes.isEmpty()) {

      dataModel.put("userType", "user");
      dataModel.put("function", "Home");
      dataModel.put("isActive", active);
      dataModel.put("recipeListPerPage", recipesPerPage);
      dataModel.put("pageNumber", pageNo);
      dataModel.put("lastPageNumber", lastPageNumber);
      dataModel.put("categoryList", categories);
      dataModel.put("categoryListChecked", checkedCategoriesList);
      dataModel.put("ingredientList", ingredients);
      dataModel.put("ingredientListChecked", checkedIngredientsList);
      dataModel.put("typeListChecked", checkedTypesList);
      dataModel.put("email", req.getSession().getAttribute("email"));
      dataModel.put("typeList", types);
      dataModel.put("checkedListOption", checkedListOption);
      dataModel.put("favouriteIdsChecked", favouriteRecipeIdsFromUser);
      dataModel.put("name", req.getSession().getAttribute("name"));
    }
    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  private static String[] getParametersList(ServletRequest request, String paramName,
      String[] defaultValue) {

    if (request.getParameterValues(paramName) != null) {
      return request.getParameterValues(paramName);
    } else {
      return defaultValue;
    }
  }
}
