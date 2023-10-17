<%-- 
    Document   : login
    Created on : 14-10-2023, 18:35:09
    Author     : VietBao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User Login</h1>
        <form action="MainController" method="POST">
            User ID<input type="text" name="id"/>
            </br>Password <input type="password" name="password"/>
            </br><input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>            
        </form>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>        
        <h4 style="color: red"><%= error%></h4>
        
        <div>Don't have an account? <a href="insert.jsp">Sign up</a> </div>
        <a href="loginAdmin.jsp">Admin mode</a>
    </body> 
</html>
