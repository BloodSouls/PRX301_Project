package dao;

import entities.Author;
import entities.Book;
import entities.BookAuthorMapping;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class BookAuthorMappingDAO {
  
  public static boolean createMapping(int bookId, int authorId) {
    EntityManager em = Ultilities.getEntityManager();
    boolean result = false;
    
    try {
      Book book = new Book();
      book.setId(bookId);
      Author author = new Author();
      author.setId(authorId);
      
      BookAuthorMapping mapping = new BookAuthorMapping();
      mapping.setBookId(book);
      mapping.setAuthorId(author);
      
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
  
  public static List<BookAuthorMapping> getBookAuthorMappingByBookId(int bookId) {
    EntityManager em = Ultilities.getEntityManager();
    List<BookAuthorMapping> result = null;
    try {
      TypedQuery<BookAuthorMapping> query = em.createQuery(
              "SELECT m FROM BookAuthorMapping m WHERE m.bookId = :bookId",
              BookAuthorMapping.class);
      query.setParameter("bookId", bookId);
      
      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return result;
  }
  
  public static List<BookAuthorMapping> getBookAuthorMappingByAuthorId(int authorId) {
    EntityManager em = Ultilities.getEntityManager();
    List<BookAuthorMapping> result = null;
    
    try {
      TypedQuery<BookAuthorMapping> query = em.createQuery(
              "SELECT m FROM BookAuthorMapping m WHERE m.authorId = :authorId",
              BookAuthorMapping.class);
      query.setParameter("authorId", authorId);
      
      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return result;
  }
  
}
