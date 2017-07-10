<%-- 
    Document   : chapterDetail
    Created on : Jun 27, 2017, 9:44:01 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cổng truyện dịch Paper</title>
    <link href="content/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="content/css/chapterDetail.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
    <c:import url="header.html" charEncoding="UTF-8" />
    <div class="chapterDetail-title">
      <div class="format-title">Tên phim</div>
      <div class="format-title">Chương:  Tên Chương: </div>
      <div class="format-title">Số trang: | Ngày đăng: | Lượt xem: </div>
      <div class="format-title"><a>Link dowload chap ? của truyện ?</a></div>
      <div class="format-btn-link">
        <div class="btn-link"><a>Tập trước</a></div>
        <div class="btn-link"><a>Tập kế</a></div>
      </div>
    </div>


    <div class="chapterDetail-content">
      <div class="manga-page">
        <img src="https://3.bp.blogspot.com/-JEogoXOoeQE/WVJarg2fw3I/AAAAAAABj_I/Abzfko8O1FoKqLhe3IRA2VgtWMY7tKviwCHMYCw/s1400/001.jpg" />
      </div>
      <div class="manga-page">
        <img src="https://3.bp.blogspot.com/-JEogoXOoeQE/WVJarg2fw3I/AAAAAAABj_I/Abzfko8O1FoKqLhe3IRA2VgtWMY7tKviwCHMYCw/s1400/001.jpg" />
      </div>
      <div class="manga-page">
        <img src="https://3.bp.blogspot.com/-JEogoXOoeQE/WVJarg2fw3I/AAAAAAABj_I/Abzfko8O1FoKqLhe3IRA2VgtWMY7tKviwCHMYCw/s1400/001.jpg" />
      </div>
      <div class="manga-page">
        <img src="https://3.bp.blogspot.com/-JEogoXOoeQE/WVJarg2fw3I/AAAAAAABj_I/Abzfko8O1FoKqLhe3IRA2VgtWMY7tKviwCHMYCw/s1400/001.jpg" />
      </div>
    </div>
    <!--        <div class="chapterDetail-title-footer">
                <div class="format-title">Tên phim</div>
                <div class="format-title">Chương:  Tên Chương: </div>
                <div class="format-title">Số trang: | Ngày đăng: | Lượt xem: </div>
                <div class="format-title"><a>Link dowload chap ? của truyện ?</a></div>
                <div class="format-btn-link">
                    <div class="btn-link"><a>Tập trước</a></div>
                    <div class="btn-link"><a>Tập kế</a></div>
                </div>
            </div>-->
    <c:import url="footer.html" charEncoding="UTF-8" />
  </body>
</html>
