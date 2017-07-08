package dao;

import entities.Book;
import entities.BookGenreMapping;
import entities.Genre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class BookGenreMappingDAO {

  public static boolean createMapping(int bookId, int genreId) {
    EntityManager em = Ultilities.getEntityManager();
    boolean result = false;

    try {
      Book book = new Book();
      book.setId(bookId);
      Genre genre = new Genre();
      genre.setId(genreId);
      
      BookGenreMapping mapping = new BookGenreMapping();
      mapping.setBookId(book);
      mapping.setGenreId(genre);

      em.getTransaction().begin();
      em.persist(mapping);
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

  public static List<BookGenreMapping> getBookGenreMappingByBookId(int bookId) {
    EntityManager em = Ultilities.getEntityManager();
    List<BookGenreMapping> result = null;

    try {
      TypedQuery<BookGenreMapping> query = em.createQuery(
              "SELECT m FROM BookGenreMapping m WHERE m.bookId = :bookId",
              BookGenreMapping.class);
      query.setParameter("bookId", bookId);

      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }

  public static List<BookGenreMapping> getBookGenreMappingByGenreId(int genreId) {
    EntityManager em = Ultilities.getEntityManager();
    List<BookGenreMapping> result = null;

    try {
      TypedQuery<BookGenreMapping> query = em.createQuery(
              "SELECT m FROM BookGenreMapping m WHERE m.genreId = :genreId",
              BookGenreMapping.class);
      query.setParameter("genreId", genreId);

      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }

}
