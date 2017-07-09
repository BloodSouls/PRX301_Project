package ultis;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.GenreDAO;
import entities.Author;
import entities.Book;
import entities.BookAuthorMapping;
import entities.BookGenreMapping;
import entities.Genre;
import java.io.Serializable;
import java.util.Date;
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

  public static void crawlAndSaveDataToDB(int startPageNumber, int endPageNumber) {
    CrawlData crawlData = new CrawlData();
    crawlData.crawl(startPageNumber, endPageNumber);

    for (Book book : crawlData.getList()) {
      book.setCreatingDate(new Date());
      book.setActive(true);

      if (book.getAuthorList() != null) {
        for (String authorName : book.getAuthorList()) {
          authorName = authorName.trim();
          Author author = AuthorDAO.findAuthorByName(authorName);
          if (author == null) {
            author = new Author();
            author.setName(authorName);
            AuthorDAO.createAuthor(author);
          }

          BookAuthorMapping mapping = new BookAuthorMapping();
          mapping.setAuthorId(author);

          book.addBookAuthorMapping(mapping);
        }
      }

      if (book.getGenreList() != null) {
        for (String genreName : book.getGenreList()) {
          genreName = genreName.trim();
          Genre genre = GenreDAO.getGenreByName(genreName);
          if (genre != null) {
            BookGenreMapping mapping = new BookGenreMapping();
            mapping.setGenreId(genre);

            book.addBookGenreMapping(mapping);
          }
        }
      }

      BookDAO.createBook(book);
    }
  }

}
