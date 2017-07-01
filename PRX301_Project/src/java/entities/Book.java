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
@Table(name = "Book")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
  @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
  @NamedQuery(name = "Book.findByAuthorId", query = "SELECT b FROM Book b WHERE b.authorId = :authorId"),
  @NamedQuery(name = "Book.findByName", query = "SELECT b FROM Book b WHERE b.name = :name"),
  @NamedQuery(name = "Book.findByAnotherName", query = "SELECT b FROM Book b WHERE b.anotherName = :anotherName"),
  @NamedQuery(name = "Book.findByReleasedDate", query = "SELECT b FROM Book b WHERE b.releasedDate = :releasedDate"),
  @NamedQuery(name = "Book.findByCreatingDate", query = "SELECT b FROM Book b WHERE b.creatingDate = :creatingDate"),
  @NamedQuery(name = "Book.findByDescription", query = "SELECT b FROM Book b WHERE b.description = :description"),
  @NamedQuery(name = "Book.findByImageUrl", query = "SELECT b FROM Book b WHERE b.imageUrl = :imageUrl"),
  @NamedQuery(name = "Book.findByBannerUrl", query = "SELECT b FROM Book b WHERE b.bannerUrl = :bannerUrl"),
  @NamedQuery(name = "Book.findByTotalView", query = "SELECT b FROM Book b WHERE b.totalView = :totalView"),
  @NamedQuery(name = "Book.findByStatus", query = "SELECT b FROM Book b WHERE b.status = :status"),
  @NamedQuery(name = "Book.findByIsActive", query = "SELECT b FROM Book b WHERE b.isActive = :isActive")})
public class Book implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "Id")
  private Integer id;
  @Column(name = "AuthorId")
  private Integer authorId;
  @Basic(optional = false)
  @Column(name = "Name")
  private String name;
  @Column(name = "AnotherName")
  private String anotherName;
  @Column(name = "ReleasedDate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date releasedDate;
  @Basic(optional = false)
  @Column(name = "CreatingDate")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creatingDate;
  @Column(name = "Description")
  private String description;
  @Column(name = "ImageUrl")
  private String imageUrl;
  @Column(name = "BannerUrl")
  private String bannerUrl;
  @Basic(optional = false)
  @Column(name = "TotalView")
  private int totalView;
  @Basic(optional = false)
  @Column(name = "Status")
  private int status;
  @Column(name = "IsActive")
  private Boolean isActive;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  private List<Bookmark> bookmarkList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  private List<Rating> ratingList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  private List<Chapter> chapterList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  private List<BookAuthorMapping> bookAuthorMappingList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  private List<BookGenreMapping> bookGenreMappingList;

  public Book() {
  }

  public Book(Integer id) {
    this.id = id;
  }

  public Book(Integer id, String name, Date creatingDate, int totalView, int status) {
    this.id = id;
    this.name = name;
    this.creatingDate = creatingDate;
    this.totalView = totalView;
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAnotherName() {
    return anotherName;
  }

  public void setAnotherName(String anotherName) {
    this.anotherName = anotherName;
  }

  public Date getReleasedDate() {
    return releasedDate;
  }

  public void setReleasedDate(Date releasedDate) {
    this.releasedDate = releasedDate;
  }

  public Date getCreatingDate() {
    return creatingDate;
  }

  public void setCreatingDate(Date creatingDate) {
    this.creatingDate = creatingDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getBannerUrl() {
    return bannerUrl;
  }

  public void setBannerUrl(String bannerUrl) {
    this.bannerUrl = bannerUrl;
  }

  public int getTotalView() {
    return totalView;
  }

  public void setTotalView(int totalView) {
    this.totalView = totalView;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  @XmlTransient
  public List<Bookmark> getBookmarkList() {
    return bookmarkList;
  }

  public void setBookmarkList(List<Bookmark> bookmarkList) {
    this.bookmarkList = bookmarkList;
  }

  @XmlTransient
  public List<Rating> getRatingList() {
    return ratingList;
  }

  public void setRatingList(List<Rating> ratingList) {
    this.ratingList = ratingList;
  }

  @XmlTransient
  public List<Chapter> getChapterList() {
    return chapterList;
  }

  public void setChapterList(List<Chapter> chapterList) {
    this.chapterList = chapterList;
  }

  @XmlTransient
  public List<BookAuthorMapping> getBookAuthorMappingList() {
    return bookAuthorMappingList;
  }

  public void setBookAuthorMappingList(List<BookAuthorMapping> bookAuthorMappingList) {
    this.bookAuthorMappingList = bookAuthorMappingList;
  }

  @XmlTransient
  public List<BookGenreMapping> getBookGenreMappingList() {
    return bookGenreMappingList;
  }

  public void setBookGenreMappingList(List<BookGenreMapping> bookGenreMappingList) {
    this.bookGenreMappingList = bookGenreMappingList;
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
    if (!(object instanceof Book)) {
      return false;
    }
    Book other = (Book) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Book[ id=" + id + " ]";
  }
  
}
