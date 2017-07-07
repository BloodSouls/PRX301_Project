/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Comment")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
  @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id"),
  @NamedQuery(name = "Comment.findByMessage", query = "SELECT c FROM Comment c WHERE c.message = :message"),
  @NamedQuery(name = "Comment.findByDate", query = "SELECT c FROM Comment c WHERE c.date = :date"),
  @NamedQuery(name = "Comment.findByIsActive", query = "SELECT c FROM Comment c WHERE c.isActive = :isActive")})
public class Comment implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "Id")
  private Integer id;
  @Basic(optional = false)
  @Column(name = "Message")
  private String message;
  @Basic(optional = false)
  @Column(name = "Date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;
  @Column(name = "IsActive")
  private Boolean isActive;
  @JoinColumn(name = "ChapterId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Chapter chapterId;
  @JoinColumn(name = "UserId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private User userId;

  public Comment() {
  }

  public Comment(Integer id) {
    this.id = id;
  }

  public Comment(Integer id, String message, Date date) {
    this.id = id;
    this.message = message;
    this.date = date;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  public Chapter getChapterId() {
    return chapterId;
  }

  public void setChapterId(Chapter chapterId) {
    this.chapterId = chapterId;
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
    if (!(object instanceof Comment)) {
      return false;
    }
    Comment other = (Comment) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Comment[ id=" + id + " ]";
  }
  
}
