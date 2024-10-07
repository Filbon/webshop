<%@ page import="com.example.webshop.dto.UserDTO" %>
<!-- header.jsp -->
<div class="header">
    <%
        UserDTO user = (UserDTO) session.getAttribute("user");
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
