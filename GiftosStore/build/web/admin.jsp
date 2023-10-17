<%-- 
    Document   : admin
    Created on : 14-10-2023, 21:44:54
    Author     : VietBao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1 style="color: red">Admin page!</h1>
        <h2>USER MANAGEMENT</h2>
        <!--WELCOME-->
        <%
            User loginUser = (User) session.getAttribute("LOGIN_USER");
            //---------Xac thuc va phan quyen-------
            if (loginUser == null || !loginUser.getRole_id().equals("AD")) {
                response.sendRedirect("login.jsp");
                return;
            }
            //--------------------------------------
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        
<!--FORM LOGOUT-->
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Logout"/>
        </form>
<!--FORM SEARCH-->           
        <form action="MainController" method="POST">
            <input type="text" name="search" value="<%= search%>" >
            <input type="submit" name="action" value="Search">
        </form>

<!--LẤY THÔNG TIN VÀ HIỂN THỊ RA DẠNG BẢNG-->
        <%
            List<User> listUser = (List<User>) request.getAttribute("LIST_USER");
            if (listUser != null) {
                if (listUser.size() > 0) {
        %> 
        <h3><a href="MainController?action=Insert">Add new</a></h3>
        <table border="1">
            <thead>
                <tr>
                    <th>no</th>
                    <th>id</th>
                    <th>fullname</th>
                    <th>email</th>
                    <th>google_id</th>
                    <th>phone_number</th>
                    <th>address</th>
                    <th>password</th>      
                    <th>role_id</th> 
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (User user : listUser) {
                %>
            <form action="MainController" method="POST"> 
                <tr>
                    <td> <%= count++%> </td>
                    <td> <%= user.getId()%></td>
                    <td> <input type="text" name="fullname" value="<%= user.getFullname()%>" required=""/> </td>  
                    <td> <input type="text" name="email" value="<%= user.getEmail()%>" required=""/> </td>  
                    <td> <input type="text" name="google_id" value="<%= user.getGoogle_id()%> " readonly=""/> </td>
                    <td> <input type="text" name="phone_number" value="<%= user.getPhone_number()%>" required=""/> </td>
                    <td> <input type="text" name="address" value="<%= user.getAddress()%>" required=""/> </td>
                    <td> <%= user.getPassword()%> </td>                                                             
                    <td> <%= user.getRole_id()%> </td>   
                    <td>                   
                        <!--DELETE-->  
                        <a href="#" onclick="doDelete('<%= user.getId()%>')">Delete</a>                      
                        <!--UPDATE-->    
                        <!--<a href="MainController?action=Update">Update</a>--> 
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="id" value="<%= user.getId()%>"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>
            </tbody>
        </table>               

<!--        CHECK KHÔNG CHO XOÁ TK ĐANG ĐĂNG NHẬP           -->
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>        
        <h4 style="color: red"><%= error%></h4>  
        <%        }
            }
        %>

<!--CONFIRM TO DELETE-->
        <script>
            function doDelete(id) {
                if (confirm("are you sure to delete '" + id + "' ?")) {
                    window.location = "MainController?id=" + id + "&action=Delete&search=" + "<%= search%>";
                }
            }
        </script>        
    </center>
</body>
</html>
