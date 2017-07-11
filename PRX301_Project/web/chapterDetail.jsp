<%-- 
    Document   : chapterDetail
    Created on : Jun 27, 2017, 9:44:01 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="chapter" value="${ requestScope.CHAPTER }" />
<%--<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>--%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Cổng truyện dịch Paper</title>
    <link href="content/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="content/css/chapterDetail.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <c:import url="header.html" charEncoding="UTF-8" />
    <div class="chapterDetail-title">
      <div class="format-title">${chapter.bookId.name}</div>
      <div class="format-title">
        Chương <fmt:formatNumber type="number" value="${chapter.number}" />
        <c:if test="${not empty chapter.name}">
          : ${chapter.name}
        </c:if>
      </div>
        <div class="format-title">
          Số trang: ${f:length(chapter.chapterPageList)} 
          | Ngày đăng: <fmt:formatDate pattern="dd/MM/yyyy" value="${chapter.releasedDate}" />
        </div>
      <div class="format-btn-link">
        <div class="btn-link"><a>Tập trước</a></div>
        <div class="btn-link"><a>Tập kế</a></div>
      </div>
    </div>

    <div class="chapterDetail-content">
      
      <c:forEach var="page" items="${chapter.chapterPageList}">
        <div class="manga-page" data-number="${page.pageNumber}">
          <img src="image${page.imageUrl}" />
        </div>
      </c:forEach>
    </div>

    <!--    <div class="chapterDetail-content">
    <c:set var="xmldoc" value="${XMLString}" />
    <c:import var="xsldoc" url="WEB-INF/chapterDetail.xsl" charEncoding="UTF-8" />
    <x:transform xml="${xmldoc}" xslt="${xsldoc}" />
  </div>-->
    <c:import url="footer.html" charEncoding="UTF-8" />
  </body>
</html>
