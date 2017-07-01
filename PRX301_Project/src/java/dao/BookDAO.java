package dao;

import entities.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class BookDAO {
  
  public static Book getBook(int bookId) {
    Book book = null;
    try {
      EntityManager em = Ultilities.getEntityManager();
      TypedQuery<Book> query = em.createNamedQuery("Book.findById", Book.class);
      query.setParameter("id", bookId);
      book = query.getSingleResult();
      em.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return book;
  }

  public static List<Book> getTop10MostViewedBook() {
    List<Book> result = null;
    try {
      EntityManager em = Ultilities.getEntityManager();
      TypedQuery<Book> query = em.createQuery(
              "SELECT b FROM Book b ORDER BY b.totalView DESC", Book.class)
              .setMaxResults(10);
      result = query.getResultList();
      em.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  public static List<Book> get10NewBook() {
    List<Book> result = null;
    try {
      EntityManager em = Ultilities.getEntityManager();
      TypedQuery<Book> query = em.createQuery(
              "SELECT b FROM Book b ORDER BY b.creatingDate DESC", Book.class)
              .setMaxResults(10);

      result = query.getResultList();
      em.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  public static List<Book> get10UpdatedBook() {
    List<Book> result = null;
    try {
      EntityManager em = Ultilities.getEntityManager();

      TypedQuery<Object[]> query = em.createQuery(
              "SELECT DISTINCT c.bookId, MAX(c.releasedDate)"
              + " FROM Chapter c"
              + " GROUP BY c.bookId"
              + " ORDER BY MAX(c.releasedDate) DESC", Object[].class)
              .setMaxResults(10);

      List<Object[]> list = query.getResultList();
      result = new ArrayList<Book>();
      for (Object[] obj : list) {
        result.add((Book) obj[0]);
      }
      
      em.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

}
