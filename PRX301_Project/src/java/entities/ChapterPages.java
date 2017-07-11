package entities;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "chapterPages")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chapterPageList", propOrder = "chapterPage")
public class ChapterPages {
  
  @XmlElement(required = true)
  private List<ChapterPage> chapterPage;

  public List<ChapterPage> getChapterPageList() {
    return chapterPage;
  }

  public void setChapterPageList(List<ChapterPage> chapterPageList) {
    this.chapterPage = chapterPageList;
  }
  
}
