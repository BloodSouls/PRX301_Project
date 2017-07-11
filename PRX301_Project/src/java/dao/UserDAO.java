package dao;

import entities.User;
import java.util.List;
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

  public static User findUserByUsername(String username) {
    EntityManager em = Ultilities.getEntityManager();
    User user = null;

    try {
      TypedQuery<User> query = em.createNamedQuery(
              "User.findByUsername",
              User.class);
      query.setParameter("username", username);
      
      List<User> list = query.getResultList();
      if (!list.isEmpty()) {
        user = list.get(0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return user;
  }

  public static User findUserByEmail(String email) {
    EntityManager em = Ultilities.getEntityManager();
    User user = null;

    try {
      TypedQuery<User> query = em.createNamedQuery(
              "User.findByEmail",
              User.class);
      query.setParameter("email", email);
      
      List<User> list = query.getResultList();
      if (!list.isEmpty()) {
        user = list.get(0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return user;
  }

  public static boolean login(String username, String password) {
    User user = findUserByUsername(username);
    if (user != null && user.getPassword().equals(password)) {
      return true;
    }
    return false;
  }

}
