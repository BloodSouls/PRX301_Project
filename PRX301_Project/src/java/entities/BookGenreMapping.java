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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "BookGenreMapping")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "BookGenreMapping.findAll", query = "SELECT b FROM BookGenreMapping b"),
  @NamedQuery(name = "BookGenreMapping.findById", query = "SELECT b FROM BookGenreMapping b WHERE b.id = :id")})
public class BookGenreMapping implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @XmlElement(required = true)
  private Integer id;
  @JoinColumn(name = "BookId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  @XmlElement(required = true)
  private Book bookId;
  @JoinColumn(name = "GenreId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  @XmlElement(required = true)
  private Genre genreId;

  public BookGenreMapping() {
  }

  public BookGenreMapping(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Book getBookId() {
    return bookId;
  }

  public void setBookId(Book bookId) {
    this.bookId = bookId;
  }

  public Genre getGenreId() {
    return genreId;
  }

  public void setGenreId(Genre genreId) {
    this.genreId = genreId;
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
    if (!(object instanceof BookGenreMapping)) {
      return false;
    }
    BookGenreMapping other = (BookGenreMapping) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.BookGenreMapping[ id=" + id + " ]";
  }
  
}
