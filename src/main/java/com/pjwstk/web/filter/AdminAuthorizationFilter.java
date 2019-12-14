package com.pjwstk.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(
    filterName = "AdminAuthorizationFilter",
    urlPatterns = {"/admin/*"},
    initParams = {
        @WebInitParam(name = "userType", value = "admin")
    }
)
public class AdminAuthorizationFilter implements Filter {

  private String authorizedType;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    authorizedType = filterConfig.getInitParameter("userType");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

    String userType = (String) httpServletRequest.getSession().getAttribute("userType");
    if (userType == null || userType.isEmpty()) {
      userType = "guest";
    }

    if (!userType.equals(authorizedType)) {
      httpServletResponse.sendRedirect("/home");
      logger.warn("An unauthorized attempt to access the admin panel has occurred!");
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
