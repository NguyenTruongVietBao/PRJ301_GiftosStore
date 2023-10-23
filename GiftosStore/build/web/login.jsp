<%-- 
    Document   : login
    Created on : 14-10-2023, 18:35:09
    Author     : VietBao
--%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <!--Made with love by Mutiullah Samim -->

        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!--Custom styles-->
        <link rel="stylesheet" type="text/css" href="styles.css">
        <style>
            /* Made with love by Mutiullah Samim*/

            @import url('https://fonts.googleapis.com/css?family=Numans');

            html,body{
                background-image: url('https://image.slidesdocs.com/responsive-images/background/watercolor-cute-pink-romantic-creative-business-powerpoint-background_faccd8d847__960_540.jpg');
                background-size: cover;
                background-repeat: no-repeat;
                height: 100%;
                font-family: 'Numans', sans-serif;
            }

            .container{
                height: 100%;
                align-content: center;
            }

            .card{
                height: 370px;
                margin-top: auto;
                margin-bottom: auto;
                width: 400px;
                background-color: rgba(255, 80, 121, 0.2) !important;
            }

            .social_icon span{
                font-size: 60px;
                margin-left: 10px;
                color: black;
            }

            .social_icon span:hover{
                color: white;
                cursor: pointer;
            }

            .card-header h3{
                color: white;
            }

            .social_icon{
                position: absolute;
                right: 20px;
                top: -45px;
            }

            .input-group-prepend span{
                width: 50px;
                background-color: white;
                color: black;
                border:0 !important;
            }

            input:focus{
                outline: 0 0 0 0  !important;
                box-shadow: 0 0 0 0 !important;

            }

            .remember{
                color: white;
            }

            .remember input
            {
                width: 20px;
                height: 20px;
                margin-left: 15px;
                margin-right: 5px;
            }

            .login_btn{
                color: black;
                background-color:white ;
                width: 100px;
            }

            .login_btn:hover{
                color: black;
                background-color: pink;
            }

            .links{
                color: white;
            }

            .links a{
                margin-left: 4px;
            }
        </style>
    </head>
    <body>
<!--        <h1>User Login</h1>
                <form action="MainController" method="POST">
                    User ID<input type="text" name="id"/>
                    </br>Password <input type="password" name="password"/>
                    </br><input type="submit" name="action" value="Login"/>
                    <input type="reset" value="Reset"/>            
                </form> 
        <div>Don't have an account?  </div>
        <a href="loginAdmin.jsp">Admin mode</a>
        
-->

        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Sign In</h3>
                        <div class="d-flex justify-content-end social_icon">
                            <span><i class="fab fa-facebook-square"></i></span>
                            <span><i class="fab fa-google-plus-square"></i></span>
                            <span><i class="fab fa-twitter-square"></i></span>
                        </div>
                    </div>
                    <div class="card-body">
                        <form action="MainController" method="POST">
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input  type="text" name="id" class="form-control" placeholder="username">

                            </div>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" name="password" class="form-control" placeholder="password">
                            </div>
                            <div class="row align-items-center remember">
                                <input type="checkbox">Remember Me
                            </div>
                            <div class="form-group">
                                <input type="submit" name="action" value="Login" class="btn float-right login_btn">
                            </div>                            
                        </form>
                        <%
                            String error = (String) request.getAttribute("ERROR");
                            if (error == null) {
                                error = "";
                            }
                        %>        
                        <h6 style="color: red"><%= error%></h6>
                    </div>
                    
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            Don't have an account?<a href="insert.jsp">Sign up</a>
                        </div>
                        <div class="d-flex justify-content-center">
                            <a href="loginAdmin.jsp">Admin mode</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body> 
</html>
