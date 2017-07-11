<%-- 
    Document   : bookDetail
    Created on : Jun 21, 2017, 3:50:42 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="book" value="${ requestScope.BOOK }" />
<c:set var="bookStatusList" value="${ requestScope.BOOKSTATUS }"  />

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-Type" content="text/html; charset=UTF-8">
    <title>Cổng truyện dịch Paper</title>
    <link href="content/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="content/css/bookDetail.css" rel="stylesheet" type="text/css"/>
    <script src="content/js/rating.js" type="text/javascript"></script>
    <style>
      <c:set var="bookImg" value="url('image${book.imageUrl}')" />
      <c:set var="bannerBg" value="url('image${book.bannerUrl}')" />

      .manga-img {
        background-image: ${bookImg};
      }

      .banner {
        background-image: 
          linear-gradient(to right, rgba(35, 35, 35, 0.5), rgba(35, 35, 35, 0.5)), 
          ${bannerBg};
      }
    </style>
  </head>
  <body>
    <c:import url="header.html" charEncoding="UTF-8" />

    <div class="banner">
      <div class="body">
        <div class="manga-img">
        </div>
        <div class="banner-info">
          <p class="title">
            <c:out value="${book.name}" />
          </p>
          <p class="description">
            <c:if test="${not empty book.description}">
              <c:choose>
                <c:when test="${f:length(book.description) > 170}">
                  ${f:substring(book.description, 0, 170)}...
                </c:when>
                <c:otherwise>
                  ${book.description}
                </c:otherwise>
              </c:choose>
            </c:if>
          </p>
        </div>
      </div>
      <div class="footer">
        <ul>
          <li>
            <c:choose>
              <c:when test="${not empty book.chapterList}">
                <c:out value="${f:length(book.chapterList)}"/> <span>chương</span>
              </c:when>
              <c:otherwise>
                0 <span>chương</span>
              </c:otherwise>
            </c:choose>
          </li>
          <li>
            <c:out value="${book.totalView}" /> <span>lượt xem</span>
          </li>
        </ul>
        <a class="btn-bookmark">
          <img class="medium-icon" src="content/img/icon/white_heart_icon.png" />
          Theo dõi
        </a>
      </div>
    </div>

    <div class="manga-detail clear">
      <div class="left-content">
        <div class="title">
          THÔNG TIN CHI TIẾT
        </div>
        <div class="content">
          <div class="info">
            <ul>
              <c:if test="${not empty book.anotherName}">
                <li class="info-child"><b>Tên khác: </b>- ${book.anotherName} -</li>
                </c:if>
                <c:if test="${not empty book.bookGenreMappingList}">
                <li class="info-child"><b>Thể loại: </b>
                  <c:forEach var="mapping" items="${book.bookGenreMappingList}" varStatus="loop">
                    - <a href="#">${mapping.genreId.name}</a>
                    <c:if test="${loop.last}"> -</c:if>
                  </c:forEach>
                </li>
              </c:if>
              <c:if test="${not empty book.bookAuthorMappingList}">
                <li class="info-child"><b>Tác giả: </b>
                  <c:forEach var="mapping" items="${book.bookAuthorMappingList}" varStatus="loop">
                    ${mapping.authorId.name}
                    <c:if test="${!loop.last}">, </c:if>
                  </c:forEach>
                </li>
              </c:if>
              <c:if test="${not empty book.status}">
                <li class="info-child"><b>Trạng thái: </b>
                  <c:forEach var="status" items="${bookStatusList}">
                    <c:if test="${book.status == status.value}">
                      ${status.name}
                    </c:if>
                  </c:forEach>
                </li>
              </c:if>
              <c:if test="${not empty book.releasedDate}">
                <li class="info-child"><b>Ngày phát hành: </b>
                  <fmt:formatDate pattern="dd/MM/yyyy" value="${book.releasedDate}" />
                </li>
              </c:if>
              <c:if test="${not empty book.description}">
                <li class="info-child"><b>Giới thiệu truyện: </b>
                  ${book.description}
                </li>
              </c:if>
            </ul>
          </div>
        </div>
        <div class="title">
          CHƯƠNG TRUYỆN
        </div>
        <div class="content">
          <div class="chapter-wrapper">
            <ul class="chapters">
              <c:if test="${not empty book.chapterList}">
                <c:forEach var="chapter" items="${book.chapterList}">
                  <li>
                    <div class="link">
                      <a href="DispatchServlet?btnAction=viewChapter&chapterId=${chapter.id}">
                        Chương <fmt:formatNumber type="number" value="${chapter.number}" />
                        <c:if test="${not empty chapter.name}">
                          : <c:out value="${chapter.name}" />
                        </c:if>
                      </a>
                    </div>
                    <div class="release-date">
                      <fmt:formatDate pattern="dd-MM-yyyy" value="${chapter.releasedDate}" />
                    </div>
                  </li>
                </c:forEach>
              </c:if>
            </ul>
          </div>
        </div>
      </div>

      <div class="right-content">
        <div class="rating">
          <div class="title">
            ĐÁNH GIÁ
          </div>
          <div class="content">
            <ul>
              <li>
                <b>Điểm:</b> 4.71/5 - 6748 phiếu bầu
              </li>
              <li>
                <div class="rating-star" onmousemove="chooseRating(event)" 
                     onmouseout="reloadRating()" onclick="setRating()">
                  <img id="star1" class="star" src="content/img/icon/full-star.png"
                       title="Không hay" />
                  <img id="star2" class="star" src="content/img/icon/full-star.png"
                       title="Tạm được" />
                  <img id="star3" class="star" src="content/img/icon/full-star.png"
                       title="Trung bình" />
                  <img id="star4" class="star" src="content/img/icon/full-star.png"
                       title="Hay" />
                  <img id="star5" class="star" src="content/img/icon/half-star.png"
                       title="Rất hay" />
                  <input id="rating-value" type="hidden" value="4.5" />
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>

    </div>

    <c:import url="footer.html" charEncoding="UTF-8" />
  </body>
</html>
