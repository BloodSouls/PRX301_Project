package dao;

import entities.Genre;
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
  
  public static Genre getGenreByName(String name) {
    EntityManager em = Ultilities.getEntityManager();
    Genre genre = null;
    
    try {
      TypedQuery<Genre> query = em.createNamedQuery(
              "Genre.findByName",
              Genre.class).setMaxResults(1);
      query.setParameter("name", name);
      
      genre = query.getSingleResult();
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
