<%-- 
    Document   : index
    Created on : Jun 17, 2017, 1:47:48 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<c:set var="newBooks" value="${requestScope.NEW_BOOK}" />
<c:set var="updatedBooks" value="${requestScope.UPDATED_BOOK}" />
<c:set var="mostViewedBooks" value="${requestScope.MOST_VIEWED_BOOK}" />
<c:import var="xsldoc" url="WEB-INF/bookList.xsl" />

<c:set var="bannerBook" value="${requestScope.BANNER_BOOK}" />

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="content-Type" content="text/html; charset=UTF-8">
    <title>Cổng truyện dịch Paper</title>
    <link href="content/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="content/css/index.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <c:import url="header.html" charEncoding="UTF-8" />

    <div class="banner">
      <img src="image${bannerBook.bannerUrl}" alt=""/>
    </div>

    <div class="frame-link">
      <img src="content/img/banner-link.png" alt=""/>
      <div class="logBtn">
        <a href="DispatchServlet?btnAction=loginPage">ĐĂNG NHẬP / ĐĂNG KÝ</a>
      </div>
      <div class="searchBtn">
        <a href="DispatchServlet?btnAction=search&type=new">DANH SÁCH / TÌM KIẾM</a>
      </div>
    </div>
    <div class="frame-container">
      <div class="frame-list-update">
        <div class="head">
          <div class="title">MỚI CẬP NHẬT</div>
          <div class="btnShowAll"><a href="DispatchServlet?btnAction=search&amp;type=update">Xem tất cả</a></div>
        </div>
        <div class="description-head">Các bộ manga mới cập nhật tập mới</div>
        <div class="update-list">
          <x:transform xml="${updatedBooks}" xslt="${xsldoc}" />
        </div>
      </div>

      <div class="seperate"></div>
      <div class="frame-list-new">
        <div class="head">
          <div class="title">MỚI RA MẮT</div>
          <div class="btnShowAll"><a href="DispatchServlet?btnAction=search&amp;type=new">Xem tất cả</a></div>
        </div>
        <div class="description-head">Các bộ manga mới ra</div>
        <div class="update-list">
          <x:transform xml="${newBooks}" xslt="${xsldoc}" />
        </div>
      </div>
        
      <div class="seperate"></div>
      <div class="frame-list-popular">
        <div class="head">
          <div class="title">PHỔ BIẾN</div>
          <div class="btnShowAll"><a href="DispatchServlet?btnAction=search&amp;type=mostview">Xem tất cả</a></div>
        </div>
        <div class="description-head">Các bộ manga được nhiều người xem</div>
        <div class="update-list">
          <x:transform xml="${mostViewedBooks}" xslt="${xsldoc}" />
        </div>
      </div>
    </div>

    <c:import url="footer.html" charEncoding="UTF-8" />
  </body>
</html>
