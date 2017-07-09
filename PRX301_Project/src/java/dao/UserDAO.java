package dao;

import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class UserDAO {

  public static User getUser(int userId) {
    EntityManager em = Ultilities.getEntityManager();
    User user = null;
    
    try {
      TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
      query.setParameter("id", userId);
      user = query.getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return user;
  }
  
  public static int createUser(User user) {
    EntityManager em = Ultilities.getEntityManager();
    
    try {
      em.getTransaction().begin();
      em.persist(user);
      em.flush();
      em.getTransaction().commit();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return user.getId();
  }

  public static User login(String username, String password) {
    return null;
  }

}
