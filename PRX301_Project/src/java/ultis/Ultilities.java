package ultis;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.GenreDAO;
import entities.Author;
import entities.Book;
import entities.BookAuthorMapping;
import entities.BookGenreMapping;
import entities.Books;
import entities.Chapter;
import entities.ChapterPage;
import entities.Genre;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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

  public static String marshalBooksToString(Books books) {
    try {
      JAXBContext jc = JAXBContext.newInstance(Books.class);
      Marshaller mar = jc.createMarshaller();

      StringWriter sw = new StringWriter();
      mar.marshal(books, sw);

      return sw.toString();
    } catch (JAXBException e) {
      e.printStackTrace();
    }

    return null;
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
              String folder = "\\" + book.getName() + "\\chapter " + chapter.getNumber();
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
      filePath = covertStringToURL(filePath);

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

    filePath = filePath.replaceAll("\\\\", "/");
    return filePath;
  }

  public static String covertStringToURL(String str) {
    try {
      String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
      Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
      return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public static List<Integer> parseStringToIntList(String str) {
    List<Integer> result = null;

    try {
      int[] arr = null;
      if (str.matches("\\d+")) {
        arr = new int[1];
        arr[0] = Integer.parseInt(str);
      } else if (str.matches("^\\[.*\\]$")) {
        arr = Arrays.stream(str.substring(1, str.length() - 1).split(","))
                .map(String::trim).mapToInt(Integer::parseInt).toArray();
      } else if (str.matches("^(\\d+,[ ]*)+\\d+$")) {
        arr = Arrays.stream(str.split(",")).map(String::trim)
                .mapToInt(Integer::parseInt).toArray();
      }

      if (arr != null) {
        result = new ArrayList<>();
        for (int i : arr) {
          result.add(i);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

}
