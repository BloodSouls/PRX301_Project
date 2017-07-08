package dao;

import entities.Book;
import entities.Bookmark;
import entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class BookmarkDAO {

  public static Bookmark getBookmarkByUserIdAndBookId(int userId, int bookId) {
    EntityManager em = Ultilities.getEntityManager();
    Bookmark bookmark = null;

    try {
      TypedQuery<Bookmark> query = em.createQuery(
              "SELECT b FROM Bookmark b WHERE b.userId = :userId AND b.bookId = :bookId",
              Bookmark.class);
      query.setParameter("userId", userId);
      query.setParameter("bookId", bookId);

      bookmark = query.getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return bookmark;
  }

  public static List<Bookmark> getBookmarkByUserId(int userId) {
    EntityManager em = Ultilities.getEntityManager();
    List<Bookmark> result = null;

    try {
      TypedQuery<Bookmark> query = em.createQuery(
              "SELECT b FROM Bookmark b WHERE b.userId = :userId",
              Bookmark.class);
      query.setParameter("userId", userId);

      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }

  public static boolean createBookmark(User user, Book book) {
    EntityManager em = Ultilities.getEntityManager();
    boolean result = false;

    try {
      Bookmark bookmark = new Bookmark();
      bookmark.setBookId(book);
      bookmark.setUserId(user);
      bookmark.setActive(true);
      
      em.getTransaction().begin();
      em.persist(bookmark);
      em.flush();
      em.getTransaction().commit();

      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }

  public static boolean updateBookmark(int userId, int bookId, boolean active) {
    EntityManager em = Ultilities.getEntityManager();
    boolean result = false;

    try {
      TypedQuery<Bookmark> query = em.createQuery(
              "SELECT b FROM Bookmark b WHERE b.userId = :userId AND b.bookId = :bookId",
              Bookmark.class);
      query.setParameter("userId", userId);
      query.setParameter("bookId", bookId);

      Bookmark bookmark = query.getSingleResult();
      if (bookmark != null) {
        bookmark.setActive(active);
      } else {
        bookmark = new Bookmark();
        User user = new User();
        user.setId(userId);
        Book book = new Book();
        book.setId(bookId);

        bookmark.setBookId(book);
        bookmark.setUserId(user);
        bookmark.setActive(active);

        em.getTransaction().begin();
        em.persist(bookmark);
        em.flush();
        em.getTransaction().commit();
      }

      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }

}
