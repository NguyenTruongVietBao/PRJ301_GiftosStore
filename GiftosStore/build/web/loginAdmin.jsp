<%-- 
    Document   : loginAdmin
    Created on : 14-10-2023, 21:46:24
    Author     : VietBao
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <style>
            body,html {
                background-image: url('https://i.imgur.com/xhiRfL6.jpg');
                height: 100%;
            }

            #profile-img {
                height:180px;
            }
            .h-80 {
                height: 80% !important;
            }
        </style>
    </head>
    <body>
        <div class="container h-80">
            <div class="row align-items-center h-100">
                <div class="col-3 mx-auto">
                    <div class="text-center">
                        <img id="profile-img" class="rounded-circle profile-img-card" src="https://i.imgur.com/6b6psnA.png" />
                        <p id="profile-name" class="profile-name-card"></p>
                        <form action="MainController" method="POST" class="form-signin">
                            <input type="text" name="id" placeholder="id admin" class="form-control form-group">
                            <input type="password" name="password" id="inputPassword" class="form-control form-group" placeholder="password" required autofocus>
                            <input class="btn btn-lg btn-primary btn-block btn-signin" type="submit" name="action" value="LoginAd"/>
                        </form>
                        <%
                            String error = (String) request.getAttribute("ERROR");
                            if (error == null) {
                                error = "";
                            }
                        %>        
                        <h6 style="color: red"><%= error%></h6>

                        <button style="background: white; width: 100px; height: 30px; border: none">
                            <a href="login.jsp" style="color: palevioletred">User mode</a>
                        </button>
                    </div>
                </div>        
            </div>
        </div>
    </body>
</html>
