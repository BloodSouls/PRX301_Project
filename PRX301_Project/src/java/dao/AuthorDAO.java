package dao;

import entities.Author;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ultis.Ultilities;

public class AuthorDAO {

  public static Author getAuthorById(int id) {
    EntityManager em = Ultilities.getEntityManager();
    Author author = null;

    try {
      TypedQuery<Author> query = em.createNamedQuery("Author.findById", Author.class);
      query.setParameter("id", id);
      author = query.getSingleResult();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return author;
  }

  public static int createAuthor(Author author) {
    EntityManager em = Ultilities.getEntityManager();

    try {
      em.getTransaction().begin();
      em.persist(author);
      em.flush();
      em.getTransaction().commit();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return author.getId();
  }
  
  public static Author findAuthorByName(String name) {
    EntityManager em = Ultilities.getEntityManager();
    Author author = null;
    
    try {
      TypedQuery<Author> query = em.createNamedQuery(
              "Author.findByName",
              Author.class).setMaxResults(1);
      query.setParameter("name", name);
      
      author = query.getSingleResult();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }
    
    return author;
  }

}
