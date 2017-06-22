<%-- 
    Document   : bookDetail
    Created on : Jun 21, 2017, 3:50:42 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cổng truyện dịch Paper</title>
    <link href="Content/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="Content/css/bookDetail.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <c:import url="header.html" charEncoding="UTF-8" />

    <div class="banner" style="background-image: 
         linear-gradient(to right, rgba(35, 35, 35, 0.5), rgba(35, 35, 35, 0.5)), 
         url(Content/img/background/dango.jpg);">
      <div class="body">
        <div class="manga-img" style="background-image: url(Content/img/banner-link.png)">
        </div>
        <div class="banner-info">
          <p class="title">
            Kuro
          </p>
          <p class="description">
            Câu chuyện về một bé mèo đen tên là Kuro và một cô bé cô đơn Coco sống trong một tòa biệt thự rộng lớn....
          </p>
        </div>
      </div>
      <div class="footer">
        <ul>
          <li>
            5 <span>tập truyện</span>
          </li>
          <li>
            123 <span>chương</span>
          </li>
          <li>
            2,800 <span>lượt xem</span>
          </li>
        </ul>
        <a class="btn-bookmark">
          <img class="medium-icon" src="Content/img/icon/white_heart_icon.png" />
          Theo dõi
        </a>
      </div>
    </div>

    <div class="manga-detail">
      <div class="title">
        THÔNG TIN CHI TIẾT
      </div>
      <div class="content">
        <div class="info">
          <ul>
            <li class="info-child"><b>Tên khác: </b>- 異能バトルは日常系のなかで - Inou Battle Within Everyday Life - </li>
            <li class="info-child">
              <b>Thể loại: </b>- <a href="http://truyen.vnsharing.site/index/TimKiem/1/key::Comedy">Comedy</a> - <a href="http://truyen.vnsharing.site/index/TimKiem/1/key::Romance ">Romance </a> - <a href="http://truyen.vnsharing.site/index/TimKiem/1/key::School Life ">School Life </a> - <a href="http://truyen.vnsharing.site/index/TimKiem/1/key::Shounen">Shounen</a> - <a href="http://truyen.vnsharing.site/index/TimKiem/1/key::Supernatural ">Supernatural </a> - </li>
            <li class="info-child"><b>Tác giả: </b>NOZOMI Kota, KUROSE Kousuke</li>
            <li class="info-child"><b>Nhà xuất bản: </b>Comp Ace (Kadokawa Shoten)</li>
            <li class="info-child"><b>Trạng thái: </b>Đang phát hành</li>
            <li class="info-child"><b>Ngày phát hành: </b>2013</li>
            <li class="info-child"><b>Giới hạn độ tuổi: </b>[13+] Truyện dành cho lứa tuổi thiếu niên</li>
            <li class="info-child"><b>Giới thiệu truyện: </b>Đối với những ai xem qua Chuunibyou demo koi ga shitai! rồi thì chắc hẳn sẽ không lạ gì hội chứng tuổi teen hoang tưởng của Main chính trong truyện này nữa 8-}, có khác chăng là Main ta có dị năng thật mà thôi, mỗi tội là cái dị năng đó nó... </li>
          </ul>
        </div>
      </div>
      <div class="title">
        CHƯƠNG TRUYỆN
      </div>
      <div class="content">
        <div class="volume-wrapper">
          <div class="volume">
            <span class="volume-number">Tập 2</span>
            <span class="num-of-chapters">Chương 6 - 10</span>
          </div>
          <ul class="chapters">
            <li>
              <div class="link">
                <a href="#">Chương 10: You wouldn't just leave me there</a>
              </div>
              <div class="release-date">
                Jul 22, 2016
              </div>
            </li>
            <li>
              <div class="link">
                <a href="#">Chương 9: Might not be able to go back</a>
              </div>
              <div class="release-date">
                Jul 21, 2016
              </div>
            </li>
            <li>
              <div class="link">
                <a href="#">Chương 8: A game she used to play a lot</a>
              </div>
              <div class="release-date">
                Jul 20, 2016
              </div>
            </li>
            <li>
              <div class="link">
                <a href="#">Chương 7: NO.2? At least!</a>
              </div>
              <div class="release-date">
                Jul 19, 2016
              </div>
            </li>
            <li>
              <div class="link">
                <a href="#">Chương 6: Getting to the Point, I've Obtained a Superpower</a>
              </div>
              <div class="release-date">
                Jul 18, 2016
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="content">
        <div class="volume-wrapper">
          <div class="volume">
            <span class="volume-number">Tập 1</span>
            <span class="num-of-chapters">Chương 1 - 5</span>
          </div>
          <ul class="chapters">
            <li>
              <div class="link">
                <a href="#">Chương 5: You wouldn't just leave me there</a>
              </div>
              <div class="release-date">
                Jul 17, 2016
              </div>
            </li>
            <li>
              <div class="link">
                <a href="#">Chương 4: Might not be able to go back</a>
              </div>
              <div class="release-date">
                Jul 16, 2016
              </div>
            </li>
            <li>
              <div class="link">
                <a href="#">Chương 3: A game she used to play a lot</a>
              </div>
              <div class="release-date">
                Jul 15, 2016
              </div>
            </li>
            <li>
              <div class="link">
                <a href="#">Chương 2: NO.2? At least!</a>
              </div>
              <div class="release-date">
                Jul 14, 2016
              </div>
            </li>
            <li>
              <div class="link">
                <a href="#">Chương 1: Getting to the Point, I've Obtained a Superpower</a>
              </div>
              <div class="release-date">
                Jul 13, 2016
              </div>
            </li>
          </ul>
        </div>
      </div>
      
      
      
    </div>

    <c:import url="footer.html" charEncoding="UTF-8" />
  </body>
</html>
