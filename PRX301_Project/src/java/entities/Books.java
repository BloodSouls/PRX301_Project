package entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bookList", propOrder = "book")
public class Books {
  
  @XmlElement(required = true)
  private List<Book> book;

  public Books() {
  }

  public Books(List<Book> book) {
    if (book == null) {
      book = new ArrayList<>();
    }
    this.book = book;
  }
  
  public List<Book> getBook() {
    return book;
  }

  public void setBook(List<Book> book) {
    this.book = book;
  }
  
}
