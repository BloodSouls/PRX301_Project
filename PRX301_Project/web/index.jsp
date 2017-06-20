<%-- 
    Document   : index
    Created on : Jun 17, 2017, 1:47:48 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cổng truyện dịch Paper</title>
        <link href="content/css/layout.css" rel="stylesheet" type="text/css"/>
        <link href="content/css/index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.html"/>

        <div class="banner">
            <img src="content/img/default-banner.jpg" alt=""/>
        </div>
        
        <div class="frame-link">
            <img src="content/img/banner-link.png" alt=""/>
        </div>
        
        <div class="frame-list-update">
            <div class="update-list">
                <div class="update-item"></div>
            </div>
        </div>
        <div class="frame-list-new">
            
            
        </div>
        <div class="frame-list-popular">
            
            
        </div>
        <jsp:include page="footer.html"/>
    </body>
</html>
