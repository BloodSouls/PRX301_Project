package ultis;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.GenreDAO;
import entities.Author;
import entities.Book;
import entities.BookAuthorMapping;
import entities.BookGenreMapping;
import entities.Chapter;
import entities.ChapterPage;
import entities.Genre;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import javax.imageio.ImageIO;
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
    
    String realPath = Const.IMAGE_PATH;
    for (Book book : crawlData.getList()) {
      book.setCreatingDate(new Date());
      book.setActive(true);
      book.setImageUrl(downloadImage(realPath, "\\" + book.getName(),
              "bookImage", book.getImageUrl()));
      book.setBannerUrl(downloadImage(realPath, "\\" + book.getName(),
              "bannerImage", book.getBannerUrl()));
      
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
      
      if (book.getChapterList() != null) {
        for (Chapter chapter : book.getChapterList()) {
          if (chapter.getChapterPageList() != null) {
            for (ChapterPage cp : chapter.getChapterPageList()) {
              String folder = "\\" + book.getName() + "\\Chapter " + chapter.getNumber();
              cp.setImageUrl(downloadImage(realPath, folder, 
                      "" + cp.getPageNumber(), cp.getImageUrl()));
            }
          }
        }
      }

      BookDAO.createBook(book);
    }
  }
  
  public static String downloadImage(String realPath, String folderPath, String fileName, String uri) {
    String filePath = "";
    try {
      String extension = uri.substring(uri.lastIndexOf(".") + 1);
      filePath = folderPath + "\\" + fileName + "." + extension;
      
      URL url = new URL(uri);
      BufferedImage img = ImageIO.read(url);
      File file = new File(realPath + filePath);
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      ImageIO.write(img, extension, file);
    } catch (MalformedURLException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    
    return filePath;
  }

}
