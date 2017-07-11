/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.BookDAO;
import entities.Book;
import entities.Books;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ultis.Ultilities;

/**
 *
 * @author USER
 */
public class MainPageServlet extends HttpServlet {
  private final String mainPage = "index.jsp";
  
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
    try {
      List<Book> newBooks = BookDAO.getNewBook(10, 0);
      List<Book> updatedBooks = BookDAO.getUpdatedBooks(10, 0);
      List<Book> mostViewedBooks = BookDAO.getMostViewedBooks(10, 0);
      
      int num;
      Random r = new Random();
      do {
        num = r.nextInt(10) + 1; // random 1~10, công thức random(max - min + 1) + min 
      } while (num >= newBooks.size());
      Book bannerBook = newBooks.get(num);
      
      String strNewBooks = Ultilities.marshalBooksToString(new Books(newBooks));
      String strUpdatedBooks = Ultilities.marshalBooksToString(new Books(updatedBooks));
      String strMostViewedBooks = Ultilities.marshalBooksToString(new Books(mostViewedBooks));
      
      request.setAttribute("NEW_BOOK", strNewBooks);
      request.setAttribute("UPDATED_BOOK", strUpdatedBooks);
      request.setAttribute("MOST_VIEWED_BOOK", strMostViewedBooks);
      request.setAttribute("BANNER_BOOK", bannerBook);
      
    } finally {
      RequestDispatcher rd = request.getRequestDispatcher(mainPage);
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
