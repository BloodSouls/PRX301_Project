package dao;

import entities.Rating;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class RatingDAO {
  
  public static Rating getRatingByUserIdAndBookId(int userId, int bookId) {
    EntityManager em = Ultilities.getEntityManager();
    Rating rating = null;
    
    try {
      TypedQuery<Rating> query = em.createQuery(
              "SELECT r FROM Rating r WHERE r.userId = :userId AND r.bookId = :bookId",
              Rating.class);
      query.setParameter("userId", userId);
      query.setParameter("bookId", bookId);
      
      rating = query.getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return rating;
  }
  
  public static List<Rating> getRatingByUserId(int userId) {
    EntityManager em = Ultilities.getEntityManager();
    List<Rating> result = null;
    
    try {
      TypedQuery<Rating> query = em.createQuery(
              "SELECT r FROM Rating r WHERE r.userId = :userId",
              Rating.class);
      query.setParameter("userId", userId);
      
      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return result;
  }
  
//  public static boolean createRating(Rating rating) {
//    boolean result = false;
//    
//    return result; 
//  }
  
}
