<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <!-- Font Icon -->
    <link rel="stylesheet"
          href="/ASS_Java4/views/auth/fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="/ASS_Java4/views/auth/css/style.css">
</head>
<body>

<div class="main">

    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure>
                        <img src="/ASS_Java4/views/auth/images/anh.png" alt="sing up image">
                    </figure>
                    <a href="/ASS_Java4/register" class="signup-image-link">Create an
                        account</a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Sign up</h2>
                    <c:if test="${ sessionScope.error != null }">
                        <div class="alert alert-danger" style="font-family: sans-serif;color: red;">${ sessionScope.error }</div>
                    </c:if>
                    <form method="post" action="/ASS_Java4/login" class="register-form"
                          id="login-form">
                        <div class="form-group">
                            <label for="ma"><i
                                    class="zmdi zmdi-account material-icons-name"></i></label>
                            <input
                                type="text" name="ma" id="ma" value="${ma}"
                                placeholder="Your Name"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label>
                            <input
                                type="password" name="password" id="password" value="${pass}"
                                placeholder="Password"/>
                        </div>
                        <div class="form-group">
                                <a href="/ASS_Java4/fogotpassword?ma=<%=(request.getAttribute("ma"))%>">Forgot Password</a>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin"
                                   class="form-submit" value="Log in"/>
                        </div>
                    </form>
<%--                    <div class="form-group">--%>
<%--                        <a href="/ASS_Java4/fogotpassword?ma=<%=(request.getAttribute("ma"))%>">Forgot Password</a>--%>
<%--                    </div>--%>
                    <p >
                        <a href="/ASS_Java4/AdminSigninServlet" class="text-center">
                            Sign in as Admin!</a>
                    </p>
                    <div class="social-login">
                        <span class="social-label">Or login with</span>
                        <ul class="socials">
                            <li><a href="#"><i
                                    class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                            <li><a href="#"><i
                                    class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                            <li><a href="#"><i
                                    class="display-flex-center zmdi zmdi-google"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="/ASS_Java4/views/auth/js/main.js"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>