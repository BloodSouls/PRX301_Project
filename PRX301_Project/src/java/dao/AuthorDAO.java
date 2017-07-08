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
      TypedQuery<Author> query = em.createNamedQuery("Arthor.findById", Author.class);
      author = query.getSingleResult();
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      em.close();
    }

    return author;
  }

  public static boolean createAuthor(Author author) {
    EntityManager em = Ultilities.getEntityManager();
    boolean result = false;

    try {
      em.getTransaction().begin();
      em.persist(author);
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
