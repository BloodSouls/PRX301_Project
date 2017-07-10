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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "ChapterPage")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "ChapterPage.findAll", query = "SELECT c FROM ChapterPage c"),
  @NamedQuery(name = "ChapterPage.findById", query = "SELECT c FROM ChapterPage c WHERE c.id = :id"),
  @NamedQuery(name = "ChapterPage.findByPageNumber", query = "SELECT c FROM ChapterPage c WHERE c.pageNumber = :pageNumber"),
  @NamedQuery(name = "ChapterPage.findByImageUrl", query = "SELECT c FROM ChapterPage c WHERE c.imageUrl = :imageUrl")})
public class ChapterPage implements Serializable {
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
  @Column(name = "PageNumber")
  @XmlElement(required = true)
  private int pageNumber;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "ImageUrl")
  @XmlElement(required = true)
  private String imageUrl;
  @JoinColumn(name = "ChapterId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  @XmlElement(required = true)
  private Chapter chapterId;

  public ChapterPage() {
  }

  public ChapterPage(Integer id) {
    this.id = id;
  }

  public ChapterPage(Integer id, int pageNumber, String imageUrl) {
    this.id = id;
    this.pageNumber = pageNumber;
    this.imageUrl = imageUrl;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Chapter getChapterId() {
    return chapterId;
  }

  public void setChapterId(Chapter chapterId) {
    this.chapterId = chapterId;
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
    if (!(object instanceof ChapterPage)) {
      return false;
    }
    ChapterPage other = (ChapterPage) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.ChapterPage[ id=" + id + " ]";
  }
  
}
