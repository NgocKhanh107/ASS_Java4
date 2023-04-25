<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login with Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
</head>

<body>
<div class="container-fluid " style="margin-top:30px">
    <div class="" style="margin-top:50px">
        <div class="rounded d-flex justify-content-center">
            <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
                <div class="text-center">
                    <h3 class="text-primary">Sign In</h3>
                </div>
                <c:if test="${ sessionScope.error != null }">
                    <div class="alert alert-danger" style="font-family: sans-serif;color: red;">${ sessionScope.error }</div>
                </c:if>
                <form action="/ASS_Java4/AdminSigninServlet" method="post">

                    <div class="p-4">
                        <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-person-plus-fill text-white"></i></span>
                            <input type="text" class="form-control" name="ma" placeholder="Username">
                        </div>
                        <div class="input-group mb-3">
                                    <span class="input-group-text bg-primary"><i
                                            class="bi bi-key-fill text-white"></i></span>
                            <input type="password" class="form-control" name="password" placeholder="password">
                        </div>
                        <button class="btn btn-primary text-center mt-2 col-8 offset-2" type="submit">
                            Login
                        </button>
                    </div>
<%--                    <a href="/ASS_Java4/fogotpassword" class="text-center text-primary mt-5 col-8 offset-2">Forgot Password</a>--%>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

</html>