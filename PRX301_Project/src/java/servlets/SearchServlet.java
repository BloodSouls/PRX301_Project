/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.BookDAO;
import dao.GenreDAO;
import entities.Book;
import entities.Books;
import entities.Genre;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ultis.Const;
import ultis.Ultilities;

/**
 *
 * @author USER
 */
public class SearchServlet extends HttpServlet {

  private final String searchPage = "search.jsp";

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
    String searchValue = request.getParameter("value");
    String type = request.getParameter("type");
    String page = request.getParameter("page");
    String genreValue = request.getParameter("genreValue");

    String resultMessage = "";
    int pageNum = 0;
    if (page != null && page.matches("\\d+")) {
      pageNum = Integer.parseInt(page);
    }

    try {
      List<Integer> genreIdList = null;
      List<Book> list = null;
      if (type != null) {
        type = type.toLowerCase();
        switch (type) {
          case "mostview":
            list = BookDAO.getMostViewedBooks(Const.BOOK_QUANTITY_SEARCH_PAGE, pageNum);
            resultMessage = "PHỔ BIẾN";
            break;
          case "new":
            list = BookDAO.getNewBook(Const.BOOK_QUANTITY_SEARCH_PAGE, pageNum);
            resultMessage = "MỚI RA MẮT";
            break;
          case "update":
            list = BookDAO.getUpdatedBooks(Const.BOOK_QUANTITY_SEARCH_PAGE, pageNum);
            resultMessage = "MỚI CẬP NHẬT";
            break;
          case "genre":
            genreIdList = Ultilities.parseStringToIntList(genreValue);
            if (genreIdList != null) {
              list = BookDAO.getBookByGenreIds(genreIdList, Const.BOOK_QUANTITY_SEARCH_PAGE, pageNum);
              resultMessage = "THỂ LOẠI:";
              List<Genre> genreList = GenreDAO.getGenreByIdList(genreIdList);
              for (int i = 0; i < genreList.size(); ++i) {
                resultMessage += " " + genreList.get(i).getName();
                if (i != genreList.size() - 1) {
                  resultMessage += ",";
                }
              }
            }
            break;
          case "filter":
            searchValue = searchValue == null ? "" : searchValue;
            genreIdList = Ultilities.parseStringToIntList(genreValue);
            if (genreIdList != null) {
              list = BookDAO.searchBookByNameAndGenreList(searchValue,
                      genreIdList, Const.BOOK_QUANTITY_SEARCH_PAGE, pageNum);
              System.out.println(pageNum);
            } else {
              list = BookDAO.searchBookByName(searchValue, Const.BOOK_QUANTITY_SEARCH_PAGE, pageNum);
            }
            resultMessage = "DANH SÁCH TRUYỆN:";
            
            break;
          default:
        }
      } else if (searchValue != null) {
        searchValue = searchValue.trim();
        list = BookDAO.searchBookByName(searchValue, Const.BOOK_QUANTITY_SEARCH_PAGE, pageNum);
        resultMessage = "TÌM KIẾM: " + searchValue;
      }

      if (list == null) {
        list = new ArrayList<>();
      }

      List<Genre> genreList = GenreDAO.getAllGenre();
      String xmlString = Ultilities.marshalBooksToString(new Books(list));

      request.setAttribute("BOOK_LIST", xmlString);
      request.setAttribute("TITLE", resultMessage);
      request.setAttribute("GENRE_LIST", genreList);
    } finally {
      RequestDispatcher rd = request.getRequestDispatcher(searchPage);
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
