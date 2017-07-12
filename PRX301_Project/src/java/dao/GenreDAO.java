package dao;

import entities.Genre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class GenreDAO {
  
  public static Genre getGenreById(int id) {
    EntityManager em = Ultilities.getEntityManager();
    Genre genre = null;
    
    try {
      TypedQuery<Genre> query = em.createNamedQuery(
              "Genre.findById", Genre.class);
      query.setParameter("id", id);
      
      genre = query.getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return genre;
  }
  
  public static List<Genre> getGenreByIdList(List<Integer> idList) {
    EntityManager em = Ultilities.getEntityManager();
    List<Genre> result = null;
    try {
      TypedQuery<Genre> query = em.createQuery(
              "SELECT g FROM Genre g WHERE g.id IN :idList",
              Genre.class);
      query.setParameter("idList", idList);
      
      result = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return result;
  }
  
  public static List<Genre> getAllGenre() {
    EntityManager em = Ultilities.getEntityManager();
    List<Genre> result = null;
    
    try {
      TypedQuery<Genre> query = em.createQuery(
              "SELECT g FROM Genre g",
              Genre.class);
      result = query.getResultList();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return result;
  }
  
  public static Genre getGenreByName(String name) {
    EntityManager em = Ultilities.getEntityManager();
    Genre genre = null;
    
    try {
      TypedQuery<Genre> query = em.createNamedQuery(
              "Genre.findByName",
              Genre.class).setMaxResults(1);
      query.setParameter("name", name);
      
      List<Genre> result = query.getResultList();
      if (!result.isEmpty()) {
        genre = result.get(0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return genre;
  }
  
  public static int createGenre(Genre genre) {
    EntityManager em = Ultilities.getEntityManager();
    
    try {
      em.getTransaction().begin();
      em.persist(genre);
      em.flush();
      em.getTransaction().commit();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
   
    return genre.getId();
  }
  
}
