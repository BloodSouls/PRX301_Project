/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "Book")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book", propOrder = {"id", "name", "anotherName", "releasedDate", "description",
 "imageUrl", "bannerUrl", "totalView", "status"})
@NamedQueries({
  @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
  @NamedQuery(name = "Book.findById", query = "SELECT b FROM Book b WHERE b.id = :id"),
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
  @NotNull
  @Column(name = "Id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @XmlElement(required = true)
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 200)
  @Column(name = "Name")
  @XmlElement(required = true)
  private String name;
  @Size(max = 500)
  @Column(name = "AnotherName")
  @XmlElement(required = true)
  private String anotherName;
  @Column(name = "ReleasedDate")
  @Temporal(TemporalType.TIMESTAMP)
  @XmlElement(required = true)
  private Date releasedDate;
  @Basic(optional = false)
  @NotNull
  @Column(name = "CreatingDate")
  @Temporal(TemporalType.TIMESTAMP)
  @XmlTransient
  private Date creatingDate;
  @Size(max = 1073741823)
  @Column(name = "Description")
  @XmlElement(required = true)
  private String description;
  @Size(max = 255)
  @Column(name = "ImageUrl")
  @XmlElement(required = true)
  private String imageUrl;
  @Size(max = 255)
  @Column(name = "BannerUrl")
  @XmlElement(required = true)
  private String bannerUrl;
  @Basic(optional = false)
  @NotNull
  @Column(name = "TotalView")
  @XmlElement(required = true)
  private int totalView;
  @Basic(optional = false)
  @NotNull
  @Column(name = "Status")
  @XmlElement(required = true)
  private int status;
  @Column(name = "IsActive")
  @XmlTransient
  private Boolean isActive;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  @XmlTransient
  private List<Bookmark> bookmarkList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  @XmlTransient
  private List<Rating> ratingList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  @XmlTransient
  @OrderBy("number DESC")
  private List<Chapter> chapterList;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  @XmlTransient
  private List<BookAuthorMapping> bookAuthorMappingList;
  @OrderBy("genreId ASC")
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
  @XmlTransient
  private List<BookGenreMapping> bookGenreMappingList;
  
  @Transient
  @XmlTransient
  private String bookDetailUrl;
  @Transient
  @XmlTransient
  private List<String> genreList;
  @Transient
  @XmlTransient
  private List<String> authorList;
  
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

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean isActive) {
    this.isActive = isActive;
  }
  
  public String getBookDetailUrl() {
    return bookDetailUrl;
  }

  public void setBookDetailUrl(String bookDetailUrl) {
    this.bookDetailUrl = bookDetailUrl;
  }

  public List<String> getGenreList() {
    return genreList;
  }

  public void setGenreList(List<String> genreList) {
    this.genreList = genreList;
  }

  public List<String> getAuthorList() {
    return authorList;
  }

  public void setAuthorList(List<String> authorList) {
    this.authorList = authorList;
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
  
  public void addChapter(Chapter chapter) {
    if (this.chapterList == null) {
      this.chapterList = new ArrayList<>();
    }
    chapter.setBookId(this);
    this.chapterList.add(chapter);
  }

  @XmlTransient
  public List<BookAuthorMapping> getBookAuthorMappingList() {
    return bookAuthorMappingList;
  }

  public void setBookAuthorMappingList(List<BookAuthorMapping> bookAuthorMappingList) {
    this.bookAuthorMappingList = bookAuthorMappingList;
  }
  
  public void addBookAuthorMapping(BookAuthorMapping mapping) {
    if (this.bookAuthorMappingList == null) {
      this.bookAuthorMappingList = new ArrayList<>();
    }
    mapping.setBookId(this);
    this.bookAuthorMappingList.add(mapping);
  }

  @XmlTransient
  public List<BookGenreMapping> getBookGenreMappingList() {
    return bookGenreMappingList;
  }

  public void setBookGenreMappingList(List<BookGenreMapping> bookGenreMappingList) {
    this.bookGenreMappingList = bookGenreMappingList;
  }
  
  public void addBookGenreMapping(BookGenreMapping mapping) {
    if (this.bookGenreMappingList == null) {
      this.bookGenreMappingList = new ArrayList<>();
    }
    mapping.setBookId(this);
    this.bookGenreMappingList.add(mapping);
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
