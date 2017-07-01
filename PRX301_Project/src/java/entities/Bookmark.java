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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Bookmark")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Bookmark.findAll", query = "SELECT b FROM Bookmark b"),
  @NamedQuery(name = "Bookmark.findById", query = "SELECT b FROM Bookmark b WHERE b.id = :id")})
public class Bookmark implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "Id")
  private Integer id;
  @JoinColumn(name = "BookId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Book bookId;
  @JoinColumn(name = "UserId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private User userId;

  public Bookmark() {
  }

  public Bookmark(Integer id) {
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

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
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
    if (!(object instanceof Bookmark)) {
      return false;
    }
    Bookmark other = (Bookmark) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Bookmark[ id=" + id + " ]";
  }
  
}
