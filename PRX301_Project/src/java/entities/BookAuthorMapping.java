/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "BookAuthorMapping")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "BookAuthorMapping.findAll", query = "SELECT b FROM BookAuthorMapping b"),
  @NamedQuery(name = "BookAuthorMapping.findById", query = "SELECT b FROM BookAuthorMapping b WHERE b.id = :id")})
public class BookAuthorMapping implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @JoinColumn(name = "AuthorId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Author authorId;
  @JoinColumn(name = "BookId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Book bookId;

  public BookAuthorMapping() {
  }

  public BookAuthorMapping(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Author getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Author authorId) {
    this.authorId = authorId;
  }

  public Book getBookId() {
    return bookId;
  }

  public void setBookId(Book bookId) {
    this.bookId = bookId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof BookAuthorMapping)) {
      return false;
    }
    BookAuthorMapping other = (BookAuthorMapping) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.BookAuthorMapping[ id=" + id + " ]";
  }
  
}
