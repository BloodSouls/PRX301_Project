package dao;

import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class UserDAO {
  
  public static User getUser(int userId) {
    User user = null;
    try {
      EntityManager em = Ultilities.getEntityManager();
      TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
      query.setParameter("id", userId);
      user = query.getSingleResult();
      em.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return user;
  }
  
  public static User login(String username, String password) {
    return null;
  }
  
}
