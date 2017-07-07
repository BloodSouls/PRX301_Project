package ultis;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Ultilities implements Serializable {
  
  private static EntityManagerFactory emf;
  EntityManager em = null;

  static {
    try {
      emf = Persistence.createEntityManagerFactory("PRX301_ProjectPU");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static EntityManager getEntityManager() {
    return emf.createEntityManager();
  }
  
  
  
}
