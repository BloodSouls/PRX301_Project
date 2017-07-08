package dao;

import entities.Chapter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class ChapterDAO {
  
  public static Chapter getChapterById(int id) {
    EntityManager em = Ultilities.getEntityManager();
    Chapter chapter = null;

    try {
      TypedQuery<Chapter> query = em.createNamedQuery("Chapter.findById", Chapter.class);
      query.setParameter("id", id);
      chapter = query.getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return chapter;
  }
  
  public static List<Chapter> getChapterByBookId(int bookId) {
    EntityManager em = Ultilities.getEntityManager();
    List<Chapter> result = null;

    try {
      TypedQuery<Chapter> query = em.createQuery(
              "SELECT c FROM Chapter c WHERE c.bookId = :bookId",
              Chapter.class);
      query.setParameter("bookId", bookId);
      
      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return result;
  }
  
  public static boolean createChapter(Chapter c) {
    EntityManager em = Ultilities.getEntityManager();
    boolean result = false;
    
    try {
      em.getTransaction().begin();
      em.persist(c);
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
  
}
