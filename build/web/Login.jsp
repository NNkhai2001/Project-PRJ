<%-- 
    Document   : Login
    Created on : Mar 6, 2022, 10:47:35 AM
    Author     : ngock
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<body style="background-image: url(https://media.istockphoto.com/photos/minimal-idea-concept-laptop-background-on-work-desk-picture-id1136466017?k=20&m=1136466017&s=170667a&w=0&h=AXzU_EUwsSFOYamEVXknMXVIpjaN71ZRgJwC39ap5_0=)">
    <div id="login">
        <h3 class="text-center text-white pt-5">Login form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="LoginController" method="post">
                            <h3 class="text-center text-info">Login</h3>
                            <p class="text-danger">${mess}</p>
                            <div class="form-group">
                                <label for="username" class="text-info">Username:</label><br>
                                <input type="text" name="username" id="username" class="form-control">
                            </div>
                            <label for="inputPassword5" class="text-info">Password:</label>
                            <input type="password" name="password" id="inputPassword5" class="form-control" aria-describedby="passwordHelpBlock">
                            <div class="form-group">
                                <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                            </div>
                            <div id="register-link" class="text-left">
                                <a href="HomeController" class="text-info">Home</a>
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="RegisterController" class="text-info">Register here</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
