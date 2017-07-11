/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.UserDAO;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ultis.Enums;

/**
 *
 * @author USER
 */
public class RegisterServlet extends HttpServlet {

  private final String registerPage = "login.jsp";
  private final String mainPage = "MainPageServlet";

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    String txtUsername = request.getParameter("txtUsername").trim();
    String txtPassword = request.getParameter("txtPassword").trim();
    String txtEmail = request.getParameter("txtEmail").trim();

    String url = registerPage;
    String errorMessage = "";

    HttpSession session = request.getSession();
    try {

      if (UserDAO.findUserByUsername(txtUsername) != null) {
        errorMessage = "Tài khoản đã tồn tại";
      } else if (UserDAO.findUserByEmail(txtEmail) != null) {
        errorMessage = "Email đã được sử dụng";
      }

      if (errorMessage == "") {
        User user = new User();
        user.setUsername(txtUsername);
        user.setPassword(txtPassword);
        user.setEmail(txtEmail);
        user.setRoleType(Enums.PersonType.NORMAL_USER.getValue());

        UserDAO.createUser(user);
        session.setAttribute("USER_NAME", txtUsername);
        url = mainPage;
      } else {
        request.setAttribute("REGISTER_ERROR_MESSAGE", errorMessage);
        request.setAttribute("LOGIN_ACTIVATION", false);
      }

    } finally {
      RequestDispatcher rd = request.getRequestDispatcher(url);
      rd.forward(request, response);
      out.close();
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
