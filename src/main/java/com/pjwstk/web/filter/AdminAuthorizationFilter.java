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
        @WebInitParam(name = "type", value = "admin")
    }
)
public class AdminAuthorizationFilter implements Filter {

  private String authorizedType;

  private Logger logger = LoggerFactory.getLogger(getClass().getName());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    authorizedType = filterConfig.getInitParameter("type");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

    String checkedType = String.valueOf(httpServletRequest.getSession().getAttribute("userType"));
    if (checkedType == null || checkedType.isEmpty()) {
      checkedType = "guest";
    }

    if (!checkedType.equals(authorizedType)) {
      httpServletResponse.sendRedirect("/home");
      logger.warn("An unauthorized attempt to access the admin panel has occurred!");
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
