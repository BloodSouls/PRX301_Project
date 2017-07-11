
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="isLoginActive" value="${requestScope.LOGIN_ACTIVATION}" />
<c:if test="${empty isLoginActive}">
  <c:set var="isLoginActive" value="true" />
</c:if>

<c:set var="loginErrorMsg" value="${LOGIN_ERROR_MESSAGE}" />
<c:set var="registerErrorMsg" value="${REGISTER_ERROR_MESSAGE}" />

<!DOCTYPE html>
<html>
  <head>
    <title>Cổng dịch truyện Paper</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="content/css/login.css" rel="stylesheet" type="text/css" />

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

        <c:if test="${not empty loginErrorMsg}">
          <div class="error-box">
            ${loginErrorMsg}
          </div>
        </c:if>
        <form action="DispatchServlet">
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
        <a class="change-box-content" id="loginChangeBox">Đăng ký tài khoản</a>
      </div>

      <div id="registerBox" class="register-box-body">
        <div class="login-box-msg">
          Đăng ký tài khoản mới
        </div>
        <c:if test="${not empty registerErrorMsg}">
          <div class="error-box">
            ${registerErrorMsg}
          </div>
        </c:if>
        <form id="registerForm" action="DispatchServlet">
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
            <input class="btn" id="btnRegister" type="submit" value="Đăng ký" />
            <input type="hidden" name="btnAction" value="register" />
          </div>
        </form>
        <a class="change-box-content" id="registerChangeBox">Đã có tài khoản? Đăng nhập tại đây!</a>
      </div>

    </div>
    <script type="text/javascript">
          document.addEventListener('DOMContentLoaded', function() {
            loginBox.style.display = "${isLoginActive}" ? "block" : "none";
            registerBox.style.display = "${isLoginActive}" ? "none" : "block";

            document.getElementById("btnRegister").addEventListener("click", function(event) {
              event.preventDefault();

              var txtUsername = document.getElementById("txtRegisterUsername");
              var txtEmail = document.getElementById("txtRegisterEmail");
              var txtPassword = document.getElementById("txtRegisterPassword");
              var txtConfirmPassword = document.getElementById("txtRegisterConfirmPassword");
              
              console.log("1 " + txtUsername.value);
              console.log("2 " + txtEmail.value);
              console.log("3 " + txtPassword.value);
              console.log("4 " + txtConfirmPassword.value);
              if (txtUsername.value == "" || txtEmail.value == "" || txtPassword.value == "" 
                      || txtConfirmPassword.value == "") {
                return;
              }
          
              if (txtPassword.value !== txtConfirmPassword.value) {
                alert("Mật khẩu nhập lại không giống nhau");
              } else {
                document.getElementById("registerForm").submit();
              }

            });
            
            document.getElementById("loginChangeBox").addEventListener("click", function(){
              var loginBox = document.getElementById("loginBox");
            var registerBox = document.getElementById("registerBox");

            loginBox.style.display = "none";
            registerBox.style.display = "block";
            clearAllText();
            });
            
            document.getElementById("registerChangeBox").addEventListener("click", function(){
              var loginBox = document.getElementById("loginBox");
            var registerBox = document.getElementById("registerBox");

            loginBox.style.display = "block";
            registerBox.style.display = "none";
            clearAllText();
            });
            
          });

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
  </body>

</html>




