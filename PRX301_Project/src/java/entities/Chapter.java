/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Chapter")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Chapter.findAll", query = "SELECT c FROM Chapter c"),
  @NamedQuery(name = "Chapter.findById", query = "SELECT c FROM Chapter c WHERE c.id = :id"),
  @NamedQuery(name = "Chapter.findByVolumeId", query = "SELECT c FROM Chapter c WHERE c.volumeId = :volumeId"),
  @NamedQuery(name = "Chapter.findByName", query = "SELECT c FROM Chapter c WHERE c.name = :name"),
  @NamedQuery(name = "Chapter.findByNumber", query = "SELECT c FROM Chapter c WHERE c.number = :number"),
  @NamedQuery(name = "Chapter.findByDescription", query = "SELECT c FROM Chapter c WHERE c.description = :description"),
  @NamedQuery(name = "Chapter.findByReleasedDate", query = "SELECT c FROM Chapter c WHERE c.releasedDate = :releasedDate")})
public class Chapter implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "Id")
  private Integer id;
  @Basic(optional = false)
  @Column(name = "VolumeId")
  private int volumeId;
  @Basic(optional = false)
  @Column(name = "Name")
  private String name;
  @Basic(optional = false)
  @Column(name = "Number")
  private double number;
  @Column(name = "Description")
  private String description;
  @Basic(optional = false)
  @Column(name = "ReleasedDate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date releasedDate;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapterId")
  private List<Comment> commentList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapterId")
  private List<ChapterPage> chapterPageList;
  @JoinColumn(name = "BookId", referencedColumnName = "Id")
  @ManyToOne(optional = false)
  private Book bookId;

  public Chapter() {
  }

  public Chapter(Integer id) {
    this.id = id;
  }

  public Chapter(Integer id, int volumeId, String name, double number, Date releasedDate) {
    this.id = id;
    this.volumeId = volumeId;
    this.name = name;
    this.number = number;
    this.releasedDate = releasedDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getVolumeId() {
    return volumeId;
  }

  public void setVolumeId(int volumeId) {
    this.volumeId = volumeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getNumber() {
    return number;
  }

  public void setNumber(double number) {
    this.number = number;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getReleasedDate() {
    return releasedDate;
  }

  public void setReleasedDate(Date releasedDate) {
    this.releasedDate = releasedDate;
  }

  @XmlTransient
  public List<Comment> getCommentList() {
    return commentList;
  }

  public void setCommentList(List<Comment> commentList) {
    this.commentList = commentList;
  }

  @XmlTransient
  public List<ChapterPage> getChapterPageList() {
    return chapterPageList;
  }

  public void setChapterPageList(List<ChapterPage> chapterPageList) {
    this.chapterPageList = chapterPageList;
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
    if (!(object instanceof Chapter)) {
      return false;
    }
    Chapter other = (Chapter) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Chapter[ id=" + id + " ]";
  }
  
}
