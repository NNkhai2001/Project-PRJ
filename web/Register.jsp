<%-- 
    Document   : Register
    Created on : Mar 6, 2022, 10:47:50 AM
    Author     : ngock
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://www.phptutorial.net/app/css/style.css">
        <title>Register</title>
    </head>
    <body style="background-image: url(https://png.pngtree.com/thumb_back/fw800/background/20190828/pngtree-top-view-background-of-desktop-with-electronic-devices-and-laptop-image_307721.jpg)">
        <main>
            <form action="RegisterController" method="post" >
                <h1>Sign Up</h1>
                <%String mess = (String)request.getAttribute("mess");%>
               
                <%if(mess != null) { %>
                  <p class="text-danger"><%=mess%></p>
                <%}%>
                <div>
                    <label for="username">Username:</label>
                    <input type="text" name="username" id="username">
                </div>
                <div>
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password">
                </div>
                <div>
                    <label for="password2">Password Again:</label>
                    <input type="password" name="password2" id="password2">
                </div>
               
                <button type="submit" name="submit" value="submit">Register</button>
                <footer>Already a member? <a href="LoginController?do=login">Login here</a></footer>
            </form>
        </main>
    </body>
</html>