package com.pjwstk.web.servlet;

import com.pjwstk.domain.api.RecipeResponse;
import com.pjwstk.domain.entity.Category;
import com.pjwstk.exception.ImageNotFound;
import com.pjwstk.freemarker.TemplateProvider;
import com.pjwstk.service.parsemanager.CategoryService;
import com.pjwstk.service.recipemanager.RecipeService;
import com.pjwstk.service.uploadmanager.FileUploadService;
import com.pjwstk.web.mapper.CategoryMapper;
import com.pjwstk.web.mapper.RecipeMapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Transactional
@MultipartConfig
@WebServlet("/manage-recipe")
public class ManageRecipeServlet extends HttpServlet {

  private static final String USER_TYPE = "userType";

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private FileUploadService fileUploadService;

  @EJB
  private RecipeMapper recipeMapper;

  @EJB
  private CategoryService categoryService;

  @EJB
  private RecipeService recipeService;

  @EJB
  private CategoryMapper categoryMapper;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");

    List<Category> categories = categoryService.getCategoriesList();
    List<String> types = recipeService.getRecipeTypes();

    String userType;

    if (req.getSession().getAttribute(USER_TYPE) == null) {
      userType = "guest";
    } else {
      userType = String.valueOf(req.getSession().getAttribute(USER_TYPE));
    }

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put(USER_TYPE, userType);
    dataModel.put("function", "ManageRecipe");
    dataModel.put("categoryList", categories);
    dataModel.put("typeList", types);
    dataModel.put("email", req.getSession().getAttribute("email"));

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String name = req.getParameter("name");
    String type = req.getParameter("type");
    String categoryName = req.getParameter("category");
    String glass = req.getParameter("glass");
    String instructions = req.getParameter("instructions");
    String[] ingredientList = req.getParameterValues("ingredient");
    String[] measuresList = req.getParameterValues("measure");

    Map<String, String> ingredients = new HashMap<>();

    for (int i = 0; i < ingredientList.length; i++) {
      ingredients.put(ingredientList[i], measuresList[i]);
    }

    Long recipeId = recipeService.getMaxId();

    RecipeResponse recipeResponse = new RecipeResponse();
    recipeResponse.setId(++recipeId);
    recipeResponse.setDrinkName(name);
    recipeResponse.setDrinkType(type);
    recipeResponse.setGlassType(glass);
    recipeResponse.setCategory(categoryName);
    recipeResponse.setInstruction(instructions);
    recipeResponse.setIngredients(ingredients);

    Part image = req.getPart("image");
    String imageUrl = "";
    try {
      imageUrl = "/images/" + fileUploadService
          .uploadFile(image).getName();
    } catch (ImageNotFound imageNotFound) {
      logger.warn(imageNotFound.getMessage());
    }

    recipeResponse.setImageUrl(imageUrl);

    Category category = Optional
        .ofNullable(categoryService.findCategoryByName(recipeResponse.getCategory()))
        .orElseGet(() -> categoryMapper.mapCategory(recipeResponse));
    category.getRecipes().add(recipeMapper.mapUserCustomRecipe(recipeResponse, category));
    categoryService.updateCategory(category);

    resp.sendRedirect("/home");
  }
}
