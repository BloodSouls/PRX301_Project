<%-- 
    Document   : search
    Created on : Jun 21, 2017, 1:07:21 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<c:set var="bookXML" value="${requestScope.BOOK_LIST}" />
<c:set var="titleMessage" value="${requestScope.TITLE}" />
<c:set var="genreList" value="${requestScope.GENRE_LIST}" />
<c:import var="bookXSL" url="WEB-INF/bookList.xsl" />

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-Type" content="text/html; charset=UTF-8">
    <title>Cổng truyện dịch Paper</title>
    <link href="content/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="content/css/search.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <c:import url="header.html" charEncoding="UTF-8" />

    <div class="search-title">
      <span>Danh sách / Tìm kiếm</span>
    </div>

    <div class="frame-container">
      <div class="frame-list-update">
        <div class="head">
          <div class="title">
            <c:if test="${empty titleMessage}">
              KHÔNG TÌM THẤY DỮ LIỆU
            </c:if>
            ${titleMessage}
          </div>
          <div class="btnFilter"><a href="#filter">Nâng cao</a></div>
        </div>
        <!--<div class="description-head">Các bộ manga mới cập nhật tập mới</div>-->
        <div class="update-list">
          <c:if test="${not empty titleMessage}">
            <x:transform xml="${bookXML}" xslt="${bookXSL}" />
          </c:if>
        </div>
      </div>
    </div>
    <a href="#x" id="filter" class="overlay"></a>
    <div class="popup">
      <div class="title-filter">
        <span>Tìm kiếm nâng cao</span>
        <div>
          <a class="close" href="#filter">×</a>
        </div>
      </div>
      <form id="fillter-form" action="DispatchServlet">
        <input type="hidden" name="btnAction" value="search" />
        <input type="hidden" name="type" value="filter" />
        <input type="hidden" name="genreValue" id="genreValue" />
        <div class="search-fill">
          <span>Từ khóa</span>
          <div>
            <input type="text" placeholder="Tên truyện" name="value" />
          </div>
        </div>
        <div class="search-filter">
          <span class="title">Thể loại</span>
          <div class="genre-list">
            <c:if test="${not empty genreList}">
              <c:forEach var="genre" items="${genreList}">
                <div class="genre">
                  <input type="checkbox" name="genreItem" value="${genre.id}" />
                  <div class="name">${genre.name}</div>
                </div>
              </c:forEach>
            </c:if>
          </div>
        </div>
        <div class="footer-filter">
          <button>RESET BỘ LỌC</button>
          <button id="btnFillter" type="submit">OK</button>
        </div>
      </form>
    </div>
    <c:import url="footer.html" charEncoding="UTF-8" />
    <script type="text/javascript">
      document.addEventListener('DOMContentLoaded', function() {
        
      });
      
      document.getElementById("btnFillter").addEventListener("click", function(event) {
        event.preventDefault();
        
        var genreList = document.getElementsByName("genreItem");
        var arrId = [];
        
        for (var i = 0; i < genreList.length; ++i) {
          if (genreList[i].checked === true) {
            arrId.push(genreList[i].value);
          }
        }
        
        var genreValue = document.getElementById("genreValue");
        genreValue.value = arrId;
        
        document.getElementById("fillter-form").submit();
      });
      
    </script>
  </body>
</html>
