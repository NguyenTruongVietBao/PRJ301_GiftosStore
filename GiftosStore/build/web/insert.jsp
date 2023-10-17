<%-- 
    Document   : insert
    Created on : 15-10-2023, 18:21:53
    Author     : VietBao
--%>

<%@page import="model.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create user</h1>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if(userError == null){
                userError = new UserError();
            }
        %>
        <form action="MainController" method="POST"> 
            id<input type="text" name="id" required=""/> 
            <%= userError.getId()%></br>
            
            fullname<input type="text" name="fullname" required=""/>  
            <%= userError.getFullname()%></br>
            
            role_id<input type="text" name="role_id" value="US" readonly=""/> </br>
            <%= userError.getRole_id()%></br>
            
            Password<input type="password" name="password" required=""/>  </br>
            Confirm Password<input type="password" name="confirm" required=""/>  
            <%= userError.getConfirmError()%></br>
            
            Email<input type="text" name="email" />
            <%= userError.getEmail()%></br>           
            
            address<input type="text" name="address" />
            <%= userError.getAddress()%></br>
            
            phone_number<input type="text" name="phone_number" />
            <%= userError.getPhone_number()%></br>     
            <input type="submit" name="action" value="Insert"/>
            <input type="reset" value="Reset"/>
        </form>
            <h3><a href="SearchController">Back</a> </h3>
    </body>
</html>
