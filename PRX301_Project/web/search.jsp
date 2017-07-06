<%-- 
    Document   : search
    Created on : Jun 21, 2017, 1:07:21 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

        <div class="">

        </div>

        <div class="frame-container">
            <div class="frame-list-update">
                <div class="head">
                    <div class="title">MỚI CẬP NHẬT</div>
                    <div class="btnFilter"><a href="#filter">Nâng cao</a></div>
                </div>
                <div class="description-head">Các bộ manga mới cập nhật tập mới</div>
                <div class="update-list">
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <div>
                            <span>Tên truyện</span>
                        </div>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                    <div class="update-item">
                        <img src="content/img/default-banner.jpg"/>
                        <span>Tên truyện</span>
                    </div>
                </div>
            </div>
            <!--            <div class="frame-list-new">
                            <div class="head">
                                <div class="title">MỚI RA MẮT</div>
                            </div>
                            <div class="description-head">Các bộ manga mới ra</div>
                            <div class="update-list">
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                            </div>
                        </div>
                        <div class="frame-list-popular">
                            <div class="head">
                                <div class="title">PHỔ BIẾN</div>
                            </div>
                            <div class="description-head">Các bộ manga được nhiều người xem</div>
                            <div class="update-list">
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                                <div class="update-item">
                                    <img src="content/img/default-banner.jpg"/>
                                    <span>Tên truyện</span>
                                </div>
                            </div>
                        </div>-->



        </div>
        <a href="#x" id="filter" class="overlay"></a>
        <div class="popup">
            <div class="title-filter">
                <span>Tìm kiếm nâng cao</span>
                <div>
                    <a class="close" href="#filter">×</a>
                </div>
            </div>
            <div class="search-fill">
                <span>Từ khóa</span>
                <div>
                    <input type="text" placeholder="Tìm kiếm theo [Tựa đề] hoặc [Tác giả]"/>
                </div>
            </div>
            <div class="search-filter">
                <div>
                    <span>Thể loại</span>
                </div>
                <label>
                    <input type="checkbox" value="" />
                    <div class="custom-checbox"></div>
                    Action
                </label>
                <label>
                    <input type="checkbox" value="" />
                    <div class="custom-checbox"></div>
                    Adult
                </label>
                <label>
                    <input type="checkbox" value="" />
                    <div class="custom-checbox"></div>
                    Adventure
                </label>
                <label>
                    <input type="checkbox" value="" />
                    <div class="custom-checbox"></div>
                    Comedy
                </label>
                <label>
                    <input type="checkbox" value="" />
                    <div class="custom-checbox"></div>
                    comic
                </label>
                <label>
                    <input type="checkbox" value="" />
                    <div class="custom-checbox"></div>
                    Drama
                </label>
                <label>
                    <input type="checkbox" value="" />
                    <div class="custom-checbox"></div>
                    Harem
                </label>
                <label>
                    <input type="checkbox" value="" />
                    <div class="custom-checbox"></div>
                    Martial Arts
                </label>
            </div>
            <div class="footer-filter">
                <button>RESET BỘ LỌC</button>
                <button type="submit">OK</button>
            </div>
        </div>
        <c:import url="footer.html" charEncoding="UTF-8" />
    </body>
</html>
