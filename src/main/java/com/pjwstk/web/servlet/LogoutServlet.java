package com.pjwstk.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  private static final Logger logger = LoggerFactory
      .getLogger(LogoutServlet.class.getName());

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setCharacterEncoding("UTF-8");

    HttpSession httpSession = req.getSession();
    String userEmail = (String) httpSession.getAttribute("email");
    logger.info("User {} log out successfully", userEmail);
    httpSession.invalidate();
    try {
      resp.sendRedirect("/home");
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
  }
}
