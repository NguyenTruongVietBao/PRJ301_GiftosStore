<%-- 
    Document   : loginAdmin
    Created on : 14-10-2023, 21:46:24
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
        <h1>Admin Login</h1>
        <form action="MainController" method="POST">
            User ID<input type="text" name="id"/>
            </br>Password <input type="password" name="password"/>
            </br><input type="submit" name="action" value="LoginAd"/>
            <input type="reset" value="Reset"/>            
        </form>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>        
        <h4 style="color: red"><%= error%></h4>
        <a href="login.jsp">User mode</a>
    </body>
</html>
