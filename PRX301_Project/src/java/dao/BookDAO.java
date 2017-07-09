package dao;

import entities.Book;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class BookDAO {

  public static Book getBook(int bookId) {
    EntityManager em = Ultilities.getEntityManager();
    Book book = null;

    try {
      TypedQuery<Book> query = em.createNamedQuery("Book.findById", Book.class);
      query.setParameter("id", bookId);
      book = query.getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return book;
  }

  public static int createBook(Book book) {
    EntityManager em = Ultilities.getEntityManager();

    try {
      em.getTransaction().begin();
      em.persist(book);
      em.flush();
      em.getTransaction().commit();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return book.getId();
  }

  public static List<Book> getTop10MostViewedBook() {
    EntityManager em = Ultilities.getEntityManager();
    List<Book> result = null;

    try {
      TypedQuery<Book> query = em.createQuery(
              "SELECT b FROM Book b ORDER BY b.totalView DESC", Book.class)
              .setMaxResults(10);
      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }

  public static List<Book> get10NewBook() {
    EntityManager em = Ultilities.getEntityManager();
    List<Book> result = null;

    try {
      TypedQuery<Book> query = em.createQuery(
              "SELECT b FROM Book b ORDER BY b.creatingDate DESC", Book.class)
              .setMaxResults(10);

      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }

  public static List<Book> get10UpdatedBook() {
    EntityManager em = Ultilities.getEntityManager();
    List<Book> result = null;

    try {

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

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }

}
