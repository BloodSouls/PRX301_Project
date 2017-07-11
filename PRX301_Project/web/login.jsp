
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="isLoginActive" value="${requestScope.LOGIN_ACTIVATION}" />
<c:if test="${empty isLoginActive}">
  <c:set var="isLoginActiv" value="true" />
</c:if>

<!DOCTYPE html>
<html>
  <head>
    <title>Cổng dịch truyện Paper</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="content/css/login.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
      document.addEventListener('DOMContentLoaded', function() {
        loginBox.style.display = "${isLoginActive}" ? "block" : "none";
        registerBox.style.display = "${isLoginActive}" ? "none" : "block";
      });

      function changeLoginToRegister() {
        var loginBox = document.getElementById("loginBox");
        var registerBox = document.getElementById("registerBox");

        loginBox.style.display = "none";
        registerBox.style.display = "block";
        clearAllText();
      }

      function changeRegisterToLogin() {
        var loginBox = document.getElementById("loginBox");
        var registerBox = document.getElementById("registerBox");

        loginBox.style.display = "block";
        registerBox.style.display = "none";
        clearAllText();
      }

      function clearAllText() {
        var txtLoginUsername = document.getElementById("txtLoginUsername");
        var txtLoginPassword = document.getElementById("txtLoginPassword");
        var txtRegisterUsername = document.getElementById("txtRegisterUsername");
        var txtRegisterEmail = document.getElementById("txtRegisterEmail");
        var txtRegisterPassword = document.getElementById("txtRegisterPassword");
        var txtRegisterConfirmPassword = document.getElementById("txtRegisterConfirmPassword");

        txtLoginUsername.value = "";
        txtLoginPassword.value = "";
        txtRegisterUsername.value = "";
        txtRegisterEmail.value = "";
        txtRegisterPassword.value = "";
        txtRegisterConfirmPassword.value = "";
      }
    </script>
  </head>
  <body class="page-background">
    <div class="login-box">
      <div class="login-box-header">
        Cổng Truyện Dịch
      </div>
      <div id="loginBox" class="login-box-body">
        <div class="login-box-msg">
          Đăng nhập để sử dụng đầy đủ tính năng trên Cổng Truyện Dịch.
        </div>
        <form action="">
          <div class="my-input-wrapper">
            <div class="icon"><img src="content/img/icon/user_icon.png" /></div>
            <input type="text" id="txtLoginUsername" name="txtUsername" placeholder="Tên tài khoản" />
          </div>
          <div class="my-input-wrapper">
            <div class="icon"><img src="content/img/icon/lock_icon.png" /></div>
            <input type="password" id="txtLoginPassword" name="txtPassword" placeholder="Mật khẩu" />
          </div>
          <div class="text-right">
            <input class="btn" type="submit" value="Đăng Nhập" />
            <input type="hidden" name="btnAction" value="login" />
          </div>
        </form>
        <a class="change-box-content" onclick="changeLoginToRegister()">Đăng ký tài khoản</a>
      </div>

      <div id="registerBox" class="register-box-body">
        <div class="login-box-msg">
          Đăng ký tài khoản mới
        </div>
        <form action="">
          <div class="my-input-wrapper">
            <div class="icon"><img src="content/img/icon/user_icon.png" /></div>
            <input type="text" id="txtRegisterUsername" name="txtUsername" 
                   placeholder="Tên tài khoản" />
          </div>
          <div class="my-input-wrapper">
            <div class="icon"><img src="content/img/icon/mail_icon.png" /></div>
            <input type="text" id="txtRegisterEmail" name="txtEmail" 
                   placeholder="Email xác nhận" />
          </div>
          <div class="my-input-wrapper">
            <div class="icon"><img src="content/img/icon/lock_icon.png" /></div>
            <input type="password" id="txtRegisterPassword" name="txtPassword" 
                   placeholder="Mật khẩu" />
          </div>
          <div class="my-input-wrapper">
            <div class="icon"><img src="content/img/icon/check_icon.png" /></div>
            <input type="password" id="txtRegisterConfirmPassword" name="txtConfirmPassword" 
                   placeholder="Nhập lại Mật khẩu" />
          </div>
          <div class="text-right">
            <input class="btn" type="submit" value="Đăng ký" />
          </div>
        </form>
        <a class="change-box-content" onclick="changeRegisterToLogin()">Đã có tài khoản? Đăng nhập tại đây!</a>
      </div>

    </div>

  </body>
</html>


