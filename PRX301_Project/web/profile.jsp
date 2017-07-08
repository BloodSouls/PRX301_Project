<%-- 
    Document   : profile
    Created on : Jun 27, 2017, 9:44:21 AM
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
        <link href="content/css/profile.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <c:import url="header.html" charEncoding="UTF-8" />
        <div class="profile-title">
            <span>Thông tin cá nhân</span>
        </div>

        <div class="tab">
            <button class="tablinks" id="defaultTab" onclick="changeTab(event, 'FollowManager')">Quản lý theo dõi</button>
            <!--            <button class="tablinks" onclick="changeTab(event, 'Information')">Thông tin cá nhân</button>-->
            <button class="tablinks" onclick="changeTab(event, 'ChangePassword')">Đổi mật khẩu</button>
        </div>

        <div id="FollowManager" class="tabcontent">
            <div>Các bộ truyện bạn đang theo dõi</div>
            <div class="container-show-list">
                <div class="header-show-list">
                    <div class="format-show-list">Tên bộ truyện</div>
                    <div class="format-show-list">Tập mới nhất</div>
                    <div class="format-show-list">Bỏ theo dõi</div>
                    <div class="clear"></div>
                </div>
                <div class="content-show-list">
                    <div class="format-show-list content-list"><a href="">Nanatsu no Taizai</a></div>
                    <div class="format-show-list content-list">extra vol 2</div>
                    <div class="format-show-list content-list">X</div>
                    <div class="clear"></div>
                </div>
                <div class="content-show-list">
                    <div class="format-show-list content-list"><a href="">Nanatsu no Taizai 2</a></div>
                    <div class="format-show-list content-list">extra vol 2</div>
                    <div class="format-show-list content-list">X</div>
                    <div class="clear"></div>
                </div>
                <div class="content-show-list">
                    <div class="format-show-list content-list"><a href="">Nanatsu no Taizai 3</a></div>
                    <div class="format-show-list content-list">extra vol 2</div>
                    <div class="format-show-list content-list">X</div>
                    <div class="clear"></div>
                </div>
                <div class="content-show-list">
                    <div class="format-show-list content-list"><a href="">Nanatsu no Taizai 4</a></div>
                    <div class="format-show-list content-list">extra vol 2</div>
                    <div class="format-show-list content-list">X</div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>

        <!--        <div id="Information" class="tabcontent">
                    <div>Thông tin</div>
                </div>-->

        <div id="ChangePassword" class="tabcontent">
            <div>Thay đổi mật khẩu</div>
            <div class="container-change-password">
                <div>
                    Mật khẩu cũ<input/>
                </div>
                <div>
                    Mật khẩu mới<input/>
                </div>
                <div>
                    Xác nhận mật khẩu mới<input/>
                </div>
                <button type="submit">Xác nhận</button>
            </div>
        </div>


        <c:import url="footer.html" charEncoding="UTF-8" />
    </body>
    <script src="content/js/changeTab.js"></script>
    <script>
                window.onload = function () {
                    changeTab(event, 'FollowManager');
                }
    </script>
</html>
