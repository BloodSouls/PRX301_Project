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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Rating")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r"),
  @NamedQuery(name = "Rating.findById", query = "SELECT r FROM Rating r WHERE r.id = :id"),
  @NamedQuery(name = "Rating.findByRating", query = "SELECT r FROM Rating r WHERE r.rating = :rating")})
public class Rating implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @XmlElement(required = true)
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "Rating")
  @XmlElement(required = true)
  private double rating;
  @JoinColumn(name = "BookId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  @XmlElement(required = true)
  private Book bookId;
  @JoinColumn(name = "UserId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  @XmlElement(required = true)
  private User userId;

  public Rating() {
  }

  public Rating(Integer id) {
    this.id = id;
  }

  public Rating(Integer id, double rating) {
    this.id = id;
    this.rating = rating;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
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
    if (!(object instanceof Rating)) {
      return false;
    }
    Rating other = (Rating) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Rating[ id=" + id + " ]";
  }
  
}
