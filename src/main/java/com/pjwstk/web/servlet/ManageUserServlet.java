package com.pjwstk.web.servlet;

import com.pjwstk.domain.entity.User;
import com.pjwstk.freemarker.TemplateProvider;
import com.pjwstk.service.recipemanager.UserService;
import com.pjwstk.service.recipemanager.UsersPageService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/admin/users")
public class ManageUserServlet extends HttpServlet {

  private static final String USER_TYPE = "userType";

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Inject
  private TemplateProvider templateProvider;

  @Inject
  private UsersPageService usersPageService;

  @EJB
  private UserService userService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");
    List<String> pageNumber = Arrays.asList(getParameterList(req, new String[]{"1"}));
    int pageNo = Integer.parseInt(pageNumber.get(0));
    int usersPerSite = 5;

    List<User> usersPerPage = usersPageService.getUsersPerPage(usersPerSite,
        pageNo, userService.getUsersList());

    Integer lastPageNo = usersPageService
        .getLastNumberPage(usersPerSite, userService.getUsersList());

    String userType;

    if (req.getSession().getAttribute(USER_TYPE) == null) {
      userType = "guest";
    } else {
      userType = String.valueOf(req.getSession().getAttribute(USER_TYPE));
    }

    Template template = templateProvider.getTemplate(getServletContext(), "home.ftlh");

    Map<String, Object> dataModel = new HashMap<>();
    dataModel.put(USER_TYPE, userType);
    dataModel.put("function", "ManageUser");
    dataModel.put("lastPageNumber", lastPageNo);
    dataModel.put("pageNumber", pageNo);
    dataModel.put("usersPerPage", usersPerPage);

    PrintWriter printWriter = resp.getWriter();

    try {
      template.process(dataModel, printWriter);
    } catch (TemplateException e) {
      logger.error(e.getMessage());
    }
  }

  private static String[] getParameterList(ServletRequest request,
      String[] defaultValue) {
    if (request.getParameterValues("page") != null) {
      return request.getParameterValues("page");
    } else {
      return defaultValue;
    }
  }
}
