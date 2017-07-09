package ultis;

import entities.Book;
import entities.Chapter;
import entities.ChapterPage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class CrawlData {

  private final String uri = "http://truyen.vnsharing.site/index/KhamPha/newest/";
  private List<Book> list;

  public CrawlData() {
    this.list = new ArrayList<>();
  }

  public List<Book> getList() {
    return list;
  }

  public void setList(List<Book> list) {
    this.list = list;
  }

  public void crawl(int startPageNumber, int endPageNumber) {
    try {
      if (startPageNumber <= 0 || endPageNumber <= 0 || endPageNumber < startPageNumber) {
        return;
      }
      list.clear();

      while (true) {
        List<Book> result = getBookListFromHtml(uri + startPageNumber);
        list.addAll(result);
        if (startPageNumber == endPageNumber || result.isEmpty()) {
          break;
        }
        ++startPageNumber;
      }
      for (Book book : list) {
        getBookDetailFromHtml(book.getBookDetailUrl(), book);
        if (book.getChapterList() != null) {
          for (Chapter chapter : book.getChapterList()) {
            getChapterPageFromHtml(chapter.getChapterUrl(), chapter);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void displayList() {
    if (list.isEmpty()) {
      System.out.println("List is empty");
      return;
    }

    int count = 1;
    for (Book book : list) {
      System.out.println("Book " + count);
      System.out.println("Name: " + book.getName());
      System.out.println("BookUrl: " + book.getBookDetailUrl());
      System.out.println("ImageUrl: " + book.getImageUrl() + "\n");

      System.out.println("BannerUrl: " + book.getBannerUrl());
      System.out.println("TotalView: " + book.getTotalView());
      System.out.println("AnotherName: " + book.getAnotherName());
      System.out.println("Genre: " + book.getGenreList().toString());
      System.out.println("Author: " + book.getAuthorList().toString());
      System.out.println("Status: " + book.getStatus());
      System.out.println("ReleasedDate: " + book.getReleasedDate());
      System.out.println("Description: " + book.getDescription() + "\n");

      if (book.getChapterList() != null) {
        for (Chapter chapter : book.getChapterList()) {
          System.out.println("--------");
          System.out.println("Chapter " + chapter.getNumber() + ": " + chapter.getName());
          System.out.println("ReleasedDate: " + chapter.getReleasedDate());
          System.out.println("ChapterUrl: " + chapter.getChapterUrl());

          for (ChapterPage cp : chapter.getChapterPageList()) {
            System.out.println("Page " + cp.getPageNumber() + ": " + cp.getImageUrl());
          }
        }
      }
      System.out.println("-----------------------------------------------");

      ++count;
    }
  }

  private BufferedReader getBufferedReaderFromHtml(String uri) {
    BufferedReader in = null;

    try {
      URL url = new URL(uri);
      URLConnection yc = url.openConnection();
      yc.addRequestProperty("User-Agent", "Mozilla/4.0 icompatible; MSIE 6.0 Windows NT 5.0");
      InputStream is = yc.getInputStream();
      in = new BufferedReader(new InputStreamReader(is, "UTF-8"));

    } catch (MalformedURLException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return in;
  }

  private List<Book> getBookListFromHtml(String uri) {
    List<Book> list = new ArrayList<Book>();
    BufferedReader in = getBufferedReaderFromHtml(uri);

    try {
      String line;

      // Tìm thẻ chứa thông tin
      String document = "<root>" + "\n";
      boolean inBookRow = false;
      while ((line = in.readLine()) != null) {
        line = line.trim();
        line = line.replaceAll("&", "&amp;");

        if (line.contains("<li")) { // Tìm thấy row của truyện
          if (line.contains("browse_result_item") && !line.contains("top")) {
            inBookRow = true;
            document += "<item>" + "\n";
          }
        }

        if (inBookRow) {
          if (line.contains("<a") && line.contains("class=\"thumb_wrap\"")) {
            line = line.replace(">", "/>");
            line = removeQuotes(line, "href");
            document += line + "\n";
          }

          if (line.contains("<img")) {
            line = line.replace(">", "/>");
            document += line + "\n";
          }

          if (line.contains("<a") && line.contains("class=\"title\"")) {
            line = removeQuotes(line, "href");
            if (line.contains("<script")) {
              String s1 = line.substring(0, line.indexOf(">"));
              String s2 = line.substring(line.indexOf("</script>") + 8);
              line = s1 + s2;
            }
            document += line + "\n";
          }

          if (line.contains("</li>")) {
            inBookRow = false;
            document += "</item>" + "\n";
          }
        }
      }
      document += "</root>" + "\n";
      in.close();

      // Lấy dữ liệu
      XMLInputFactory fact = XMLInputFactory.newFactory();
      fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
      fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

      InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
      XMLEventReader reader = fact.createXMLEventReader(
              new InputStreamReader(is, "UTF-8"));

      int count = 0;
      inBookRow = false;
      boolean inBookTitle = false;
      while (reader.hasNext()) {
        XMLEvent event = reader.nextEvent();

        if (event.isStartElement()) {
          StartElement ele = event.asStartElement();

          if (ele.getName().toString().equals("item")) {
            inBookRow = true;
          }

          if (inBookRow) {
            Attribute attrClass = ele.getAttributeByName(new QName("class"));

            if (ele.getName().toString().equals("a")) {
              Attribute attrHref = ele.getAttributeByName(new QName("href"));
              if (attrClass != null && attrClass.getValue().equals("thumb_wrap")) { // Lấy BookUrl
                if (attrHref != null) {
                  Book book = new Book();
                  book.setBookDetailUrl(attrHref.getValue());
                  list.add(book);
                }
              }

              if (attrClass != null && attrClass.getValue().equals("title")) { // Tìm thấy tên truyện
                inBookRow = false;
                inBookTitle = true;
              }
            }

            if (ele.getName().toString().equals("img")) {
              if (attrClass != null && attrClass.getValue().equals("lazy")) { // Lấy ImageUrl
                Attribute attrSrc = ele.getAttributeByName(new QName("data-src"));
                if (attrSrc != null) {
                  list.get(count).setImageUrl(attrSrc.getValue());
                }
              }
            }
          }
        } // end start element

        if (event.isCharacters()) {
          Characters chars = event.asCharacters();
          if (inBookTitle) {
            list.get(count).setName(chars.getData().trim());
            inBookTitle = false;
            ++count;
          }
        } // end characters element
      }

      reader.close();
    } catch (MalformedURLException ex) {
      System.out.println(uri);
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println(uri);
      ex.printStackTrace();
    } catch (XMLStreamException ex) {
      System.out.println(uri);
      ex.printStackTrace();
    }

    return list;
  }

  private void getBookDetailFromHtml(String uri, Book book) {
    BufferedReader in = getBufferedReaderFromHtml(uri);

    try {
      String line;
      String document = "<root>";
      boolean inBanner = false;
      boolean inMangaInfo = false;
      boolean inChapter = false;

      boolean isFullTag = false;
      String chapterChildContent = "";

      while ((line = in.readLine()) != null) {
        line = line.trim();
        line = line.replaceAll("&", "&amp;");

        if (line.contains("id=\"banner-section\"")) {
          inBanner = true;
        }

        if (inBanner) {
          document += line + "\n";
          if (line.contains(">")) {
            inBanner = false;
            document += "</div>" + "\n";
          }
        }

        if (line.contains("li") && line.contains("span")
                && line.toLowerCase().contains("lượt xem")) {
          String totalView = line.substring(
                  line.indexOf("<li>") + 4,
                  line.indexOf("<span>")).trim();
          document += "<a class=\"total-view\" view=\"" + totalView + "\"/>" + "\n";
        }

        if (line.contains("li") && line.contains("class=\"info_more_child\"")) {
          inMangaInfo = true;
        }

        if (inMangaInfo) {
          if (line.contains("Tên khác:")) {
            int startPos = line.indexOf("</b>") + 4;
            int endPos = line.indexOf("</li>");
            String anotherNameContent = line.substring(startPos, endPos).trim();
            anotherNameContent = anotherNameContent.replaceAll("&amp;", "and");
            anotherNameContent = anotherNameContent.replaceAll("<", "");
            anotherNameContent = anotherNameContent.replaceAll(">", "");
            line = line.substring(0, startPos) + anotherNameContent + line.substring(endPos);
          }

          document += line + "\n";
          if (line.contains("</li>")) {
            inMangaInfo = false;
          }
        }

        if (line.contains("li") && line.contains("class=\"browse_result_item\"")) {
          document += line + "\n";
          inChapter = true;
        }

        if (inChapter && line.contains("</li>")) {
          document += line + "\n";
          inChapter = false;
        }

        if (inChapter && (line.contains("<a") || line.contains("</a>"))) {
          if (line.contains("<a")) {
            isFullTag = false;
          }

          if (line.contains("</a>")) {
            isFullTag = true;
          }

          chapterChildContent += line + "\n";
          if (isFullTag) {
            if (chapterChildContent.contains("_Helma_Lennartz_\"Phần_2\"")) {
              chapterChildContent = chapterChildContent.replaceAll("2\"", "2\"\"");
            }

            chapterChildContent = removeQuotes(chapterChildContent, "href");
            chapterChildContent = removeQuotes(chapterChildContent, "title");
            document += chapterChildContent;
            chapterChildContent = "";
          }
        }

      }
      document += "</root>" + "\n";
      in.close();

      XMLInputFactory fact = XMLInputFactory.newFactory();
      fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
      fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

      InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
      XMLEventReader reader = fact.createXMLEventReader(
              new InputStreamReader(is, "UTF-8"));

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
      SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM dd, yyyy"); // April 21, 2004

      boolean inMangaInfoChild = false;
      boolean inMangaInfoChildTitle = false;
      boolean inMangaInfoChildContent = false;

      boolean inAnotherName = false;
      boolean inAuthor = false;
      boolean inStatus = false;
      boolean inReleasedDate = false;
      boolean inDescription = false;

      boolean inGenre = false; // Từng thể loại được bọc bởi thẻ "a", cách lấy dữ liệu khác 5 cái trên
      boolean inGenreContent = false;

      boolean inChapterChild = false;

      while (reader.hasNext()) {
        XMLEvent event = reader.nextEvent();

        if (event.isStartElement()) {
          StartElement ele = event.asStartElement();

          Attribute attrClass = ele.getAttributeByName(new QName("class"));

          if (ele.getName().toString().equals("div")) {
            Attribute attrId = ele.getAttributeByName(new QName("id"));
            if (attrId != null && attrId.getValue().equals("banner-section")) {
              Attribute attrStyle = ele.getAttributeByName(new QName("style"));
              String content = attrStyle.getValue().trim();
              int startPos = content.lastIndexOf("url('");
              int endPos = content.lastIndexOf("')");
              book.setBannerUrl(content.substring(startPos + 5, endPos));
            }
          } // end div ele

          if (ele.getName().toString().equals("li")) {
            if (attrClass != null && attrClass.getValue().equals("info_more_child")) {
              inMangaInfoChild = true;
            }

            if (attrClass != null && attrClass.getValue().equals("browse_result_item")) {
              book.addChapter(new Chapter());
              inChapterChild = true;
            }
          } // end li ele

          if (ele.getName().toString().equals("a")) {
            if (attrClass != null && attrClass.getValue().equals("total-view")) {
              Attribute attrView = ele.getAttributeByName(new QName("view"));
              book.setTotalView(Integer.parseInt(attrView.getValue()));
            }

            if (inGenre) {
              inGenreContent = true;
            }

            if (inChapterChild) {
              if (attrClass != null && attrClass.getValue().equals("title")) {
                Chapter chapter = book.getChapterList().get(book.getChapterList().size() - 1);

                Attribute attrHref = ele.getAttributeByName(new QName("href"));
                if (attrHref != null) {
                  chapter.setChapterUrl(attrHref.getValue());
                }

                Attribute attrTitle = ele.getAttributeByName(new QName("title"));
                if (attrTitle != null) {
                  String content = attrTitle.getValue().trim();
                  if (content.contains(book.getName())) {
                    content = content.substring(book.getName().length()).trim();
                    if (content.matches(".*\\d+.*")) { // Nếu không phải là chuỗi số
                      Pattern pattern = Pattern.compile("\\d");
                      Matcher matcher = pattern.matcher(content);
                      if (matcher.find()) {
                        content = content.substring(matcher.start());
                      }
                    } else {
                      content = "0";
                    }

                    String[] data = new String[1];
                    if (content.contains(":")) {
                      data = content.split(":");

                      if (!data[0].matches("\\d+")) { // Trường hợp số dính chung với chữ. Vd: 000A, 000B
                        data[0] = content.replaceAll("\\D+", ""); // Xóa ký tự ko phải số
                      }
                    } else if (content.contains("-")) {
                      data = content.split("-");

                      if (!data[0].matches("\\d+")) {
                        data[0] = content.replaceAll("\\D+", "");
                      }
                    } else if (content.matches("^\\d+(\\.\\d+)? .*$")) {
                      int firstSpace = content.indexOf(" ");
                      data = new String[2];
                      data[0] = content.substring(0, firstSpace);
                      data[1] = content.substring(firstSpace + 1);
                    } else {
                      if (!content.matches("\\d+")) {
                        data[0] = content.replaceAll("\\D+", "");
                      } else {
                        data[0] = content;
                      }
                    }

                    double chapterNumber = Double.parseDouble(data[0].trim());
                    chapter.setNumber(chapterNumber);
                    if (data.length > 1) {
                      chapter.setName(data[1].trim());
                    }
                  }
                }
              }
              if (attrClass != null && attrClass.getValue().equals("author")) {
                Attribute attrTitle = ele.getAttributeByName(new QName("title"));
                if (attrTitle != null) {
                  String strDate = attrTitle.getValue();
                  Date date = sdf.parse(strDate);
                  book.getChapterList().get(book.getChapterList().size() - 1).setReleasedDate(date);
                }
                inChapterChild = false;
              }
            } // end inChapterChild

          } // end "a" ele

          if (inMangaInfoChild) {
            if (ele.getName().toString().equals("b")) {
              inMangaInfoChildTitle = true;
            }
          }

        } // end start element

        if (event.isEndElement()) {
          EndElement ele = (EndElement) event;

          if (ele.getName().toString().equals("b")) {
            if (inMangaInfoChild) {
              inMangaInfoChildContent = true;
            }
          }

          if (ele.getName().toString().equals("li")) {
            inMangaInfoChild = false;
          }

        } // end "end" element

        if (event.isCharacters()) {
          Characters chars = event.asCharacters();

          if (inMangaInfoChildTitle) {
            String title = chars.getData().trim();
            switch (title) {
              case "Tên khác:":
                inAnotherName = true;
                break;
              case "Thể loại:":
                inGenre = true;
                break;
              case "Tác giả:":
                inGenre = false;
                inAuthor = true;
                break;
              case "Trạng thái:":
                inStatus = true;
                break;
              case "Ngày phát hành:":
                inReleasedDate = true;
                break;
              case "Giới thiệu truyện:":
                inDescription = true;
                break;
            }
            inMangaInfoChildTitle = false;
          }

          if (inMangaInfoChildContent) {
            String content = chars.getData().trim();

            if (inAnotherName) {
              if (content.matches("^[-].*[-]$")) {
                content = content.substring(content.indexOf("-") + 1,
                        content.lastIndexOf("-") - 1).trim();

                book.setAnotherName(content);
              }
              inAnotherName = false;
            } else if (inAuthor) {
              List<String> authorList = null;
              if (content.contains("|")) {
                authorList = splitString(content, "\\|");
              } else if (content.contains(",")) {
                authorList = splitString(content, ",");
              } else if (!content.isEmpty()) {
                authorList = new ArrayList<String>();
                authorList.add(content);
              }

              book.setAuthorList(authorList);
              inAuthor = false;
            } else if (inStatus) {
              int status = 1;
              if (content.equals("Hoàn thiện")) {
                status = Enums.BookStatus.COMPLETE.getValue();
              } else if (content.equals("Đang phát hành")) {
                status = Enums.BookStatus.NOT_COMPLETED.getValue();
              } else if (content.equals("Ngừng nữa đường")) {
                status = Enums.BookStatus.DROP.getValue();
              }
              book.setStatus(status);
              inStatus = false;
            } else if (inReleasedDate) {
              if (content.matches("^\\d{1,2}[ ]+/[ ]+\\d{1,2}[ ]+/[ ]+\\d{4}.*")) {
                content = content.replaceAll("[ ]+", "");
              }
              Calendar c = Calendar.getInstance();
              if (content.matches("\\d{1,2}/\\d{1,2}/\\d{4}[-]\\d{1,2}/\\d{1,2}/\\d{4}")) {
                content = content.substring(0, content.indexOf("-")).trim();
              }
              if (content.matches("^[a-zA-Z]+[ ]\\d{1,2}\\,[ ]\\d{4}[ –]+.*")) {
                content = content.substring(0, content.indexOf("–")).trim();
              }

              Date releasedDate = null;
              if (content.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                String[] data = content.split("/");
                int date = Integer.parseInt(data[0]);
                int month = Integer.parseInt(data[1]);
                int year = Integer.parseInt(data[2]);

                c.set(year, month, date, 0, 0, 0);
                releasedDate = c.getTime();
              } else if (content.matches("[a-zA-Z]+[ ]\\d{1,2}\\,[ ]\\d{4}")) {
                releasedDate = sdf2.parse(content);
              } else {
                try {
                  int year = Integer.parseInt(content);
                  c.set(year, 0, 1, 0, 0, 0); // Tháng trong calendar bắt đầu từ 0
                  releasedDate = c.getTime();
                } catch (Exception e) {
                }
              }
              book.setReleasedDate(releasedDate);
              inReleasedDate = false;
            } else if (inDescription) {
              book.setDescription(content);
              inDescription = false;
              inMangaInfo = false;
            }
            inMangaInfoChildContent = false;
          }

          if (inGenreContent) {
            String content = chars.getData().trim();
            if (book.getGenreList() == null) {
              book.setGenreList(new ArrayList<String>());
            }

            if (!content.isEmpty()) {
              book.getGenreList().add(content);
            }
            inGenreContent = false;
          }

        } // end characters ele

      }

      reader.close();
    } catch (IOException ex) {
      System.out.println(uri);
      ex.printStackTrace();
    } catch (XMLStreamException ex) {
      System.out.println(uri);
      ex.printStackTrace();
    } catch (ParseException ex) {
      System.out.println(uri);
      ex.printStackTrace();
    }

  }

  private void getChapterPageFromHtml(String uri, Chapter chapter) {
    System.out.println(uri);

    BufferedReader in = getBufferedReaderFromHtml(uri);

    try {
      String line;

      boolean inMangaImage = false;
      String document = "<root>";
      while ((line = in.readLine()) != null) {
        line = line.trim();
        line = line.replaceAll("&", "&amp;");

        if (line.contains("<div") && line.contains("class=\"br_frame")) {
          inMangaImage = true;
        }

        if (inMangaImage && line.contains("<img") && line.contains("manga_page")) {
          document += checkDivElement(line);
        }
      }
      document += "</root>";
      in.close();

      XMLInputFactory fact = XMLInputFactory.newFactory();
      fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
      fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

      InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
      XMLEventReader reader = fact.createXMLEventReader(
              new InputStreamReader(is, "UTF-8"));

      inMangaImage = false;
      while (reader.hasNext()) {
        XMLEvent event = reader.nextEvent();

        if (event.isStartElement()) {
          StartElement ele = event.asStartElement();

          if (ele.getName().toString().equals("div")) {
            Attribute attrClass = ele.getAttributeByName(new QName("class"));
            Attribute attrId = ele.getAttributeByName(new QName("id"));

            if (attrClass != null && attrClass.getValue().equals("br_frame")) {
              if (attrId != null) {
                ChapterPage page = new ChapterPage();
                page.setPageNumber(Integer.parseInt(attrId.getValue()));
                chapter.addChapterPage(page);
              }
              inMangaImage = true;
            }
          }

          if (inMangaImage && ele.getName().toString().equals("img")) {
            Attribute attrId = ele.getAttributeByName(new QName("id"));
            if (attrId != null && attrId.getValue().equals("manga_page")) {
              Attribute attrSrc = ele.getAttributeByName(new QName("src"));
              chapter.getChapterPageList()
                      .get(chapter.getChapterPageList().size() - 1)
                      .setImageUrl(attrSrc.getValue());
            }
          }
        } // end start element

        if (event.isEndElement()) {
          EndElement ele = event.asEndElement();
          if (inMangaImage && ele.getName().toString().equals("div")) {
            inMangaImage = false;
          }
        }
      }

      reader.close();
    } catch (IOException ex) {
      System.out.println(uri);
      ex.printStackTrace();
    } catch (XMLStreamException ex) {
      System.out.println(uri);
      ex.printStackTrace();
    }

  }

  private String removeQuotes(String ele, String tagName) {
    ele = ele.replaceAll("=\"[ ]+", "=\"");
    ele = ele.replaceAll("\"[ ]+(?!title)(?!href)(?!class)", "\"");
    String result = "";
    int pos = 0;

    if (ele.contains("</a>")) {
      for (int i = 0; i < ele.length(); ++i) {
        char character = ele.charAt(i);
        if (character == '>') {
          pos = i;
          if (i != 0 && ele.charAt(i - 1) == '"') {
            break;
          }
        }
      }
    } else {
      pos = ele.indexOf("/>");
    }

    String content = ele.substring(0, pos);
    String[] data = content.split(" ");

    String innerTagContent = "";
    boolean foundTag = false;
    boolean fullInnerContent = false;
    for (String string : data) {
      if (string.contains(tagName + "=\"")) {
        foundTag = true;
      }

      if (foundTag) {
        innerTagContent += " " + string;
        innerTagContent = innerTagContent.trim();

        char[] chars = innerTagContent.toCharArray();
        int countQuot = 0;
        for (char c : chars) {
          if (c == '"') {
            ++countQuot;
          }
        }

        if (!innerTagContent.isEmpty() && (countQuot % 2) == 0 && innerTagContent.substring(
                innerTagContent.length() - 1).equals("\"")) {
          fullInnerContent = true;
        }
      } else {
        result += string + " ";
      }

      if (fullInnerContent) {

        innerTagContent = innerTagContent.substring(tagName.length() + 2,
                innerTagContent.length() - 1);
        innerTagContent = innerTagContent.replaceAll("\"", "&quot;");
        innerTagContent = innerTagContent.replaceAll("<", "&lt;");
        innerTagContent = innerTagContent.replaceAll(">", "&gt;");
        innerTagContent = innerTagContent.replaceAll("%", "");
        result += tagName + "=\"" + innerTagContent + "\" ";

        foundTag = false;
        fullInnerContent = false;
      }
    }
    result += ele.substring(pos);
    result = result.replaceAll(" +>", ">");

    return result;
  }

  private List<String> splitString(String content, String s) {
    List<String> result = new ArrayList<>();
    String[] data = content.split(s);
    for (String string : data) {
      string = string.trim();
      if (!string.isEmpty()) {
        result.add(string);
      }
    }

    return result;
  }

  private String checkDivElement(String content) {
    Pattern pattern;
    Matcher matcher;
    int countStartDiv = 0;
    int countEndDiv = 0;

    // Count start div
    pattern = Pattern.compile("<div>");
    matcher = pattern.matcher(content);
    while (matcher.find()) {
      ++countStartDiv;
    }

    // Count end div
    pattern = Pattern.compile("</div>");
    matcher = pattern.matcher(content);
    while (matcher.find()) {
      ++countEndDiv;
    }

    if (countEndDiv > countStartDiv) {
      int pos = content.lastIndexOf("</div>");
      content = content.substring(0, pos) + content.substring(pos + 6);
    }

    return content;
  }

}
