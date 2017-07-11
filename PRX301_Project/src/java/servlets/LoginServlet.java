/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
public class LoginServlet extends HttpServlet {

  private final String loginPage = "login.jsp";
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
    PrintWriter out = response.getWriter();
    response.setContentType("text/html;charset=UTF-8");

    String url = loginPage;
    try {
      String txtUsername = request.getParameter("txtUsername").trim();
      String txtPassword = request.getParameter("txtPassword").trim();
      
      txtUsername = txtUsername == null ? "" : txtUsername;
      txtPassword = txtPassword == null ? "" : txtPassword;
      
      HttpSession session = request.getSession();
      if (UserDAO.login(txtUsername, txtPassword)) {
        url = mainPage;
        session.setAttribute("USER_NAME", txtUsername);
      } else {
        request.setAttribute("LOGIN_ERROR_MESSAGE", "Tên đăng nhập hoặc mật khẩu bị sai");
        request.setAttribute("LOGIN_ACTIVATION", true);
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
