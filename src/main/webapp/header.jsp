<%@ page import="com.example.webshop.model.User" %>
<!-- header.jsp -->
<div class="header">
    <%
        User user = (User) session.getAttribute("user");
        if (user != null) {
    %>
    <p>Welcome, <%= user.getUsername() %>! <a href="logout.jsp">Logout</a></p>
    <%
    } else {
    %>
    <p><a href="login.jsp">Login</a> | <a href="registration.jsp">Register</a></p>
    <%
        }
    %>
</div>
